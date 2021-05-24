package ressources;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PageLoader {



    private static final String INDEXPATH = "Ouverture.fxml";
    public static final String PAG1PATH = "Exercise.fxml";
    public static final String PAG2PATH = "DescriptionOuverture.fxml";
    public static final String PAG3PATH = "Realisation_Mode_Evaluation.fxml";
    private static final String INTERFACEDETEST = "sample.fxml";

    private AnchorPane anchorPane;

    private FXMLLoader fxmlLoader;

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

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

}
