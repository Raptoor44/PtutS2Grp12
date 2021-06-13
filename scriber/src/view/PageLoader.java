package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PageLoader {

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
        return load(Layout.OUVERTURE.getPathToFile());
    }

    public void loadSubPage(String subPagePath){
        AnchorPane pane = null;
        pane = (AnchorPane) load(subPagePath);
        anchorPane.getChildren().setAll(pane);
    }



}
