package sep.project.AdminGuiRender;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import sep.project.Controllers.AdminUIController;
// Import your model lists
import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.GreenActionList;
import sep.project.Models.AggregativeModels.CommunityTaskList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AtomicModels.CommunityGreenPoints;

// Import atomic models for simulation/testing
import sep.project.Models.AtomicModels.ClovervilleResident;
import sep.project.Models.AtomicModels.GreenAction;

public class AdminUIApp extends Application {

    // IMPORTANT: In your actual application, these fields would hold the
    // instances returned by your persistence layer (e.g., JSON file loaders).
    private ClovervilleResidentList residentList;
    private GreenActionList greenActionList;
    private CommunityTaskList communityTaskList;
    private PointTradeList tradeList;
    private CommunityGreenPoints communityPoints;

    @Override
    public void start(Stage primaryStage) {
        // --- 1. Load Data from Persistence (Simulated) ---
        // REPLACE THIS BLOCK with your actual file loading logic.
        residentList = new ClovervilleResidentList();
        residentList.setResidentList(new ArrayList<>());

        greenActionList = new GreenActionList();
        greenActionList.setGreenActionList(new ArrayList<>());

        // Example resident for ID usage
        ClovervilleResident adminUser = new ClovervilleResident("Admin");
        residentList.addResident(adminUser);

        // Example unapproved action
        greenActionList.addGreenAction(new GreenAction("Planted tree", 100, adminUser.getResidentId()));

        // Note: CommunityTaskList constructor takes ArrayList<CommunityTask>
        communityTaskList = new CommunityTaskList(new ArrayList<>());

        tradeList = new PointTradeList();

        communityPoints = new CommunityGreenPoints(5000);

        try {

            FXMLLoader loader = new FXMLLoader(
                    AdminUIApp.class.getResource(
                            "/local/AdminUI.fxml"));
            // --- 2. Set the Controller Factory ---
            // This tells the FXMLLoader how to construct the AdminUIController,
            // allowing us to pass our pre-loaded, single-instance models.
            //loader.setControllerFactory(c -> {
            //    if (c == AdminUIController.class) {
            //        return new AdminUIController(
            //                residentList,
            //                greenActionList,
            //                communityTaskList,
            //                tradeList,
            //                communityPoints);
            //    }
            //    // Use default factory for other controllers
            //    try {
            //        return c.getDeclaredConstructor().newInstance();
            //    } catch (Exception e) {
            //        throw new RuntimeException("Could not create controller", e);
            //    }
            //});

            AdminUIController controller = new AdminUIController(
                    residentList,
                    greenActionList,
                    communityTaskList,
                    tradeList,
                    communityPoints);

            loader.setController(controller);

            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Cloverville Green Points Admin Panel");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}