package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import sample.GenerateurExercice;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPage3 extends  SuperController implements Initializable {


    @FXML
    private TextArea scriptTextArea;

    @FXML
    void onRetourClick(ActionEvent event){
        pageLoader.loadSubPage(PageLoader.PAG2PATH);
    }

    @FXML
    void onNextPageClick(ActionEvent event){
        if(scriptTextArea.getText() != null && !scriptTextArea.getText().isEmpty() && !scriptTextArea.getText().matches(".*\\w.*")){
            generateurExercice.setScriptExercice(scriptTextArea.getText());
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !!");

            alert.setContentText("le script est vide  !!");

            alert.showAndWait();
            return;

        }

        pageLoader.loadSubPage(PageLoader.PAG4PATH);

    }




}
