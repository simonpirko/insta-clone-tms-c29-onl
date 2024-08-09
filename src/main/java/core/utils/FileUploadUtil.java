
package core.utils;


import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class FileUploadUtil {
    public static String saveFile(String uploadDir, Part part) {

        String fileName = part.getSubmittedFileName();
        String filePath = uploadDir + File.separator + fileName;
        try {
            part.write(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }
}