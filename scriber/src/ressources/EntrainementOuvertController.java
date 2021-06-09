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
import javafx.stage.FileChooser;
import sample.*;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class EntrainementOuvertController implements Initializable {

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
    Label image;

    @FXML
    Label help;

    @FXML
    Button startExercise;

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
        if(exerciceLoader == null) System.err.println("wtf dude");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        caseSensitive.setText(exercice.isCaseSensitive() ? "Activé" : "Désactivé");

        if(exercice instanceof Entrainement){
            displayTraining(exercice);
        } else if(exercice instanceof Evaluation){
            displayTest(exercice);
        }

        if(exerciceLoader.chargerImageDepuisExercice(fileExercice.getPath()) != null){
                imageView.setImage(exerciceLoader.chargerImageDepuisExercice(fileExercice.getPath()));
                image.setText("Image :");
        }

    }

    private void displayTest(Exercice exercice){
        Evaluation evaluation = (Evaluation) exercice;
        timeOrPartial.setText("Temps :");
        startExercise.setText("Débuter l'exercice évalué");
        partialDiscoveringEnableOrTime.setText(((Integer) evaluation.getTemps()).toString());
        help.setVisible(false);
        helpEnable.setVisible(false);
    }

    private void displayTraining(Exercice exercice){
        Entrainement entrainement = (Entrainement) exercice;
        startExercise.setText("Débuter l'exercice d'entrainement");
        timeOrPartial.setText("Remplacement partiel :");
        partialDiscoveringEnableOrTime.setText(entrainement.isAllowReplacement() ? "Oui" : "Non");
        help.setText("Aide :");
        helpEnable.setText(entrainement.isHelpAllowed() ? "Activé" : "Désactivé");
    }

    @FXML
    public void changePage(){
        pageLoader.loadSubPage(Layout.REALISATION_EXERCICE.getPathToFile());
    }

}
