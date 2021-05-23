package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import sample.ExerciceLoader;
import sample.Main;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class OuvertureController extends Controller implements Initializable {

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
        exerciceLoader = main.exerciceLoader;
        if(exerciceLoader == null) System.err.println("wtf dude");
        pageLoader = main.pageLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pageLoader.setAnchorPane(anchorPane);
    }

    @FXML
    private void OnLoadExerciceButtonCLick(ActionEvent event){

        FileChooser chooser = new FileChooser();
        fileExercice = chooser.showOpenDialog(null);

        //Todo enregistrer quelque par fileExercice

        pageLoader.loadSubPage(PageLoader.PAG1PATH);

    }
    /* Defined in Ouverture COntroller
    @FXML
    private void OnLoadExerciceButtonCLick(ActionEvent event){
        FileChooser chooser = new FileChooser();
        fileExercice = chooser.showOpenDialog(null);

        displayFile(fileExercice);
        //initializeMediaVideo(fileExercice);
        //initializeMediaAudio(fileExercice);
    }

     */



}
