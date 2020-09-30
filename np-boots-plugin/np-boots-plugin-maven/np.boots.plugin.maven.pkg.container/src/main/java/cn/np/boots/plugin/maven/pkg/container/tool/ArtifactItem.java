package cn.np.boots.plugin.maven.pkg.container.tool;

import org.apache.maven.artifact.Artifact;

public class ArtifactItem {

    private static final String GAV_SPLIT = ":";

    private String              groupId;

    private String              artifactId;

    private String              version   = "?";

    private String              classifier;

    private String              type      = "jar";

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getClassifier() {
        return classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        if (this.classifier == null) {
            return String.format("%s%s%s%s%s%s%s", groupId, GAV_SPLIT, artifactId, GAV_SPLIT,
                    version, GAV_SPLIT, type);
        } else {
            return String.format("%s%s%s%s%s%s%s%s%s", groupId, GAV_SPLIT, artifactId, GAV_SPLIT,
                    classifier, GAV_SPLIT, version, GAV_SPLIT, type);
        }
    }

    public boolean isSameIgnoreVersion(ArtifactItem that) {
        if (that == null) {
            return false;
        }

        return StringUtils.isSameStr(this.getGroupId(), that.getGroupId())
                && StringUtils.isSameStr(this.getArtifactId(), that.getArtifactId())
                && StringUtils.isSameStr(this.getClassifier(), that.getClassifier());
    }

    /**
     * parse string pattern {groupId:artifactId} or {groupId:artifactId:classifier}
     * @param s location pattern
     * @return
     */
    public static ArtifactItem parseArtifactItemIgnoreVersion(String s) {
        String[] arr = new String[] {};

        if (s != null && !s.isEmpty()) {
            arr = s.split(GAV_SPLIT);
        }

        // groupId, artifactId and classifier(optional)
        AssertUtils.isTrue(arr != null && arr.length >= 2 && arr.length <= 3,
                "artifact item format error: %s", s);

        ArtifactItem item = new ArtifactItem();
        item.setGroupId(arr[0]);
        item.setArtifactId(arr[1]);
        if (arr.length == 3) {
            item.setClassifier(arr[3]);
        }
        return item;
    }

    /**
     * parse string pattern {groupId:artifactId:version} or {groupId:artifactId:version:classifier}
     * @param s location pattern
     * @return
     */
    public static ArtifactItem parseArtifactItemWithVersion(String s) {
        String[] arr = new String[] {};

        if (s != null && !s.isEmpty()) {
            arr = s.split(GAV_SPLIT);
        }

        // groupId, artifactId, version and classifier(optional)
        AssertUtils.isTrue(arr != null && arr.length >= 3 && arr.length <= 4,
                "artifact item format error: %s", s);

        ArtifactItem item = new ArtifactItem();
        item.setGroupId(arr[0]);
        item.setArtifactId(arr[1]);
        item.setVersion(arr[2]);
        if (arr.length == 4) {
            item.setClassifier(arr[3]);
        }
        return item;
    }

    public static ArtifactItem parseArtifactItem(Artifact artifact) {
        ArtifactItem artifactItem = new ArtifactItem();
        artifactItem.setGroupId(artifact.getGroupId());
        artifactItem.setArtifactId(artifact.getArtifactId());
        artifactItem.setClassifier(artifact.getClassifier());
        artifactItem.setVersion(artifact.getVersion());
        artifactItem.setType(artifact.getType());
        return artifactItem;
    }
}
