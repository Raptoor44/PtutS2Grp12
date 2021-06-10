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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sample.*;

import java.io.File;
import java.net.URL;
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
    TextArea script;

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

    @FXML
    Button end;

    @FXML
    ImageView imageView;

    private ExerciceLoader exerciceLoader;
    private Main main;
    private MediaAfficheur mediaAfficheur;
    private Exercice exercice;
    private TextAfficheur textAfficheur;
    private File exerciceFile;
    private PageLoader pageLoader;
    private Score scoreEtudiant;
    private Timer timer;

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

        scoreEtudiant = main.getScore();
        scoreEtudiant.startExercice();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mediaAfficheur.setMediaView(mediaView);
        displayFile(exercice);

        if(mediaAfficheur.isAudio(exerciceFile)){
            mediaAfficheur.initializeMediaAudio(exerciceFile);
            imageView.setImage(mediaAfficheur.getImage());
        } else if (mediaAfficheur.isVideo(exerciceFile)) {
            mediaAfficheur.initializeMediaVideo(exerciceFile);
        }


        script.setEditable(false);

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

        timeSlider.valueProperty().addListener(observable -> {
            if(timeSlider.isValueChanging()){
                mediaAfficheur.getMediaPlayer().seek(mediaAfficheur.getMedia().getDuration().multiply(timeSlider.getValue() / 100));
            }
        });

        timeSlider.setOnMouseClicked(event -> {
            timeSlider.setValueChanging(true);
            double value =(event.getX()/timeSlider.getWidth()*timeSlider.getMax());
            timeSlider.setValue(value);
            timeSlider.setValueChanging(false);
        });

        mediaAfficheur.getMediaPlayer().currentTimeProperty().addListener(observable -> updateValues());

        mediaAfficheur.getMediaPlayer().setOnReady(this::updateValues);

        //update du timer
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    Main.getInstance().getExerciseController().updateLabel();
                    Main.getInstance().getExerciseController().timeEnd(scoreEtudiant.getTimePassed());
                });
            }
        }, 0, 50);

        if(exercice instanceof Entrainement){
            Entrainement entrainement = (Entrainement) exercice;
            if(entrainement.isAllowDisplayingSolution()){
                end.setText("Solution");
            } else {
                end.setDisable(true);
                end.setVisible(false);
            }

            if (!entrainement.isAllowDisplayNbWordDiscover()){
                score.setVisible(false);
            }
        }

    }


    private void updateLabel(){
        time.setText(String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(scoreEtudiant.getTimePassed()),
                TimeUnit.MILLISECONDS.toSeconds(scoreEtudiant.getTimePassed()) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(scoreEtudiant.getTimePassed()))
        ));
    }

    private void timeEnd(long l){
        if(exercice instanceof Evaluation){
            Evaluation evaluation = (Evaluation) exercice;
            if (l >= evaluation.getTemps() * 1000L){
                end();
            }
        }
    }

    private void enter(){
        if(enterWords.getText().isEmpty()){
            return;
        }

        String word = enterWords.getText();

        if(!exercice.isCaseSensitive()){
            word = word.toLowerCase();
        }

        if(exercice instanceof Entrainement){
            Entrainement entrainement = (Entrainement) exercice;

            if(entrainement.isAllowReplacement()){
                textAfficheur.discoverWord(word, entrainement.getNbLetterMinimum());
                script.setText(textAfficheur.buildOccultedScript());

            } else {
                textAfficheur.discoverWord(word);
                script.setText(textAfficheur.buildOccultedScript());
            }
        }

        if(exercice instanceof Evaluation){
            Evaluation evaluation = (Evaluation) exercice;
            textAfficheur.discoverWord(word);
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
        timer.cancel();
        scoreEtudiant.stopTime();
        main.getMediaAfficheur().closeMedia();
        main.getScore().setPoints(textAfficheur.getPoints());
        main.getScore().setNbWords(textAfficheur.getWords().size());
        main.getScore().setAnswer(textAfficheur.buildOccultedScript());
        pageLoader.loadSubPage(Layout.FIN_EXERCICE.getPathToFile());
    }
}
