package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerIndex implements Initializable {

    private Main main;
    private PageLoader pageLoader;


    @FXML
    private AnchorPane anchorPane;

    public ControllerIndex(){
        main = Main.getInstance();
        pageLoader = main.pageLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pageLoader.setAnchorPane(anchorPane);
    }

    @FXML
    public void onNextPageClick(ActionEvent event){
        pageLoader.loadSubPage(PageLoader.PAG1PATH);


    }




}
