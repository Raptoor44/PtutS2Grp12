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

public class ControllerPage1TitreEtConsigne extends SuperController implements Initializable {

    @FXML
    private TextField titre;

    @FXML
    private TextArea consigne;


    @FXML
    protected AnchorPane ProgressAnchorPane;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        if(generateurExercice.titreExercice != null)
            titre.setText(generateurExercice.titreExercice);
        if(generateurExercice.consigneExercice != null)
            consigne.setText(generateurExercice.consigneExercice);

    }

    @FXML
    void onNextPageClick(ActionEvent event){



        if(titre.getText() == null || titre.getText().isEmpty() || titre.getText().matches(" +")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !!");

            alert.setContentText("Un titre est obligatoire !!");

            alert.showAndWait();
            return;
        }
        if(consigne.getText() == null || consigne.getText().isEmpty() || consigne.getText().matches(" +")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !!");

            alert.setContentText("Une consigne est obligatoire !!");

            alert.showAndWait();
            return;
        }




        generateurExercice.titreExercice = titre.getText();
        generateurExercice.consigneExercice = consigne.getText();

        pageLoader.loadSubPage(PageLoader.PAG2PATH);
    }




}
