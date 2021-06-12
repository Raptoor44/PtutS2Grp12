package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PageLoader {

    private static final String INDEXPATH = "Index.fxml";
    public static final String PAG1PATH = "page1TitreEtConsigne.fxml";
    public static final String PAG2PATH = "page2Media.fxml";
    public static final String PAG3PATH = "page3Script.fxml";
    public static final String PAG4PATH = "page4OptionEtExport.fxml";
    private static final String INTERFACEDETEST = "testInterface.fxml";
    private static final String PROGRESSBAR = "partial/progressHeader.fxml";

    private AnchorPane anchorPane;

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    private String lastPagePath;

    public String getLastPagePath() {
        return lastPagePath;
    }

    private FXMLLoader fxmlLoader;

    public Object getController() {
        return fxmlLoader.getController();
    }

    public Parent load(String fileName){

        try {
            fxmlLoader = new FXMLLoader(getClass().getResource(fileName));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Parent loadIndex(){
        lastPagePath = INDEXPATH;
        return load(INDEXPATH);
    }

    public void loadSubPage(String subPagePath){
        lastPagePath = subPagePath;
        AnchorPane pane = null;
        pane = (AnchorPane) load(subPagePath);
        anchorPane.getChildren().setAll(pane);

    }

    public void loadProgress(AnchorPane progressPane){
        AnchorPane pane = null;
        pane = (AnchorPane) load(PROGRESSBAR);
        progressPane.getChildren().setAll(pane);

    }

}
