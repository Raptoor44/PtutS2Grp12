package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ressources.Controller;

public class Main extends Application {

    public static Parent parent;
    public static Scene scene;
    public static Controller controller;

    private static final String SAMPLEPATH = "../ressources/Index.fxml";
    private static final String PAG1PATH = "../ressources/page1.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ressources/Index.fxml"));
        parent = loader.load();
        primaryStage.setTitle("Scriber Editor");
        scene = new Scene(parent, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        controller = (Controller) loader.getController();

    }


    public static void main(String[] args) {
        launch(args);
    }




}
