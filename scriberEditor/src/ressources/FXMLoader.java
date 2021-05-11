package ressources;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class FXMLoader {

    private FXMLLoader fxmlLoader;

    public Controller getController() {
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
    

}
