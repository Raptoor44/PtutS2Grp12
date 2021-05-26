package ressources.partial;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import ressources.PageLoader;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class progressController implements Initializable {

    @FXML
    private ImageView imageViewArrow1;


    private Main main;
    private PageLoader pageLoader;

    public progressController(){
        main = Main.getInstance();
        pageLoader = main.pageLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        switch (pageLoader.getLastPagePath()){
            case PageLoader.PAG1PATH:
                System.out.println("chargé les icone de la page1");
                break;
            case PageLoader.PAG2PATH:
                System.out.println("chargé les icone de la page2");
                break;
            case PageLoader.PAG3PATH:
                System.out.println("chargé les icone de la page3");
                break;
            case PageLoader.PAG4PATH:
                System.out.println("chargé les icone de la page4");
                break;
            default:
                System.out.println(pageLoader.getLastPagePath());
        }
    }
}
