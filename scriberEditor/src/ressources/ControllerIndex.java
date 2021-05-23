package ressources;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sample.Main;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerIndex implements Initializable {

    private Main main;

    public ControllerIndex(){
        main = Main.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void onNextPageClick(ActionEvent event){



    }


}
