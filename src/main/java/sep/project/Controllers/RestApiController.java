package sep.project.Controllers;

import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.CommunityTaskList;
import sep.project.Models.AggregativeModels.GreenActionList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AtomicModels.CommunityGreenPoints;

import spark.Spark;

/**
 * <h3>
 * returns only text
 * </h3>
 * <p>
 * for fetch calls that then load on page
 * </p>
 */
// ... imports
// ... class definition
public class RestApiController {

    private final PointTradeList pointTradeList;
    private final ClovervilleResidentList clovervilleResidentList;
    private final CommunityGreenPoints communityGreenPoints;
    private final CommunityTaskList communityTaskList;
    private final GreenActionList greenActionList;

    public RestApiController(PointTradeList pointTradeList, ClovervilleResidentList clovervilleResidentList,
            CommunityGreenPoints communityGreenPoints, CommunityTaskList communityTaskList,
            GreenActionList greenActionList) {
        this.communityGreenPoints = communityGreenPoints;
        this.pointTradeList = pointTradeList;
        this.clovervilleResidentList = clovervilleResidentList;
        this.communityTaskList = communityTaskList;
        this.greenActionList = greenActionList;
    }

    public void setupRoutes() {
        Spark.path("/api", () -> {

            Spark.get("/pointTrades", (request, response) -> {
                response.type("application/json");
                return pointTradeList.toJsonString();
            });
            Spark.get("/greenActions", (request, response) -> {
                response.type("application/json");
                System.out.println("processing");
                return greenActionList.getWeekLongActionListJson();
            });
            Spark.get("/communityTasks", (request, response) -> {
                response.type("application/json");
                return communityTaskList.toJsonString();
            });

            Spark.get("/greenPoints", (request, response) -> {
                response.type("application/json");
                return communityGreenPoints.toJsonString();
            });

        });
    }

}