package ressources;

import exercice.Entrainement;
import exercice.Evaluation;
import exercice.Exercice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
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
    private Exercice exercice;
    private TextAfficheur textAfficheur;

    public ExerciseController(){
        main = Main.getInstance();
        exerciceLoader = main.getExerciceLoader();
        if(exerciceLoader == null) System.err.println("wtf dude");
        exercice = main.getExercice();
        main.setExerciseController(this);
        mediaAfficheur = main.getMediaAfficheur();
        textAfficheur = new TextAfficheur(exercice, exercice.getOccultationCharacter());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mediaAfficheur.setMediaView(mediaView);
        displayFile(exercice);

        enterWords.setTextFormatter(new TextFormatter<Object>(change -> {
            if(change.getControlNewText().matches("|[A-za-z-_]+")){
                return change;
            }
            return null;
        }));

        enterWords.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if(keyEvent.getCode() == KeyCode.SPACE){
                enter(enterWords.getText());
            }
        });

    }

    private void enter(String str){
        if(enterWords.getText().isEmpty()){
            return;
        }

        if(exercice instanceof Entrainement){
            Entrainement entrainement = (Entrainement) exercice;

            if(entrainement.isReplacementAllowed()){
                textAfficheur.discoverWord(str, 4);
                script.setText(textAfficheur.buildOccultedScript());
                displayScore();

            } else {
                textAfficheur.discoverWord(str);
                script.setText(textAfficheur.buildOccultedScript());
                displayScore();
            }
        }

        if(exercice instanceof Evaluation){
            Evaluation evaluation = (Evaluation) exercice;
            textAfficheur.discoverWord(str);
            script.setText(textAfficheur.buildOccultedScript());
            displayScore();
        }

        enterWords.setText("");

    }

    private void displayFile(Exercice exercice){
        consigne.setText(exercice.getConsigne());
        exerciseName.setText(exercice.getTitre());
        script.setText(textAfficheur.getOccultedString());
        displayScore();

        if(exercice instanceof Evaluation){

        } else if(exercice instanceof Exercice){

        }

    }

    private void displayScore(){
        score.setText(textAfficheur.getScore());
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
