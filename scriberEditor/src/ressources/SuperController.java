package ressources;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import sample.GenerateurExercice;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class SuperController implements Initializable {

    @FXML
    protected AnchorPane ProgressAnchorPane;

    @FXML
    protected AnchorPane anchorPane;

    protected Main main;
    protected GenerateurExercice generateurExercice;
    protected PageLoader pageLoader;

    public SuperController(){
        main = Main.getInstance();
        generateurExercice = main.generateurExercice;
        pageLoader = main.pageLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane.setBottomAnchor(anchorPane, 0d);
        AnchorPane.setLeftAnchor(anchorPane, 0d);
        AnchorPane.setRightAnchor(anchorPane, 0d);
        AnchorPane.setTopAnchor(anchorPane, 0d);
        pageLoader.loadProgress(ProgressAnchorPane);
        VBox.setVgrow(anchorPane, Priority.ALWAYS);

    }





}
