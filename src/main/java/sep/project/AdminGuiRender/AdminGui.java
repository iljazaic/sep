package sep.project.AdminGuiRender;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AdminGui extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button button1 = new Button("Button One");
        Button button2 = new Button("Button Two");
        Button button3 = new Button("Button Three");

        button1.setOnAction(event -> System.out.println("Button One Clicked!"));

        HBox root = new HBox(10); 
        root.setPadding(new Insets(20));

        root.getChildren().addAll(button1, button2, button3);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Three Buttons");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}