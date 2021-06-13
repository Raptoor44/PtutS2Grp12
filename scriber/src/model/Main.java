package model;

import controller.Controller;
import controller.EntrainementOuvertController;
import controller.ExerciseController;
import controller.OuvertureController;
import exercice.Entrainement;
import exercice.Evaluation;
import exercice.Exercice;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.PageLoader;

import java.io.File;

public final class Main extends Application {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    private ExerciceLoader exerciceLoader;
    private Parent parent;
    private Scene scene;
    private OuvertureController ouvertureController;
    private ExerciseController exerciseController;
    private PageLoader pageLoader;
    private MediaAfficheur mediaAfficheur;
    private File exerciseFile;
    private Exercice exercice;
    private Score score;
    private TextAfficheur textAfficheur;

    @Override
    public void start(Stage primaryStage) throws Exception{
        instance = this;
        pageLoader = new PageLoader();
        exerciceLoader = new ExerciceLoader();
        mediaAfficheur = new MediaAfficheur();
        score = new Score();

        parent = pageLoader.loadIndex();
        primaryStage.setTitle("Scriber");
        scene = new Scene(parent);
        scene.getStylesheets().add("ressources/Style.css");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(
                new Image(Main.class.getResourceAsStream( "/ressources/img/scriberIcon.png" )));

        primaryStage.setMaximized(true);

        if(path != null){
            File exerciceFile = new File(path);

            ouvertureController.loadExercice(exerciceFile);

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

    public void setTextAfficheur(TextAfficheur textAfficheur) {
        this.textAfficheur = textAfficheur;
    }

    public TextAfficheur getTextAfficheur() {
        return textAfficheur;
    }

    public void setOuvertureController(OuvertureController ouvertureController) {
        this.ouvertureController = ouvertureController;
    }
}
