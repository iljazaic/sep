package sep.project.AdminGuiRender;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import sep.project.Controllers.AdminUIController;

public class AdminUIApp extends Application {

    private AdminUIController adminUIController;

    @Override
    public void start(Stage primaryStage) {

        //pulls from the launcher when it is assigned - since the application is only launched after assigning the static values keep it unchecked
        this.adminUIController = AdminUILauncher.adminUIController;
        try {
            FXMLLoader loader = new FXMLLoader(
                    AdminUIApp.class.getResource(
                            "/local/AdminUI.fxml"));

            loader.setController(this.adminUIController);

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