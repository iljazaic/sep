package sep.project;

import java.util.Scanner;

import sep.project.AdminGuiRender.AdminGui;
import sep.project.Controllers.RestApiController;
import sep.project.Controllers.WebPageController;
import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.CommunityTaskList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AtomicModels.CommunityGreenPoints;
import sep.project.Services.ClovervillePersistenceService;

import spark.Spark;

public class ApplicationStart {

    public static void main(String[] args) throws Exception {

        System.out.println("Starting Application");

        Scanner scan = new Scanner(System.in); // Create a Scanner object
        String text = scan.nextLine(); // Read user input
        // start web server
        Spark.port(8080);

        // pull everything from persistence and init all the necessary models

        // first pull the stuff we persist;
        PointTradeList pointTradeList = ClovervillePersistenceService.loadTradeList();
        CommunityGreenPoints communityGreenPoints = ClovervillePersistenceService.loadCommunityGreenPoints();
        ClovervilleResidentList clovervilleResidentList = ClovervillePersistenceService.loadClovervilleResidentList();
        // CommunityTaskList communityTaskList =
        // ClovervillePersistenceService.loadCommunityTaskList();
        CommunityTaskList communityTaskList = null;
        // then distribute them across the system

        // init the controllers

        WebPageController webPageController = new WebPageController(pointTradeList, clovervilleResidentList,
                communityGreenPoints, communityTaskList);
        webPageController.setupRoutes();

        RestApiController restApiController = new RestApiController(pointTradeList, clovervilleResidentList,
                communityGreenPoints, communityTaskList);
        restApiController.setupRoutes();

        // create Admin user
        // admin = new Administrator("Green Bob");

        // lastly, launch admin gui
        // AdminGui.launch();
    }
}
