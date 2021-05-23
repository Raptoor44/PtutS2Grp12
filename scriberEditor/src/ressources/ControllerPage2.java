package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import sample.GenerateurExercice;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPage2 implements Initializable {

    private Main main;
    private PageLoader pageLoader;
    private GenerateurExercice generateurExercice;

    private String mediafilePath;
    private String imagefilePath;

    public ControllerPage2(){
        main = Main.getInstance();
        pageLoader = main.pageLoader;
        generateurExercice = main.generateurExercice;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void onRetourClick(ActionEvent event){
        pageLoader.loadSubPage(PageLoader.PAG1PATH);
    }


    @FXML
    void OnImportMediaClick(ActionEvent event){
        FileChooser chooser = new FileChooser();
        mediafilePath = chooser.showOpenDialog(null).getAbsolutePath();
        //TODO indiquer que le media est bien charger sur l'interface  et faire une vérif que le chemin obtenue est pas null
    }

    @FXML
    void OnImportImageClick(ActionEvent event){
        FileChooser chooser = new FileChooser();
        imagefilePath = chooser.showOpenDialog(null).getAbsolutePath();
        //TODO indiquer que le media est bien charger sur l'interface  et faire une vérif que le chemin obtenue est pas null
    }

    @FXML
    void onNextPageClick(ActionEvent event){

        if(mediafilePath == null || mediafilePath.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !!");

            alert.setContentText("tu as selectionner aucun media !!");

            alert.showAndWait();
            return;
        }

        generateurExercice.setMediaFilePath(mediafilePath);
        if(imagefilePath != null && !imagefilePath.isEmpty()){
            generateurExercice.setImageFilePath(imagefilePath);
        }

        pageLoader.loadSubPage(PageLoader.PAG3PATH);

    }




}
