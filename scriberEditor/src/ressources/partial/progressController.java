package ressources.partial;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
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

    @FXML
    Label labelEtap1;
    @FXML
    Label labelEtap2;
    @FXML
    Label labelEtap3;
    @FXML
    Label labelEtap4;


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
        resetImageView();

    }

    private void resetImageView(){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.3);
        colorAdjust.setHue(-3d);


        switch (pageLoader.getLastPagePath()){
            case PageLoader.PAG1PATH:
                imageViewArrow1.setImage(arrowFullImage);
                imageViewArrow2.setImage(arrowImage);
                imageViewArrow3.setImage(arrowImage);
                imageViewArrow4.setImage(arrowImage);
                labelEtap1.setStyle("-fx-font-family: Montserrat-Regular; -fx-font-weight: bold;");
                break;
            case PageLoader.PAG2PATH:
                imageViewArrow1.setImage(arrowImage);
                imageViewArrow1.setEffect(colorAdjust);
                imageViewArrow2.setImage(arrowFullImage);
                labelEtap2.setStyle("-fx-font-family: Montserrat-Regular; -fx-font-weight: bold;");
                imageViewArrow3.setImage(arrowImage);
                imageViewArrow4.setImage(arrowImage);

                break;
            case PageLoader.PAG3PATH:
                imageViewArrow1.setImage(arrowImage);
                imageViewArrow1.setEffect(colorAdjust);
                imageViewArrow2.setImage(arrowImage);
                imageViewArrow2.setEffect(colorAdjust);
                imageViewArrow3.setImage(arrowFullImage);
                labelEtap3.setStyle("-fx-font-family: Montserrat-Regular; -fx-font-weight: bold;");
                imageViewArrow4.setImage(arrowImage);

                break;
            case PageLoader.PAG4PATH:
                imageViewArrow1.setImage(arrowImage);
                imageViewArrow1.setEffect(colorAdjust);
                imageViewArrow2.setImage(arrowImage);
                imageViewArrow2.setEffect(colorAdjust);
                imageViewArrow3.setImage(arrowImage);
                imageViewArrow3.setEffect(colorAdjust);
                imageViewArrow4.setImage(arrowFullImage);
                labelEtap4.setStyle("-fx-font-family: Montserrat-Regular; -fx-font-weight: bold;");

                break;
            default:
                System.out.println(pageLoader.getLastPagePath());
        }

    }


    @FXML
    void startAnimation(MouseEvent event){
        if(event.getSource() instanceof ImageView){
            ImageView imageView = (ImageView) event.getSource();
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
            resetImageView();
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
