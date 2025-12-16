package sep.project.Controllers;

import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.CommunityTaskList;
import sep.project.Models.AggregativeModels.GreenActionList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AtomicModels.ClovervilleResident;
import sep.project.Models.AtomicModels.CommunityGreenPoints;
import sep.project.Models.AtomicModels.GreenAction;
import sep.project.Services.ClovervillePersistenceService;
import sep.project.Services.WebPageService;
import spark.Spark; // Required for SparkJava routing

/**
 * <h3>
 * returns html files and static files
 * </h3>
 * <p>
 * for website
 * </p>
 */
// ... imports
// ... class definition
public class WebPageController {

    private final PointTradeList pointTradeList;
    private final ClovervilleResidentList clovervilleResidentList;
    private final CommunityGreenPoints communityGreenPoints;
    private final CommunityTaskList communityTaskList;
    private final GreenActionList greenActionList;

    // private final String pathToHtmlFiles = "src/main/resources";
    private final String pathToStaticFiles = "src/main/resources/static";

    public WebPageController(PointTradeList pointTradeList, ClovervilleResidentList clovervilleResidentList,
            CommunityGreenPoints communityGreenPoints, CommunityTaskList communityTaskList,
            GreenActionList greenActionList) {
        this.communityGreenPoints = communityGreenPoints;
        this.pointTradeList = pointTradeList;
        this.clovervilleResidentList = clovervilleResidentList;
        this.communityTaskList = communityTaskList;
        this.greenActionList = greenActionList;
    }

    public void setupRoutes() {

        Spark.staticFiles.location(pathToStaticFiles);

        Spark.path("/", () -> {

            Spark.get("/", (request, response) -> {
                response.type("text/html");
                return WebPageService.renderHtml("index.html");
            });
            Spark.get("/about", (request, response) -> {
                response.type("text/html");
                return WebPageService.renderHtml("aboutus.html");
            });
            Spark.get("/register-green-action", (request, response) -> {
                response.type("text/html");

                String userName = request.queryParams("userName");
                String actionDesc = request.queryParams("description");

                ClovervilleResident registeringResident = clovervilleResidentList
                        .getClovervilleResidentByName(userName);

                if (registeringResident != null) {
                    GreenAction action = new GreenAction(actionDesc, 0, registeringResident.getResidentId());
                    // create an unapproved action
                    // then we can display all the unapproved actions for the admin to approve yay
                    greenActionList.addGreenAction(action);
                    ClovervillePersistenceService.saveList(greenActionList);
                }
                return WebPageService.renderHtml("createUser.html");
            });
        });
    }

    public ClovervilleResident getResidentById(Long residentId) {
        // Placeholder for actual lookup logic
        return this.clovervilleResidentList.getClovervilleResidentById(residentId);
    }

}