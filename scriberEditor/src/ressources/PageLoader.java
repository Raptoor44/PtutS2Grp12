package ressources;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PageLoader {

    private static final String INDEXPATH = "Index.fxml";
    public static final String PAG1PATH = "page1.fxml";
    public static final String PAG2PATH = "page2.fxml";
    public static final String PAG3PATH = "page3.fxml";
    public static final String PAG4PATH = "page4.fxml";
    private static final String INTERFACEDETEST = "testInterface.fxml";
    private static final String PROGRESSBAR = "partial/progressHeader.fxml";

    private AnchorPane anchorPane;

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
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
        return load(INDEXPATH);
    }

    public void loadSubPage(String subPagePath){

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
