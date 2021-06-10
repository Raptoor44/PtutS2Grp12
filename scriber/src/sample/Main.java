package sample;

import exercice.Entrainement;
import exercice.Evaluation;
import exercice.Exercice;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ressources.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public final class Main extends Application {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    private ExerciceLoader exerciceLoader;
    private Parent parent;
    private Scene scene;
    private Controller controller;
    private EntrainementOuvertController ouvertureController;
    private ExerciseController exerciseController;
    private PageLoader pageLoader;
    private MediaAfficheur mediaAfficheur;
    private File exerciseFile;
    private Exercice exercice;
    private Score score;

    @Override
    public void start(Stage primaryStage) throws Exception{
        instance = this;
        pageLoader = new PageLoader();
        exerciceLoader = new ExerciceLoader();
        mediaAfficheur = new MediaAfficheur();
        score = new Score();

        parent = pageLoader.loadIndex();
        primaryStage.setTitle("Scriber");
        primaryStage.getIcons().add(new Image(new File("src/ressources/img/scriberIcon.png").toURI().toString()));
        scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);

        if(path != null){
            File exerciceFile = new File(path);
            controller.displayFile(exerciceFile);
        }

        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

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

    public Controller getController() {
        return controller;
    }

    public EntrainementOuvertController getOuvertureController() {
        return ouvertureController;
    }

    public ExerciceLoader getExerciceLoader() {
        return exerciceLoader;
    }

    public ExerciseController getExerciseController() {
        return exerciseController;
    }

    public File getExerciseFile() {
        return exerciseFile;
    }

    public MediaAfficheur getMediaAfficheur() {
        return mediaAfficheur;
    }

    public PageLoader getPageLoader() {
        return pageLoader;
    }

    public void setExerciseController(ExerciseController exerciseController) {
        this.exerciseController = exerciseController;
    }

    public void setExerciseFile(File exerciseFile) {
        this.exerciseFile = exerciseFile;
    }

    public void setExercice(Entrainement entrainement){
        this.exercice = entrainement;
    }

    public void setExercice(Evaluation evaluation){
        this.exercice = evaluation;
    }

    public Exercice getExercice() {
        return exercice;
    }

    public Score getScore() {
        return score;
    }



}
