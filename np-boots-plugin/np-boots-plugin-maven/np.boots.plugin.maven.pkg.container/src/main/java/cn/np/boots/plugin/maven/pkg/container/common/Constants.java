package cn.np.boots.plugin.maven.pkg.container.common;

public interface Constants {

    /**
     * String Constants
     */
    public final static String SPACE_SPLIT = "\\s+";
    public final static String STRING_COLON = ":";
    public final static String STRING_SEMICOLON = ";";
    public final static String TELNET_STRING_END = new String(new byte[]{(byte) 13, (byte) 10});
    public final static String COMMA_SPLIT = ",";
    public final static String EMPTY_STR = "";
    public final static String AMPERSAND_SPLIT = "&";
    public final static String EQUAL_SPLIT = "=";
    public final static String QUESTION_MARK_SPLIT = "?";
    public final static String ROOT_WEB_CONTEXT_PATH = "/";

    /**
     * Ark Biz Attribute
     */
    String MAIN_CLASS_ATTRIBUTE = "Main-Class";
    String ARK_BIZ_NAME = "Ark-Biz-Name";
    String ARK_BIZ_VERSION = "Ark-Biz-Version";
    String DENY_IMPORT_CLASSES = "deny-import-classes";
    String DENY_IMPORT_PACKAGES = "deny-import-packages";
    String DENY_IMPORT_RESOURCES = "deny-import-resources";

    String PACKAGE_PREFIX_MARK = "*";
    String DEFAULT_PACKAGE = ".";
    String MANIFEST_VALUE_SPLIT = COMMA_SPLIT;
    String RESOURCE_STEM_MARK = "*";

    String IMPORT_RESOURCES_ATTRIBUTE = "import-resources";
    String EXPORT_RESOURCES_ATTRIBUTE = "export-resources";

    String SUREFIRE_BOOT_CLASSPATH = "Class-Path";
    String SUREFIRE_BOOT_CLASSPATH_SPLIT = " ";
    String SUREFIRE_BOOT_JAR = "surefirebooter";
}
