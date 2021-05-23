package ressources;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PageLoader {

    //Todo refaire cette class avec une liste static de touts les chemin des pages a chargé
    //Todo une méthode chargé les "sous pages " des interfaces
    //Todo une méthode chargé la page principales Index cette méthode va devoir set les élément principal par exemple l'anchorPane qui sert a chargé les sous page

    private static final String INDEXPATH = "Index.fxml";
    private static final String PAG1PATH = "page1.fxml";
    private static final String PAG2PATH = "page2.fxml";
    private static final String PAG3PATH = "page3.fxml";
    private static final String PAG4PATH = "page4.fxml";
    private static final String INTERFACEDETEST = "testInterface.fxml";



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

    public Parent loadIndex(){
        return load(INDEXPATH);
    }


}
