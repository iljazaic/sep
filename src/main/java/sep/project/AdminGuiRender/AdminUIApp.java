package sep.project.AdminGuiRender;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import sep.project.Controllers.AdminUIController;
import sep.project.Models.AggregativeModels.ClovervilleResidentList;
import sep.project.Models.AggregativeModels.GreenActionList;
import sep.project.Models.AggregativeModels.CommunityTaskList;
import sep.project.Models.AggregativeModels.PointTradeList;
import sep.project.Models.AtomicModels.CommunityGreenPoints;


public class AdminUIApp extends Application {

    private ClovervilleResidentList residentList;
    private GreenActionList greenActionList;
    private CommunityTaskList communityTaskList;
    private PointTradeList tradeList;
    private CommunityGreenPoints communityPoints;

    @Override
    public void start(Stage primaryStage) {

        //pulls from the launcher when they are assigned - since the application is only launched after assigning the static values keep it unchecked
        this.residentList = AdminUILauncher.residentList;
        this.greenActionList = AdminUILauncher.greenActionList;
        this.communityTaskList = AdminUILauncher.communityTaskList;
        this.tradeList = AdminUILauncher.tradeList;
        this.communityPoints = AdminUILauncher.communityPoints;
        try {
            FXMLLoader loader = new FXMLLoader(
                    AdminUIApp.class.getResource(
                            "/local/AdminUI.fxml"));

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