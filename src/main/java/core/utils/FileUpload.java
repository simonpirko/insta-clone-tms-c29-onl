package core.utils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUpload {

    public static String saveFile(String uploadDir, Part part) throws IOException {
        String fileName = part.getSubmittedFileName();
        String filePath = uploadDir + File.separator + fileName;
        part.write(filePath);
        return filePath;
    }
}