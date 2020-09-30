package cn.np.boots.plugin.maven.pkg.container.common;

import java.util.jar.JarFile;

public class ArkUtils {

    /**
     * Archiver Marker
     */
    public final static String       ARK_CONTAINER_MARK_ENTRY                      = "com/alipay/sofa/ark/container/mark";

    public final static String       ARK_PLUGIN_MARK_ENTRY                         = "com/alipay/sofa/ark/plugin/mark";

    public final static String       ARK_BIZ_MARK_ENTRY                            = "com/alipay/sofa/ark/biz/mark";

    public static boolean isArkContainer(JarFile jarFile) {
        return jarFile.getEntry(ARK_CONTAINER_MARK_ENTRY) != null;
    }

    public static  boolean isArkPlugin(JarFile jarFile) {
        return jarFile.getEntry(ARK_PLUGIN_MARK_ENTRY) != null;
    }

    public static  boolean isArkModule(JarFile jarFile) {
        return jarFile.getEntry(ARK_BIZ_MARK_ENTRY) != null;
    }
}
