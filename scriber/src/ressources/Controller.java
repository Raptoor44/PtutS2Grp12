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
    public Label titreMedia;
    @FXML
    public Label albumMedia;
    @FXML
    public Label annerMedia;
    @FXML
    public ImageView imageView;


    private ExerciceLoader exerciceLoader;
    private File fileExercice;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exerciceLoader = Main.exerciceLoader;
        if(exerciceLoader == null) System.err.println("wtf dude");

    }

    @FXML
    private void OnLoadExerciceButtonCLick(ActionEvent event){
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

    public void displayFile(File ExerciceFile){
        Exercice exercice =  exerciceLoader.chargerUnExercice(ExerciceFile.getPath());
        exerciceInfo.getChildren().clear();
        exerciceInfo.getChildren().add(new Text(exercice.toString()));
        if(exerciceLoader.chargerImageDepuisExercice(ExerciceFile.getPath()) != null){
            imageView.setImage(exerciceLoader.chargerImageDepuisExercice(ExerciceFile.getPath()) );
        }

    }

}
