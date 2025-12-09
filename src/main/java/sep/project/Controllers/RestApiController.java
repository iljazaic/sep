package sep.project.Controllers;

import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.CommunityTaskList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AtomicModels.ClovervilleResident;
import sep.project.Models.AtomicModels.CommunityGreenPoints;

import spark.Spark; // Required for SparkJava routing

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

    public RestApiController(PointTradeList pointTradeList, ClovervilleResidentList clovervilleResidentList,
            CommunityGreenPoints communityGreenPoints, CommunityTaskList communityTaskList) {
        this.communityGreenPoints = communityGreenPoints;
        this.pointTradeList = pointTradeList;
        this.clovervilleResidentList = clovervilleResidentList;
        this.communityTaskList = communityTaskList;
    }

    public void setupRoutes() {
        Spark.path("/api", () -> {

            Spark.get("/pointTrades", (request, response) -> {
                response.type("application/json");
                return pointTradeList.toJsonString();
            });

            Spark.get("/greenPoints", (request, response) -> {
                response.type("application/json");
                return communityGreenPoints.toJsonString();
            });

            Spark.get("/residents", (request, response) -> {
                response.type("application/json");
                return clovervilleResidentList.toJsonString();
            });

            Spark.get("/communityTasks", (request, response) -> {
                response.type("application/json");
                return communityTaskList.toJsonString();
            });

        });
    }


    public ClovervilleResident getResidentById(Long residentId) {
        // Placeholder for actual lookup logic
        return this.clovervilleResidentList.getClovervilleResidentById(residentId);
    }

}