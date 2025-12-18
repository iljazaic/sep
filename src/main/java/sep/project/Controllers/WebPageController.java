package sep.project.Controllers;

import sep.project.Services.WebPageService;
import spark.Spark;

/**
 * <h3>
 * returns html files and static files
 * </h3>
 * <p>
 * for website
 * </p>
 */
public class WebPageController {

    public WebPageController() {
    }

    public void setupRoutes() {

        Spark.path("/", () -> {

            Spark.get("/", (request, response) -> {
                response.type("text/html");
                return WebPageService.renderHtml("index.html");
            });
            Spark.get("/about", (request, response) -> {
                response.type("text/html");
                return WebPageService.renderHtml("aboutus.html");
            });
        });
    }

}