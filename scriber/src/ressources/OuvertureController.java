package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import sample.ExerciceLoader;
import sample.Main;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class OuvertureController extends Controller implements Initializable {

    @FXML
    Button openFile;

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
    }




}
