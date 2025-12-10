package sep.project.Services;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class WebPageService {
    
    /**
     * Reads and spits out file text
     * honestly just a helper function dont pay too much attention
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