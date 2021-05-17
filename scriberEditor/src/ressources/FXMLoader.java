package ressources;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class FXMLoader {

    //Todo refaire cette class avec une liste static de touts les chemin des pages a chargé
    //Todo une méthode chargé les "sous pages " des interfaces
    //Todo une méthode chargé la page principales Index cette méthode va devoir set les élément principal par exemple l'anchorPane qui sert a chargé les sous page

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
