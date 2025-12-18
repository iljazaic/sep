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
            Spark.get("/aboutus", (request, response) -> {
                response.type("text/html");
                return WebPageService.renderHtml("aboutus.html");
            });
            Spark.get("/contact", (request, response) -> {
                response.type("text/html");
                return WebPageService.renderHtml("contact.html");
            });
            Spark.get("/green-points", (request, response) -> {
                response.type("text/html");
                return WebPageService.renderHtml("green-points.html");
            });
            Spark.get("/green-tasks", (request, response) -> {
                response.type("text/html");
                return WebPageService.renderHtml("green-tasks.html");
            });
            Spark.get("/task-trades", (request, response) -> {
                response.type("text/html");
                return WebPageService.renderHtml("task-trades.html");
            });

        });
    }

}