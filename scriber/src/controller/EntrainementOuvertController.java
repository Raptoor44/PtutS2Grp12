package controller;

import exercice.Entrainement;
import exercice.Evaluation;
import exercice.Exercice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import view.Layout;
import view.PageLoader;
import model.ExerciceLoader;
import model.Main;
import model.MediaAfficheur;
import model.TextAfficheur;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class EntrainementOuvertController implements Initializable {

    @FXML
    AnchorPane anchorPane;

    @FXML
    Label exerciseTitle;

    @FXML
    Label exerciseInstruction;

    @FXML
    Label partialDiscoveringEnableOrTime;

    @FXML
    Label timeOrPartial;

    @FXML
    Label helpEnable;

    @FXML
    Label caseSensitive;

    @FXML
    Label exerciseWords;

    @FXML
    ImageView imageView;

    @FXML
    Label help;

    @FXML
    Button startExercise;

    @FXML
    MediaView mediaView;

    @FXML
    ImageView consigneMark;

    @FXML
    ImageView imageMark;

    @FXML
    ImageView nbMotsMark;

    @FXML
    ImageView timeOrPartialMark;

    @FXML
    ImageView sensitiveMark;

    @FXML
    ImageView helpMark;

    private ExerciceLoader exerciceLoader;
    private File fileExercice;
    private Main main;
    private PageLoader pageLoader;
    private MediaAfficheur mediaAfficheur;
    private Exercice exercice;

    public EntrainementOuvertController(){
        main = Main.getInstance();
        exerciceLoader = main.getExerciceLoader();
        pageLoader = main.getPageLoader();
        fileExercice = main.getExerciseFile();
        exercice = main.getExercice();
        mediaAfficheur = main.getMediaAfficheur();
        if(exerciceLoader == null) System.err.println("wtf dude");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane.setTopAnchor(anchorPane,.0);
        AnchorPane.setRightAnchor(anchorPane,.0);
        AnchorPane.setLeftAnchor(anchorPane,.0);
        AnchorPane.setBottomAnchor(anchorPane,.0);

        displayFile(exercice);
    }

    @FXML
    private void OnLoadExerciceButtonCLick(ActionEvent event){
        FileChooser chooser = new FileChooser();
        fileExercice = chooser.showOpenDialog(null);
        Exercice anotherExercise = exerciceLoader.chargerUnExercice(fileExercice.getPath());

        if(anotherExercise instanceof Entrainement){
            main.setExercice((Entrainement) anotherExercise);
        } else if (anotherExercise instanceof Evaluation){
            main.setExercice((Evaluation) anotherExercise);
        }

        displayFile(exercice);

    }

    public void displayFile(Exercice exercice){

        if(exercice != null){
            TextAfficheur textAfficheur = new TextAfficheur(exercice, exercice.getOccultationCharacter());
            exerciseWords.setText(String.valueOf(textAfficheur.getWords().size()));
        }

        exerciseInstruction.setText(exercice.getConsigne());
        exerciseTitle.setText(exercice.getTitre());
        caseSensitive.setText(exercice.isCaseSensitive() ? "Activée" : "Désactivée");

        if(exercice instanceof Entrainement){
            displayTraining(exercice);
        } else if(exercice instanceof Evaluation){
            displayTest(exercice);
        }

        if(mediaAfficheur.isAudio(fileExercice)){
            imageView.setImage(exerciceLoader.chargerImageDepuisExercice(fileExercice.getPath()));
            mediaView.setDisable(true);
        } else {
            imageView.setDisable(true);
            imageView.setVisible(false);

            javafx.scene.media.Media media = new Media(exerciceLoader.chargerMediaDepuisExercice(fileExercice.getPath()).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            mediaView.setMediaPlayer(mediaPlayer);

        }

    }

    private void displayTest(Exercice exercice){
        Evaluation evaluation = (Evaluation) exercice;
        timeOrPartial.setText("Temps :");
        startExercise.setText("Débuter l'exercice évalué");
        long tempAlouerEnms = TimeUnit.SECONDS.toMillis(((Evaluation) exercice).getTemps());
        partialDiscoveringEnableOrTime.setText(String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(tempAlouerEnms),
                TimeUnit.MILLISECONDS.toSeconds( tempAlouerEnms) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(tempAlouerEnms))
        ));
        help.setVisible(false);
        helpEnable.setVisible(false);
        helpMark.setDisable(true);
        helpMark.setVisible(false);
    }

    private void displayTraining(Exercice exercice){
        Entrainement entrainement = (Entrainement) exercice;
        startExercise.setText("Débuter l'exercice d'entrainement");
        timeOrPartial.setText("Remplacement partiel :");
        partialDiscoveringEnableOrTime.setText(entrainement.isAllowReplacement() ? "Activé" : "Désactivé");
        help.setText("Aide :");
        helpEnable.setText(entrainement.isHelpAllowed() ? "Activée" : "Désactivée");
    }

    @FXML
    public void changePage(){
        pageLoader.loadSubPage(Layout.REALISATION_EXERCICE.getPathToFile());
    }

    @FXML
    public void tipConsigne(){
        Tooltip tooltip = new Tooltip("Consigne donnée par le professeur.");
        Tooltip.install(consigneMark, tooltip);
    }

    @FXML
    public void tipImage(){
        Tooltip tooltip = new Tooltip("Image représentant le média que vous allez écouter.");
        Tooltip.install(imageMark, tooltip);
    }

    @FXML
    public void tipNbMots(){
        Tooltip tooltip = new Tooltip("Nombre de mots dans le script.");
        Tooltip.install(nbMotsMark, tooltip);
    }

    @FXML
    public void tipTimeOrPartial(){
        Tooltip tooltip = new Tooltip();
        if (exercice instanceof Entrainement){
            Entrainement entrainement = (Entrainement) exercice;
            if(entrainement.isAllowReplacement()){
                tooltip.setText("Vous pourrez entrer des débuts de mots (" + entrainement.getNbLetterMinimum() + " lettres minimum).");
            } else if(!entrainement.isAllowReplacement()){
                tooltip.setText("Vous ne pourrez entrer que des mots entiers.");
            }
        } else if (exercice instanceof Evaluation){
            tooltip.setText("Durée de l'évaluation.");
        }

        Tooltip.install(timeOrPartialMark, tooltip);
    }

    @FXML
    public void tipSensitive(){
        Tooltip tooltip = new Tooltip();
        if(exercice.isCaseSensitive()){
            tooltip.setText("Vous serez obligé de mettre des majuscules à vos mots.");
        } else if (!exercice.isCaseSensitive()){
            tooltip.setText("Vous pourrez écrire sans majuscule.");
        }
        Tooltip.install(sensitiveMark, tooltip);
    }

    @FXML
    public void tipHelp(){
        if(exercice instanceof Entrainement){
            Tooltip tooltip = new Tooltip();
            Entrainement entrainement = (Entrainement) exercice;
            if(entrainement.isHelpAllowed()){
                tooltip.setText("L'aide est autorisée, vous aurez un bouton où cliquer.");
            } else {
                tooltip.setText("L'aide est désactivée, malheureusement...");
            }
            Tooltip.install(helpMark, tooltip);
        }
    }
}
