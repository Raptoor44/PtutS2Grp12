package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.GenerateurExercice;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPage3Script extends  SuperController implements Initializable {


    @FXML
    private TextArea scriptTextArea;

    @FXML
    ImageView scriptMark;

    @FXML
    void onRetourClick(ActionEvent event){
        pageLoader.loadSubPage(PageLoader.PAG2PATH);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        if(generateurExercice.scriptExercice != null)
            scriptTextArea.setText(generateurExercice.scriptExercice);


    }

    @FXML
    void onNextPageClick(ActionEvent event){
        if(scriptTextArea.getText() != null && !scriptTextArea.getText().isEmpty() && !scriptTextArea.getText().matches(" +")){
            generateurExercice.scriptExercice = scriptTextArea.getText();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !!");

            alert.setContentText("Le script est vide.");

            alert.showAndWait();
            return;

        }

        pageLoader.loadSubPage(PageLoader.PAG4PATH);

    }

    @FXML
    public void scriptTip(){
        Tooltip tooltip = new Tooltip("Ici, entrez la transcription de votre média.");
        Tooltip.install(scriptMark, tooltip);
    }


}
