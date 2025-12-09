package sep.project.Services;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class WebPageService {
    
    /**
     * Reads the content of a file located in the resources directory.
     * @param fileName The path to the file relative to the resources root.
     * @return The file content as a single String.
     */
    public static String renderHtml(String fileName) {
        try {
            // Assumes files are placed in src/main/resources/
            String path = "src/main/resources/public/" + fileName;
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
            return "<html><body><h1>Error loading page!</h1></body></html>";
        }
    }
}