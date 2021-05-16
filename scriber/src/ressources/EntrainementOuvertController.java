package ressources;

import exercice.Entrainement;
import exercice.Exercice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import sample.ExerciceLoader;
import sample.Main;
import sample.TextAfficheur;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class EntrainementOuvertController extends Controller implements Initializable {
    @FXML
    Text exerciseName;

    @FXML
    Label exerciseTitle;

    @FXML
    Label exerciseInstruction;

    @FXML
    Label mediaAlbum;

    @FXML
    Button openFile;

    @FXML
    Label partialDiscoveringEnable;

    @FXML
    Label exerciseYear;

    @FXML
    Label caseSensitive;

    @FXML
    Label exerciseWords;

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
        Entrainement exercice = (Entrainement) exerciceLoader.chargerUnExercice(exercise.getPath());

        //exerciceInfo.getChildren().clear();
        //exerciceInfo.getChildren().add(new Text(exercice.toString()));

        exerciceLoader.loadMediaData(exerciceLoader.chargerMediaDepuisExercice(exercise.getPath()));
        //exerciseName.setText(exerciceLoader.getTitle());
        //mediaAlbum.setText(exerciceLoader.getAlbum());
        //exerciseYear.setText( ((Float) exerciceLoader.getYear()).toString());

        if(exercice != null){
            TextAfficheur textAfficheur = new TextAfficheur(exercice.getScript(), "#");
            exerciseWords.setText(String.valueOf(textAfficheur.getWords().size()));
        }

        exerciseInstruction.setText(exercice.getConsigne());
        exerciseTitle.setText(exercice.getTitre());
        partialDiscoveringEnable.setText(exercice.getRemplacementPartielEstAutoriser() ? "Oui" : "Non");
        caseSensitive.setText(exercice.getSensibiliterCaseEstActiver() ? "Activé" : "Désactivé");

        if(exerciceLoader.chargerImageDepuisExercice(exercise.getPath()) != null){
            imageView.setImage(exerciceLoader.chargerImageDepuisExercice(exercise.getPath()));
        }

    }



}
