package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPage4 implements Initializable {

    private Main main;
    private PageLoader pageLoader;


    public ControllerPage4(){
        main = Main.getInstance();
        pageLoader = main.pageLoader;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



}
