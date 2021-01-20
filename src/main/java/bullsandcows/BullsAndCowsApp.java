package bullsandcows;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BullsAndCowsApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Bull and Cows Game");
    //    var root = new FlowPane();
    //    var button = new Button("Press me!");
    //    button.setOnAction((x) -> {
    //        System.out.println("Hello!");   // действие при нажатии кнопки
    //    });
    //    var label = new Label("Hello World!");
    //    root.getChildren().addAll(button, label);
        Pane root = FXMLLoader.load(getClass().getResource("/bullsandcows/main.fxml"));
        var scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
