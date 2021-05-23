package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ressources.*;

import java.io.File;

public final class Main extends Application {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public ExerciceLoader exerciceLoader;
    public Parent parent;
    public Scene scene;
    public Controller controller;
    public EntrainementOuvertController ouvertureController;
    public ExerciseController exerciseController;
    public PageLoader pageLoader;

    @Override
    public void start(Stage primaryStage) throws Exception{
        instance = this;
        pageLoader = new PageLoader();
        exerciceLoader = new ExerciceLoader();
        parent = pageLoader.loadIndex();
        primaryStage.setTitle("Scriber");
        scene = new Scene(parent);
        primaryStage.setScene(scene);

        if(path != null){

            File exerciceFile = new File(path);
            controller.displayFile(exerciceFile);
        }

        primaryStage.show();




    }

    private static String path;

    public static void main(String[] args) {

        //TODO faire une verif que le premier argument est bien un chemin vers un exercice genre il finit par .exer
        if (args.length > 0) {

            path = args[0];
            System.out.println(path);
        }

        launch(args);
    }
}
