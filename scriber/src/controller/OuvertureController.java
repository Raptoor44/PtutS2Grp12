package controller;

import exercice.Entrainement;
import exercice.Evaluation;
import exercice.Exercice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import view.PageLoader;
import model.ExerciceLoader;
import view.Layout;
import model.Main;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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
        main.setOuvertureController(this);
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

        loadExercice(fileExercice);

    }


    public void  loadExercice(File exercieFile){

        fileExercice = exercieFile;
        main.setExerciseFile(exercieFile);

        Exercice exercice = exerciceLoader.chargerUnExercice(exercieFile.getPath());

        if(exercice instanceof Entrainement){
            main.setExercice((Entrainement) exercice);
        } else if (exercice instanceof Evaluation){
            main.setExercice((Evaluation) exercice);
        }

        pageLoader.loadSubPage(Layout.DESCRIPTION_EXERCICE.getPathToFile());

    }

    @FXML
    public void voirLeTuto(ActionEvent event){
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/TLBail/PtutS2Grp12/wiki/Cr%C3%A9er-un-exercice").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void voirLeDepot(ActionEvent event){
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/TLBail/PtutS2Grp12").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


}
