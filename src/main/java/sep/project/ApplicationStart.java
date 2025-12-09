package sep.project;

import sep.project.Controllers.RestApiController;
import sep.project.Controllers.WebPageController;
import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AtomicModels.Administrator;
import sep.project.Models.AtomicModels.CommunityGreenPoints;
import sep.project.Services.ClovervillePersistenceService;

public class ApplicationStart {

    public static void Main() throws Exception {

        // pull everything from persistence and init all the necessary models

        // first pull the stuff we persist;
        PointTradeList pointTradeList = ClovervillePersistenceService.loadTradeList();
        CommunityGreenPoints communityGreenPoints = ClovervillePersistenceService.loadCommunityGreenPoints();
        ClovervilleResidentList clovervilleResidentList = ClovervillePersistenceService.loadClovervilleResidentList();

        // then distribute them across the system
        
        //init the controllers
        WebPageController webPageController = new WebPageController();
        RestApiController restApiController = new RestApiController(pointTradeList, clovervilleResidentList, communityGreenPoints);


        //create Admin user
        Administrator admin = new Administrator("Green Bob");

        
    }
}
