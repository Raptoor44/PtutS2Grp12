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

public class ControllerPage3 implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextArea scriptTextArea;


    private Main main;
    private PageLoader pageLoader;
    private GenerateurExercice generateurExercice;



    public ControllerPage3(){
        main = Main.getInstance();
        pageLoader = main.pageLoader;
        generateurExercice = main.generateurExercice;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane.setBottomAnchor(anchorPane, 0d);
        AnchorPane.setLeftAnchor(anchorPane, 0d);
        AnchorPane.setRightAnchor(anchorPane, 0d);
        AnchorPane.setTopAnchor(anchorPane, 0d);

    }


    @FXML
    void onRetourClick(ActionEvent event){
        pageLoader.loadSubPage(PageLoader.PAG2PATH);
    }

    @FXML
    void onNextPageClick(ActionEvent event){
        if(scriptTextArea.getText() != null && !scriptTextArea.getText().isEmpty()){
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
