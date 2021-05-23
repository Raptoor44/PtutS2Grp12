package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ressources.Controller;
import ressources.ControllerIndex;
import ressources.PageLoader;

public final class Main extends Application {

    private static Main instance;


    public static Main getInstance()
    {
        return instance;
    }

    public Parent parent;
    public  Scene scene;
    public ControllerIndex controller;
    public PageLoader pageLoader;

    @Override
    public void start(Stage primaryStage) throws Exception{
        instance = this;
        pageLoader = new PageLoader();

        parent = pageLoader.loadIndex();
        primaryStage.setTitle("Scriber Editor");
        scene = new Scene(parent, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        controller = (ControllerIndex) pageLoader.getController();



    }


    public static void main(String[] args) {
        launch(args);
    }




}
