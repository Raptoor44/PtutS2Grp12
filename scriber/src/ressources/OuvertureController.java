package ressources;

import exercice.Entrainement;
import exercice.Evaluation;
import exercice.Exercice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import sample.ExerciceLoader;
import sample.Layout;
import sample.Main;
import sample.MediaAfficheur;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class OuvertureController implements Initializable {

    @FXML
    Button openFile;

    @FXML
    private AnchorPane anchorPane;

    private ExerciceLoader exerciceLoader;
    private File fileExercice;
    private Main main;
    private PageLoader pageLoader;

    public OuvertureController(){
        main = Main.getInstance();
        exerciceLoader = main.getExerciceLoader();
        if(exerciceLoader == null) System.err.println("wtf dude");
        pageLoader = main.getPageLoader();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pageLoader.setAnchorPane(anchorPane);
    }

    @FXML
    private void OnLoadExerciceButtonCLick(ActionEvent event){

        FileChooser chooser = new FileChooser();
        fileExercice = chooser.showOpenDialog(null);
        main.setExerciseFile(fileExercice);

        Exercice exercice = exerciceLoader.chargerUnExercice(fileExercice.getPath());

        if(exercice instanceof Entrainement){
            main.setExercice((Entrainement) exercice);
        } else if (exercice instanceof Evaluation){
            main.setExercice((Evaluation) exercice);
        }

        pageLoader.loadSubPage(Layout.DESCRIPTION_EXERCICE.getPathToFile());

    }


}
