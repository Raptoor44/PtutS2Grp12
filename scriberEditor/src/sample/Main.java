package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ressources.Controller;
import ressources.FXMLoader;

public class Main extends Application {

    public static Parent parent;
    public static Scene scene;
    public static Controller controller;


    private static final String INDEXPATH = "../ressources/Index.fxml";
    private static final String PAG1PATH = "../ressources/page1.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{
        parent = new FXMLoader().load("Index.fxml");
        primaryStage.setTitle("Scriber Editor");
        scene = new Scene(parent, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }




}
