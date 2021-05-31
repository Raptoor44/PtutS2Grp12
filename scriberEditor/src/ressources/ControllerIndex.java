package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.Main;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerIndex extends SuperController implements Initializable {



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pageLoader.setAnchorPane(anchorPane);
    }

    @FXML
    public void onNextPageClick(ActionEvent event){
        pageLoader.loadSubPage(PageLoader.PAG1PATH);
    }


    @FXML
    public void voirLeDepot(ActionEvent event){
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/TLBail/PtutS2Grp12").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void voirLeTuto(ActionEvent event){
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/TLBail/PtutS2Grp12/wiki/Cr%C3%A9er-un-exercice").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }




}
