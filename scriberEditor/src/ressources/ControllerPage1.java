package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.GenerateurExercice;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPage1 extends SuperController implements Initializable {

    @FXML
    private TextField titre;

    @FXML
    private TextArea consigne;


    @FXML
    protected AnchorPane ProgressAnchorPane;



    @FXML
    void onRetourClick(ActionEvent event){
        pageLoader.loadIndex();
    }

    @FXML
    void onNextPageClick(ActionEvent event){



        if(titre.getText() == null || titre.getText().isEmpty() || !titre.getText().matches(".*\\w.*")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !!");

            alert.setContentText("Un titre est obligatoire !!");

            alert.showAndWait();
            return;
        }
        if(consigne.getText() == null || consigne.getText().isEmpty() || !consigne.getText().matches(".*\\w.*")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !!");

            alert.setContentText("Une consigne est obligatoire !!");

            alert.showAndWait();
            return;
        }




        generateurExercice.setTitreExercice(titre.getText());
        generateurExercice.setConsigneExercice(consigne.getText());

        pageLoader.loadSubPage(PageLoader.PAG2PATH);
    }




}
