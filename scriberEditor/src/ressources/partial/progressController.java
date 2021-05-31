package ressources.partial;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import ressources.PageLoader;
import sample.Main;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class progressController implements Initializable {

    @FXML
    ImageView imageViewArrow1;

    @FXML
    ImageView imageViewArrow2;

    @FXML
    ImageView imageViewArrow3;

    @FXML
    ImageView imageViewArrow4;


    private Main main;
    private PageLoader pageLoader;

    private static final Image arrowFullImage = new Image("/images/arrowFull.png");
    private static final Image arrowImage = new Image("/images/arrow.png");

    public progressController(){
        main = Main.getInstance();
        pageLoader = main.pageLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println(new File("/images/arrowFull.png").toURI().toString());

        switch (pageLoader.getLastPagePath()){
            case PageLoader.PAG1PATH:
                System.out.println("chargé les icone de la page1");
                imageViewArrow1.setImage(arrowFullImage);
                imageViewArrow2.setImage(arrowImage);
                imageViewArrow3.setImage(arrowImage);
                imageViewArrow4.setImage(arrowImage);
                break;
            case PageLoader.PAG2PATH:
                System.out.println("chargé les icone de la page2");
                imageViewArrow1.setImage(arrowImage);
                imageViewArrow2.setImage(arrowFullImage);
                imageViewArrow3.setImage(arrowImage);
                imageViewArrow4.setImage(arrowImage);

                break;
            case PageLoader.PAG3PATH:
                System.out.println("chargé les icone de la page3");
                imageViewArrow1.setImage(arrowImage);
                imageViewArrow2.setImage(arrowImage);
                imageViewArrow3.setImage(arrowFullImage);
                imageViewArrow4.setImage(arrowImage);

                break;
            case PageLoader.PAG4PATH:
                System.out.println("chargé les icone de la page4");
                imageViewArrow1.setImage(arrowImage);
                imageViewArrow2.setImage(arrowImage);
                imageViewArrow3.setImage(arrowImage);
                imageViewArrow4.setImage(arrowFullImage);

                break;
            default:
                System.out.println(pageLoader.getLastPagePath());
        }


    }

    @FXML
    void startAnimation(MouseEvent event){
        if(event.getSource() instanceof ImageView){
            ImageView imageView = (ImageView) event.getSource();
            /*
            ColorAdjust monochrome = new ColorAdjust();
            monochrome.setSaturation(0.0);

            Blend blush = new Blend(
                    BlendMode.MULTIPLY,
                    monochrome,
                    new ColorInput(
                            0,
                            0,
                            50,
                            50,
                            Color.RED
                    )
            );

            imageView.setEffect(blush);
             */
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-0.5);
            imageView.setEffect(colorAdjust);
            RotateTransition rt = new RotateTransition(Duration.millis(200), (Node) event.getSource());
            rt.setToAngle(-90);
            rt.setInterpolator(Interpolator.LINEAR);
            rt.play();
        }
    }

    @FXML
    void endAnimation(MouseEvent event){


        if(event.getSource() instanceof ImageView){
            ImageView imageView = (ImageView) event.getSource();
            imageView.setEffect(null);
            RotateTransition rt2 = new RotateTransition(Duration.millis(200), (Node) event.getSource());
            rt2.setToAngle(0);
            rt2.setInterpolator(Interpolator.LINEAR);
            rt2.play();

        }

    }

    @FXML
    void onClick(MouseEvent event){
        if(event.getSource() instanceof ImageView){
            if(event.getSource() == imageViewArrow1){
                pageLoader.loadSubPage(PageLoader.PAG1PATH);
            }
            if(event.getSource() == imageViewArrow2){
                pageLoader.loadSubPage(PageLoader.PAG2PATH);
            }
            if(event.getSource() == imageViewArrow3){
                pageLoader.loadSubPage(PageLoader.PAG3PATH);
            }
            if(event.getSource() == imageViewArrow4){
                pageLoader.loadSubPage(PageLoader.PAG4PATH);
            }
        }
    }


}
