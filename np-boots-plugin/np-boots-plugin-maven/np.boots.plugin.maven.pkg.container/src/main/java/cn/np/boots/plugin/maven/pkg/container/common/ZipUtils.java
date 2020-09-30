package cn.np.boots.plugin.maven.pkg.container.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ZipUtils {
    private static final byte[] ZIP_FILE_HEADER = new byte[]{'P', 'K', 3, 4};

    public static boolean isZip(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                return isZip(fileInputStream);
            } finally {
                fileInputStream.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }

    public static boolean isZip(InputStream inputStream) throws IOException {
        for (int i = 0; i < ZIP_FILE_HEADER.length; i++) {
            if (inputStream.read() != ZIP_FILE_HEADER[i]) {
                return false;
            }
        }
        return true;
    }
}
