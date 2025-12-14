package sep.project;

import java.util.ArrayList;
import java.util.Scanner;

import sep.project.AdminGuiRender.AdminUIApp;
import sep.project.AdminGuiRender.AdminUILauncher;
import sep.project.Controllers.AdminUIController;
import sep.project.Controllers.RestApiController;
import sep.project.Controllers.WebPageController;
import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.CommunityTaskList;
import sep.project.Models.AggregativeModels.GreenActionList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AtomicModels.CommunityGreenPoints;
import sep.project.Services.ClovervillePersistenceService;

import spark.Spark;

public class ApplicationStart {

        public static void main(String[] args) throws Exception {
                // start web server obv
                Spark.port(8080);
                Spark.staticFileLocation("/static");
                // pull everything from persistence and init all the necessary models

                // first pull the stuff we persist;
                PointTradeList pointTradeList = ClovervillePersistenceService.loadTradeList();
                CommunityGreenPoints communityGreenPoints = ClovervillePersistenceService.loadCommunityGreenPoints();
                ClovervilleResidentList clovervilleResidentList = ClovervillePersistenceService
                                .loadClovervilleResidentList();
                CommunityTaskList communityTaskList = ClovervillePersistenceService.load;

                GreenActionList greenActionList = ClovervillePersistenceService.loadClovervilleGreenActionList();
                // then distribute them across the system

                // init the controllers
                // this is nonsense ofc
                WebPageController webPageController = new WebPageController(pointTradeList, clovervilleResidentList,
                                communityGreenPoints, communityTaskList, greenActionList);
                webPageController.setupRoutes();

                RestApiController restApiController = new RestApiController(pointTradeList, clovervilleResidentList,
                                communityGreenPoints, communityTaskList);
                restApiController.setupRoutes();

                AdminUIController adminUIController = new AdminUIController(clovervilleResidentList, greenActionList,
                                communityTaskList, pointTradeList, communityGreenPoints);

                // lastly, launch admin gui
                AdminUILauncher.startAdminUI(
                                clovervilleResidentList,
                                greenActionList,
                                communityTaskList,
                                pointTradeList,
                                communityGreenPoints,
                                adminUIController);
        }
}
