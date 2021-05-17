package ressources;

import exercice.Entrainement;
import exercice.Evaluation;
import exercice.Exercice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import sample.ExerciceLoader;
import sample.Main;
import sample.TextAfficheur;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class EntrainementOuvertController extends Controller implements Initializable {
    @FXML
    Label exerciseName;

    @FXML
    Label exerciseTitle;

    @FXML
    Label exerciseInstruction;

    @FXML
    Label mediaAlbum;

    @FXML
    Button openFile;

    @FXML
    Label partialDiscoveringEnableOrTime;

    @FXML
    Label timeOrPartial;

    @FXML
    Label helpEnable;

    @FXML
    Label exerciseYear;

    @FXML
    Label caseSensitive;

    @FXML
    Label exerciseWords;

    @FXML
    ImageView imageView;

    @FXML
    Label image;

    @FXML
    Label title;

    @FXML
    Label album;

    @FXML
    Label annee;

    @FXML
    Label help;

    @FXML
    Button startExercise;

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

    @Override
    public void displayFile(File exercise){
        Exercice exercice = exerciceLoader.chargerUnExercice(exercise.getPath());

        exerciceLoader.loadMediaData(exerciceLoader.chargerMediaDepuisExercice(exercise.getPath()));
        //TODO passer les metadata en null au lieu d'une valeur par défaut.
        if(exerciceLoader.getTitle() == null){
            title.setVisible(false);
            exerciseName.setVisible(false);
            exerciseYear.setLayoutY(exerciseYear.getLayoutY() - 60);
            annee.setLayoutY(annee.getLayoutY() - 60);
            albumMedia.setLayoutY(albumMedia.getLayoutY() - 60);
            album.setLayoutY(album.getLayoutY() - 60);
        } else {
            exerciseName.setText(exerciceLoader.getTitle());
        }

        if(((Float) exerciceLoader.getYear()).toString() == null){
            exerciseYear.setVisible(false);
            annee.setVisible(false);
            albumMedia.setLayoutY(albumMedia.getLayoutY() - 60);
            album.setLayoutY(album.getLayoutY() - 60);
        } else {
            exerciseYear.setText(((Float) exerciceLoader.getYear()).toString());
        }

        if(exerciceLoader.getAlbum() == null){
            mediaAlbum.setVisible(false);
            album.setVisible(false);
        } else {
            mediaAlbum.setText(exerciceLoader.getAlbum());
        }


        if(exercice != null){
            TextAfficheur textAfficheur = new TextAfficheur(exercice.getScript(), "#");
            exerciseWords.setText(String.valueOf(textAfficheur.getWords().size()));
        }

        exerciseInstruction.setText(exercice.getConsigne());
        exerciseTitle.setText(exercice.getTitre());
        caseSensitive.setText(exercice.isSensibiliterCaseEstActiver() ? "Activé" : "Désactivé");

        if(exercice instanceof Entrainement){
            Entrainement entrainement = (Entrainement) exercice;
            startExercise.setText("Débuter l'exercice d'entrainement");
            timeOrPartial.setText("Remplacement partiel :");
            partialDiscoveringEnableOrTime.setText(entrainement.isRemplacementPartielEstAutoriser() ? "Oui" : "Non");
            help.setText("Aide :");
            helpEnable.setText(entrainement.isAideEstAutoriser() ? "Activé" : "Désactivé");

        } else if(exercice instanceof Evaluation){
            Evaluation evaluation = (Evaluation) exercice;
            timeOrPartial.setText("Temps :");
            startExercise.setText("Débuter l'exercice évalué");
            partialDiscoveringEnableOrTime.setText(((Float) evaluation.getTempAlouer()).toString());
            help.setVisible(false);
            helpEnable.setVisible(false);
            title.setLayoutY(title.getLayoutY() - 60);
            annee.setLayoutY(annee.getLayoutY() - 60);
            album.setLayoutY(album.getLayoutY() - 60);
            exerciseName.setLayoutY(exerciseName.getLayoutY() - 60);
            exerciseYear.setLayoutY(exerciseYear.getLayoutY() - 60);
            mediaAlbum.setLayoutY(mediaAlbum.getLayoutY() - 60);
        }

        if(exerciceLoader.chargerImageDepuisExercice(exercise.getPath()) != null){
                imageView.setImage(exerciceLoader.chargerImageDepuisExercice(exercise.getPath()));
                image.setText("Image :");
        }

    }



}
