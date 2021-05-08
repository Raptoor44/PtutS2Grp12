package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class Controller {

    @FXML
    public Button button;

    @FXML
    void onNextButton(ActionEvent event){
        System.out.println("salut");
    }

    @FXML
    void onNextButton(MouseEvent event){
        System.out.println("salut toi");
    }

}
