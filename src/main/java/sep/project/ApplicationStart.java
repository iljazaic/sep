package sep.project;

import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AtomicModels.CommunityGreenPoints;
import sep.project.Services.ClovervillePersistenceService;

public class ApplicationStart {

    public static void Main() throws Exception{
        //pull everything from persistence and init all the necessary models


        //first pull the stuff we persist;
        PointTradeList pointTradeList = ClovervillePersistenceService.loadTradeList();
        CommunityGreenPoints communityGreenPoints = ClovervillePersistenceService.loadCommunityGreenPoints();
        ClovervilleResidentList clovervilleResidentList = ClovervillePersistenceService
        //second using those init the controllers
        


    }
}
