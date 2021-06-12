package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import sample.GenerateurExercice;
import sample.Main;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerPage2Media extends SuperController implements Initializable {

    @FXML
    private MediaView mediaView;

    @FXML
    private ImageView imageView;

    private String mediafilePath;
    private String imagefilePath;

    @FXML
    protected AnchorPane ProgressAnchorPane;

    @FXML
    private Label mediaSucessLabel;

    @FXML
    private Label imageSucessLabel;


    @FXML
    void onRetourClick(ActionEvent event){
        pageLoader.loadSubPage(PageLoader.PAG1PATH);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        if(generateurExercice.mediaFilePath != null){
            mediafilePath = generateurExercice.mediaFilePath;
            Media media = new Media(new File(mediafilePath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            mediaView.setMediaPlayer(mediaPlayer);
            mediaSucessLabel.setText("le media " + new File(mediafilePath).getName() + " a été chargé avec succès");

        }
        if(generateurExercice.imageFilePath != null) {
            imagefilePath = generateurExercice.imageFilePath;
            imageView.setImage(new Image(new File(imagefilePath).toURI().toString()));
            imageSucessLabel.setText("l'image " + new File(imagefilePath).getName() + " a été chargé avec succès");

        }

    }

    @FXML
    void OnImportMediaClick(ActionEvent event){
        FileChooser chooser = new FileChooser();
        mediafilePath = chooser.showOpenDialog(null).getAbsolutePath();
        //TODO indiquer que le media est bien charger sur l'interface  et faire une vérif que le chemin obtenue est pas null

        Media media = new Media(new File(mediafilePath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaView.setMediaPlayer(mediaPlayer);
        mediaSucessLabel.setText("le media " + new File(mediafilePath).getName() + " a été chargé avec succès");

         List list =  media.getTracks();
        for (Object objet : list
                ) {
            System.out.println(objet.toString());
        }

        if(list.isEmpty()){
            System.out.println("elle est vide ");
        }
    }

    @FXML
    void OnImportImageClick(ActionEvent event){
        FileChooser chooser = new FileChooser();
        imagefilePath = chooser.showOpenDialog(null).getAbsolutePath();
        //TODO indiquer que le media est bien charger sur l'interface  et faire une vérif que le chemin obtenue est pas null

        imageView.setImage(new Image(new File(imagefilePath).toURI().toString()));
        imageSucessLabel.setText("l'image " + new File(imagefilePath).getName() + " a été chargé avec succès");

    }

    @FXML
    void onNextPageClick(ActionEvent event){

        if(mediafilePath == null || mediafilePath.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !!");

            alert.setContentText("tu as selectionner aucun media !!");

            alert.showAndWait();
            return;
        }


        generateurExercice.mediaFilePath  = mediafilePath;
        if(imagefilePath != null && !imagefilePath.isEmpty()){
            generateurExercice.imageFilePath =  imagefilePath;
        }

        pageLoader.loadSubPage(PageLoader.PAG3PATH);

    }




}
