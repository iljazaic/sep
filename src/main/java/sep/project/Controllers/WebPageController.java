package sep.project.Controllers;

import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.CommunityTaskList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AtomicModels.ClovervilleResident;
import sep.project.Models.AtomicModels.CommunityGreenPoints;
import sep.project.Models.AtomicModels.GreenAction;
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

    //private final String pathToHtmlFiles = "src/main/resources";
    private final String pathToStaticFiles = "src/main/resources/static";

    public WebPageController(PointTradeList pointTradeList, ClovervilleResidentList clovervilleResidentList,
            CommunityGreenPoints communityGreenPoints, CommunityTaskList communityTaskList) {
        this.communityGreenPoints = communityGreenPoints;
        this.pointTradeList = pointTradeList;
        this.clovervilleResidentList = clovervilleResidentList;
        this.communityTaskList = communityTaskList;
    }

    public void setupRoutes() {

        Spark.staticFiles.location(pathToStaticFiles);

        Spark.path("/", () -> {

            Spark.get("/", (request, response) -> {
                response.type("text/html");
                return WebPageService.renderHtml("index.html");
            });
            Spark.get("/register-green-action", (request,response)->{
                response.type("text/html");






                String userName = request.queryParams("userName");
                String actionDesc = request.queryParams("description");
                

                GreenAction action = new GreenAction(actionDesc, 0);
                

                clovervilleResidentList.addResident(new ClovervilleResident(userName));

                return WebPageService.renderHtml("createUser.html");
            });
        });
    }

    public ClovervilleResident getResidentById(Long residentId) {
        // Placeholder for actual lookup logic
        return this.clovervilleResidentList.getClovervilleResidentById(residentId);
    }

}