package sep.project;

import sep.project.Controllers.RestApiController;
import sep.project.Controllers.WebPageController;
import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.CommunityTaskList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AtomicModels.Administrator;
import sep.project.Models.AtomicModels.CommunityGreenPoints;
import sep.project.Services.ClovervillePersistenceService;

import spark.Spark;

public class ApplicationStart {

    public static void Main() throws Exception {

        // start web server
        Spark.port(8080);

        // pull everything from persistence and init all the necessary models

        // first pull the stuff we persist;
        PointTradeList pointTradeList = ClovervillePersistenceService.loadTradeList();
        CommunityGreenPoints communityGreenPoints = ClovervillePersistenceService.loadCommunityGreenPoints();
        ClovervilleResidentList clovervilleResidentList = ClovervillePersistenceService.loadClovervilleResidentList();
        CommunityTaskList communityTaskList = ClovervillePersistenceService.loadCommunityTaskList();

        // then distribute them across the system

        // init the controllers
        
        WebPageController webPageController = new WebPageController();
        webPageController.setupRoutes();

        RestApiController restApiController = new RestApiController(pointTradeList, clovervilleResidentList,
                communityGreenPoints, communityTaskList);
        restApiController.setupRoutes();

        // create Admin user
        Administrator admin = new Administrator("Green Bob");

    }
}
