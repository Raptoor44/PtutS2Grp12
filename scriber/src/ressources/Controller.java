package ressources;

import exercice.Exercice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import sample.ExerciceLoader;
import sample.Main;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public TextFlow exerciceInfo;

    @FXML
    public ImageView imageView;


    private ExerciceLoader exerciceLoader;
    private File fileExercice;
    private Main main;

    public Controller(){
        main = Main.getInstance();
        exerciceLoader = main.getExerciceLoader();
        if(exerciceLoader == null) System.err.println("wtf dude");


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onLoadExerciceButtonCLick(ActionEvent event){
        FileChooser chooser = new FileChooser();
        fileExercice = chooser.showOpenDialog(null);

        displayFile(fileExercice);

    }

    @FXML
    private void OnOpenMediaClick(ActionEvent event){

        File mediaFile = exerciceLoader.chargerMediaDepuisExercice(fileExercice.getPath());

        try {
            Desktop.getDesktop().open(mediaFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void displayFile(File exerciceFile){
        Exercice exercice =  exerciceLoader.chargerUnExercice(exerciceFile.getPath());
        exerciceInfo.getChildren().clear();
        exerciceInfo.getChildren().add(new Text(exercice.toString()));
        if(exerciceLoader.chargerImageDepuisExercice(exerciceFile.getPath()) != null){
            imageView.setImage(exerciceLoader.chargerImageDepuisExercice(exerciceFile.getPath()) );
        }

    }

}
