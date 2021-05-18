package ressources;

import exercice.Exercice;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import sample.ExerciceLoader;
import sample.Main;
import sample.TextAfficheur;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ExerciseController extends Controller implements Initializable {

    private ExerciceLoader exerciceLoader;
    private File fileExercice;
    private Media media;
    private MediaPlayer mediaPlayer;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exerciceLoader = Main.exerciceLoader;
        //initializeMedia();
        if(exerciceLoader == null) System.err.println("wtf dude");
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

    private void initializeMedia(File fileExercice){
        if(exerciceLoader.chargerImageDepuisExercice(fileExercice.getPath()) == null){
            media = exerciceLoader.chargerMediaDepuisExerciceMEDIA(fileExercice.getPath());
            mediaPlayer = new MediaPlayer(media);
            mediaView = new MediaView(mediaPlayer);

        } else if(exerciceLoader.chargerImageDepuisExercice(fileExercice.getPath()) != null){
            media = exerciceLoader.chargerMediaDepuisExerciceMEDIA(fileExercice.getPath());
            mediaPlayer = new MediaPlayer(media);
            mediaView = new MediaView(mediaPlayer);

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

}
