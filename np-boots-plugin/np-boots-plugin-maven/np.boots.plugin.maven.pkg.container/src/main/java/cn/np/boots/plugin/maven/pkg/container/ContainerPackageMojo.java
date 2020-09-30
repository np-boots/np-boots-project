package cn.np.boots.plugin.maven.pkg.container;

import cn.np.boots.plugin.maven.pkg.container.tool.ArtifactItem;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.archiver.Archiver;
import org.codehaus.plexus.archiver.manager.ArchiverManager;
import org.codehaus.plexus.archiver.manager.NoSuchArchiverException;
import org.codehaus.plexus.archiver.zip.AbstractZipArchiver;

import java.io.File;
import java.util.*;

@Mojo(name = "np-boots-container-isolation-maven-plugin", defaultPhase = LifecyclePhase.PACKAGE, requiresDependencyResolution = ResolutionScope.RUNTIME)
public class ContainerPackageMojo extends AbstractMojo {
    private static final String ARCHIVE_MODE = "zip";
    private static final String PLUGIN_SUFFIX = ".ark.plugin";
    private static final String TEMP_PLUGIN_SUFFIX = ".ark.plugin.bak";


    @Component
    protected MavenProject project;

    @Component
    protected ArchiverManager archiverManager;


    /* configuration */
    @Parameter(defaultValue = "${project.groupId}", readonly = true)
    protected String groupId;

    @Parameter(defaultValue = "${project.artifactId}", readonly = true)
    protected String artifactId;

    @Parameter(defaultValue = "${project.version}", readonly = true)
    protected String version;

    @Parameter(defaultValue = "${project.artifactId}")
    public String pluginName;

    @Parameter(defaultValue = " ")
    protected String description;

    @Parameter(defaultValue = "${project.build.directory}", property = "sofa.ark.plugin.repository")
    protected File outputDirectory;


    /*  config */

    @Parameter(defaultValue = "")
    protected LinkedHashSet<String> excludeGroupIds;

    @Parameter(defaultValue = "")
    protected LinkedHashSet<String> excludeArtifactIds;

    @Parameter(defaultValue = "")
    protected LinkedHashSet<String> excludes = new LinkedHashSet<>();

    public void execute() throws MojoExecutionException, MojoFailureException {
        Archiver archiver;

        try {
            archiver = getArchiver();
        } catch (NoSuchArchiverException e) {
            throw new MojoExecutionException(e.getMessage());
        }

        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }

        String fileName = getFileName();
        File destination = new File(outputDirectory, fileName);
        File tmpDestination = new File(outputDirectory, getTempFileName());
        if (destination.exists()) {
            destination.delete();
        }
        if (tmpDestination.exists()) {
            tmpDestination.delete();
        }
        archiver.setDestFile(tmpDestination);

        Set<Artifact> artifacts = project.getArtifacts();
        artifacts = filterExcludeArtifacts(artifacts);

        Set<Artifact> conflictArtifacts = filterConflictArtifacts(artifacts);

        addArkPluginArtifact(archiver, artifacts, conflictArtifacts);
    }

    private Archiver getArchiver() throws NoSuchArchiverException {
        AbstractZipArchiver archiver = (AbstractZipArchiver) archiverManager.getArchiver(ARCHIVE_MODE);
        archiver.setCompress(false);
        return archiver;
    }

    private String getFileName() {
        return String.format("%s%s", pluginName, PLUGIN_SUFFIX);
    }

    protected String getTempFileName() {
        return String.format("%s%s", pluginName, TEMP_PLUGIN_SUFFIX);
    }

    protected Set<Artifact> filterExcludeArtifacts(Set<Artifact> artifacts) {
        List<ArtifactItem> excludeList = new ArrayList<>();
        for (String exclude : excludes) {
            ArtifactItem item = ArtifactItem.parseArtifactItemIgnoreVersion(exclude);
            excludeList.add(item);
        }

        Set<Artifact> result = new LinkedHashSet<>();
        for (Artifact e : artifacts) {
            boolean isExclude = false;

            for (ArtifactItem exclude : excludeList) {
                if (exclude.isSameIgnoreVersion(ArtifactItem.parseArtifactItem(e))) {
                    isExclude = true;
                    break;
                }
            }

            if (excludeGroupIds != null && excludeGroupIds.contains(e.getGroupId())) {
                isExclude = true;
            }

            if (excludeArtifactIds != null && excludeArtifactIds.contains(e.getArtifactId())) {
                isExclude = true;
            }

            if (!isExclude) {
                result.add(e);
            }
        }

        return result;
    }

    protected Set<Artifact> filterConflictArtifacts(Set<Artifact> artifacts) {
        HashMap<String, Artifact> existArtifacts = new HashMap<>(Collections.singletonMap(project.getArtifact().getArtifactId(), project.getArtifact()));
        HashSet<Artifact> conflictArtifacts = new HashSet<>();

        for (Artifact artifact : artifacts) {
            if (existArtifacts.containsKey(artifact.getArtifactId())) {
                conflictArtifacts.add(artifact);
                conflictArtifacts.add(existArtifacts.get(artifact.getArtifactId()));
            } else {
                existArtifacts.put(artifact.getArtifactId(), artifact);
            }
        }

        return conflictArtifacts;
    }

    protected void addArkPluginArtifact(Archiver archiver, Set<Artifact> dependencies,
                                        Set<Artifact> conflicts) {
        addArtifact(archiver, project.getArtifact(), conflicts.contains(project.getArtifact()));
        for (Artifact artifact : dependencies) {
            if (Repackager.isZip(artifact.getFile())) {
                addArtifact(archiver, artifact, conflicts.contains(artifact));
            }
        }
    }

    protected void addArtifact(Archiver archiver, Artifact artifact, boolean artifactIdConflict) {
        if (isShadeJar(artifact)) {
            return;
        }
        String destination = artifact.getFile().getName();
        if (artifactIdConflict) {
            destination = artifact.getGroupId() + "-" + destination;
        }
        destination = "lib/" + destination;
        getLog().debug("  " + artifact + " => " + destination);
        archiver.addFile(artifact.getFile(), destination);
    }
}
