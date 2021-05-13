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
import ressources.FXMLoader;

public class Main extends Application {

    public static Parent parent;
    public static Scene scene;
    public static Controller controller;


    private static final String INDEXPATH = "Index.fxml";
    private static final String PAG1PATH = "page1.fxml";
    private static final String INTERFACEDETEST = "testInterface.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLoader fxmLoader = new FXMLoader();
        parent = fxmLoader.load(INDEXPATH);
        primaryStage.setTitle("Scriber Editor");
        scene = new Scene(parent, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        controller = fxmLoader.getController();



    }


    public static void main(String[] args) {
        launch(args);
    }




}
