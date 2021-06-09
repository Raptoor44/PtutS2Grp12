package ressources;

import exercice.Entrainement;
import exercice.Evaluation;
import exercice.Exercice;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sample.*;

import java.io.File;
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class ExerciseController implements Initializable {

    @FXML
    Label exerciseName;

    @FXML
    Text consigne;

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

    @FXML
    Label mediaTime;

    @FXML
    Slider timeSlider;

    private ExerciceLoader exerciceLoader;
    private Main main;
    private MediaAfficheur mediaAfficheur;
    private Exercice exercice;
    private TextAfficheur textAfficheur;
    private File exerciceFile;
    private PageLoader pageLoader;
    private Score scoreEtudiant;

    public ExerciseController(){
        main = Main.getInstance();
        exerciceLoader = main.getExerciceLoader();
        if(exerciceLoader == null) System.err.println("wtf dude");
        exercice = main.getExercice();
        main.setExerciseController(this);
        mediaAfficheur = main.getMediaAfficheur();
        textAfficheur = new TextAfficheur(exercice, exercice.getOccultationCharacter());
        exerciceFile = main.getExerciseFile();
        pageLoader = main.getPageLoader();


        //Todo créer un Score Etudiant autre par
        scoreEtudiant = main.getScore();
        scoreEtudiant.startExercice();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mediaAfficheur.setMediaView(mediaView);
        displayFile(exercice);

        mediaAfficheur.initializeMediaAudio(exerciceFile);
        mediaAfficheur.initializeMediaVideo(exerciceFile);

        enterWords.setTextFormatter(new TextFormatter<Object>(change -> {
            if(change.getControlNewText().matches("|[A-za-z-_]+")){
                return change;
            }
            return null;
        }));

        enterWords.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if(keyEvent.getCode() == KeyCode.SPACE){
                enter();
            }
        });

        enterWords.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                enter();
            }
        });

        timeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(timeSlider.isValueChanging()){
                    mediaAfficheur.getMediaPlayer().seek(mediaAfficheur.getMedia().getDuration().multiply(timeSlider.getValue() / 100));
                }
            }
        });

        timeSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                timeSlider.setValueChanging(true);
                double value =(event.getX()/timeSlider.getWidth()*timeSlider.getMax());
                timeSlider.setValue(value);
                timeSlider.setValueChanging(false);
            }
        });

        mediaAfficheur.getMediaPlayer().currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                updateValues();
            }
        });

        mediaAfficheur.getMediaPlayer().setOnReady(new Runnable() {
            @Override
            public void run() {
                updateValues();
            }
        });

        //update du timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Main.getInstance().getExerciseController().updateLabel();
                        Main.getInstance().getExerciseController().timeEnd(scoreEtudiant.getTimePassed());
                    }
                });
            }
        }, 0, 50);

    }


    public void updateLabel(){
        time.setText(String.format("%d:%d",
                TimeUnit.MILLISECONDS.toMinutes(scoreEtudiant.getTimePassed()),
                TimeUnit.MILLISECONDS.toSeconds(scoreEtudiant.getTimePassed()) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(scoreEtudiant.getTimePassed()))
        ));
    }

    public void timeEnd(long l){
        if(exercice instanceof Evaluation){
            Evaluation evaluation = (Evaluation) exercice;
            if (l == TimeUnit.SECONDS.toMillis(evaluation.getTemps())){
                end();
            }
        }
    }

    private void enter(){
        if(enterWords.getText().isEmpty()){
            return;
        }

        if(exercice instanceof Entrainement){
            Entrainement entrainement = (Entrainement) exercice;

            if(entrainement.isReplacementAllowed()){
                textAfficheur.discoverWord(enterWords.getText(), 4);
                script.setText(textAfficheur.buildOccultedScript());

            } else {
                textAfficheur.discoverWord(enterWords.getText());
                script.setText(textAfficheur.buildOccultedScript());
            }
        }

        if(exercice instanceof Evaluation){
            Evaluation evaluation = (Evaluation) exercice;
            textAfficheur.discoverWord(enterWords.getText());
            script.setText(textAfficheur.buildOccultedScript());
        }

        score.setText(textAfficheur.getScore());
        enterWords.setText("");
    }

    protected void updateValues(){
        if(timeSlider != null && mediaTime != null){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Duration currentTime = mediaAfficheur.getMediaPlayer().getCurrentTime();
                    Duration duration = mediaAfficheur.getMedia().getDuration();
                    mediaTime.setText(formatTime(currentTime, duration));
                    timeSlider.setDisable(duration.isUnknown());
                    if(!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging()){
                        timeSlider.setValue(currentTime.divide(duration).toMillis() * 100);
                    }
                }
            });
        }
    }

    private String formatTime (Duration elapsed, Duration duration){
        int intElapsed = (int)Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int)Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 -
                    durationMinutes * 60;
            if (durationHours > 0) {
                return String.format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds,durationMinutes,
                        durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d", elapsedHours,
                        elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d",elapsedMinutes,
                        elapsedSeconds);
            }
        }
    }

    @FXML
    private void enterFXML(ActionEvent actionEvent){
        enter();
    }

    private void displayFile(Exercice exercice){
        consigne.setText(exercice.getConsigne());
        exerciseName.setText(exercice.getTitre());
        script.setText(textAfficheur.getOccultedString());
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



    @FXML
    private void endButton(ActionEvent actionEvent){
        end();
    }

    private void end(){
        main.getScore().setPoints(textAfficheur.getPoints());
        main.getScore().setNbWords(textAfficheur.getWords().size());
        pageLoader.loadSubPage(Layout.FIN_EXERCICE.getPathToFile());
    }
}
