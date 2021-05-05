package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();

        if(path != null){

            ExerciceLoader exerciceLoader = new ExerciceLoader();
            exerciceLoader.chargerUnExercice(path);

        }
    }

    private static String path;

    public static void main(String[] args) {
        for (String elem :
                args) {
            System.out.println("args : " + elem);

        }

        if (args.length > 0) {

            path = args[0];
            System.out.println(path);
        }

        launch(args);
    }
}
