package ressources;

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
    private MediaAfficheur mediaAfficheur;

    public OuvertureController(){
        main = Main.getInstance();
        exerciceLoader = main.exerciceLoader;
        if(exerciceLoader == null) System.err.println("wtf dude");
        pageLoader = main.pageLoader;
        mediaAfficheur = main.mediaAfficheur;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pageLoader.setAnchorPane(anchorPane);
    }

    @FXML
    private void OnLoadExerciceButtonCLick(ActionEvent event){

        FileChooser chooser = new FileChooser();
        fileExercice = chooser.showOpenDialog(null);
        main.exerciseFile = fileExercice;

        pageLoader.loadSubPage(Layout.DESCRIPTION_EXERCICE.getPathToFile());

    }


}
