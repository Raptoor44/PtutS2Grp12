package ressources;

import exercice.Exercice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import sample.ExerciceLoader;
import sample.Main;
import sample.MediaAfficheur;
import sample.TextAfficheur;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ExerciseController implements Initializable {

    @FXML
    Label exerciseName;

    @FXML
    Text consigne; // •

    @FXML
    Text time;

    @FXML
    Text score;

    @FXML
    Label script;

    @FXML
    MediaView mediaView;

    @FXML
    Button pause;

    @FXML
    Button start;

    @FXML
    Button validate;

    @FXML
    TextField enterWords;


    private ExerciceLoader exerciceLoader;
    private Main main;
    private MediaAfficheur mediaAfficheur;

    public ExerciseController(){
        main = Main.getInstance();
        exerciceLoader = main.exerciceLoader;
        if(exerciceLoader == null) System.err.println("wtf dude");
        main.exerciseController = this;
        mediaAfficheur = main.mediaAfficheur;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mediaAfficheur.setMediaView(mediaView);
    }


    public void displayFile(File fileExercice){
        Exercice exercice = exerciceLoader.chargerUnExercice(fileExercice.getPath());
        //TODO ajouter une variable pour le char d'occultation
        TextAfficheur textAfficheur = new TextAfficheur(exercice, exercice.getOccultationCharacter());

        consigne.setText(exercice.getConsigne());
        exerciseName.setText(exercice.getTitre());
        score.setText(textAfficheur.getScore());
        script.setText(textAfficheur.getOccultedString());

    }

    @FXML
     void pause(ActionEvent actionEvent){
        mediaAfficheur.pauseMedia();
    }

    @FXML
    void start(ActionEvent actionEvent){
        mediaAfficheur.playMedia();
    }

    /* Defined in Ouverture COntroller

    @FXML
    private void OnLoadExerciceButtonCLick(ActionEvent event){
        FileChooser chooser = new FileChooser();
        fileExercice = chooser.showOpenDialog(null);

        displayFile(fileExercice);
        initializeMediaVideo(fileExercice);
        initializeMediaAudio(fileExercice);
    }
     */

}
