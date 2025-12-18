package sep.project;

import sep.project.AdminGuiRender.AdminUILauncher;
import sep.project.Controllers.AdminUIController;
import sep.project.Controllers.RestApiController;
import sep.project.Controllers.WebPageController;
import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.CommunityTaskList;
import sep.project.Models.AggregativeModels.CommunityTaskTemplateList;
import sep.project.Models.AggregativeModels.GreenActionList;
import sep.project.Models.AggregativeModels.GreenActionTemplateList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AtomicModels.CommunityGreenPoints;
import sep.project.Models.AtomicModels.GreenActionTemplate;
import sep.project.Services.ClovervillePersistenceService;

import spark.Spark;

public class ApplicationStart {

        public static void main(String[] args) throws Exception {
                // start web server obv
                Spark.port(8080);
                Spark.staticFileLocation("/static");

                // now pull everything from persistence and init all the necessary models

                // here, my idea was to keep one initial instance of every list object "owned"
                // by
                // the main() method, and then references to it passed around the other systems
                // to
                // ensure consistency. this would mean that a change to a list in the
                // adminController would result in the same change being visible in restApi so
                // as persistence is maintained

                // first pull the stuff we persist;
                PointTradeList pointTradeList = ClovervillePersistenceService.loadTradeList();
                CommunityGreenPoints communityGreenPoints = ClovervillePersistenceService.loadCommunityGreenPoints();
                ClovervilleResidentList clovervilleResidentList = ClovervillePersistenceService
                                .loadClovervilleResidentList();
                CommunityTaskList communityTaskList = ClovervillePersistenceService.loadCommunityTasks();

                GreenActionList greenActionList = ClovervillePersistenceService.loadClovervilleGreenActionList();


                GreenActionTemplateList greenActionTemplateList = ClovervillePersistenceService.loadGreenActionTemplates();
                CommunityTaskTemplateList communityTaskTemplateList = ClovervillePersistenceService.loadCommunityTaskTemplates();

                // then distribute them across the system

                // init the controllers
                WebPageController webPageController = new WebPageController();
                webPageController.setupRoutes();

                RestApiController restApiController = new RestApiController(pointTradeList, clovervilleResidentList,
                                communityGreenPoints, communityTaskList, greenActionList);
                restApiController.setupRoutes();

                AdminUIController adminUIController = new AdminUIController(clovervilleResidentList, greenActionList,
                                communityTaskList, pointTradeList, communityGreenPoints, greenActionTemplateList, communityTaskTemplateList);

                // now, launch admin gui to actually do things
                AdminUILauncher.startAdminUI(adminUIController);
        }
}
