package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller  implements Initializable {

    @FXML
    public Button button;

    @FXML
    public AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onNextButton(ActionEvent event) throws IOException{
        System.out.println("salut");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("page1.fxml"));
        anchorPane.getChildren().setAll(pane);
    }

    @FXML
    void onNextButton(MouseEvent event){
        System.out.println("salut toi");
    }




}
