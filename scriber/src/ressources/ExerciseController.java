package ressources;

import exercice.Exercice;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import sample.ExerciceLoader;
import sample.Main;
import sample.TextAfficheur;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExerciseController extends Controller implements Initializable {

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
    private File fileExercice;
    private Media media;
    private MediaPlayer mediaPlayer;
    private Main main;

    public ExerciseController(){
        main = Main.getInstance();
        exerciceLoader = main.exerciceLoader;
        if(exerciceLoader == null) System.err.println("wtf dude");
        main.exerciseController = this;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @Override
    public void displayFile(File fileExercice){
        Exercice exercice = exerciceLoader.chargerUnExercice(fileExercice.getPath());
        //TODO ajouter une variable pour le char d'occultation
        TextAfficheur textAfficheur = new TextAfficheur(exercice, "#");

        consigne.setText(exercice.getConsigne());
        exerciseName.setText(exercice.getTitre());
        score.setText(textAfficheur.getScore());
        script.setText(textAfficheur.getOccultedString());


    }

    private void initializeMediaVideo(File fileExercice){
       if(exerciceLoader.chargerMediaDepuisExercice(fileExercice.getPath()) != null){
            media = new Media(exerciceLoader.chargerMediaDepuisExercice(fileExercice.getPath()).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaView.setMediaPlayer(mediaPlayer);
        }
    }

    private void initializeMediaAudio(File fileExercice){
        if(exerciceLoader.chargerMediaDepuisExercice(fileExercice.getPath()) != null && exerciceLoader.chargerImageDepuisExercice(fileExercice.getPath()) != null){
            media = new Media(exerciceLoader.chargerMediaDepuisExercice(fileExercice.getPath()).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaView.setMediaPlayer(mediaPlayer);

        }
    }

    @FXML
    private void pause(){
        pause.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                mediaPlayer.pause();
            }
        });
    }

    @FXML
    private void start(){
        start.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                mediaPlayer.play();
            }
        });
    }

    @FXML
    private void OnLoadExerciceButtonCLick(ActionEvent event){
        FileChooser chooser = new FileChooser();
        fileExercice = chooser.showOpenDialog(null);

        displayFile(fileExercice);
        initializeMediaVideo(fileExercice);
        initializeMediaAudio(fileExercice);
    }

}
