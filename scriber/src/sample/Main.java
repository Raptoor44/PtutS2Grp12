package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();


        ExerciceLoader exerciceLoader = new ExerciceLoader();


        if(path != null){

             exerciceLoader = new ExerciceLoader();
            exerciceLoader.chargerUnExercice(path);
            exerciceLoader.loadMediaData(exerciceLoader.chargerMediaDepuisExercice(path));

            System.out.println("title " + exerciceLoader.getTitle());
            System.out.println("album " + exerciceLoader.getAlbum());
            System.out.println("Artist " + exerciceLoader.getArtist());
            System.out.println("genre " + exerciceLoader.getGenre());
            System.out.println("Year " + exerciceLoader.getYear());


        }





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
