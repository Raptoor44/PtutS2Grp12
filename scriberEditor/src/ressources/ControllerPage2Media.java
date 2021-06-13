package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import sample.GenerateurExercice;
import sample.Main;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
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
    ImageView mediaMark;

    @FXML
    ImageView imageMark;

    @FXML
    VBox imageBox;

    private static final String[] VIDEO_EXTENSION_SUPPORTE = {"mp4","MP4","avi","AVI"};
    private static final String[] AUDIO_EXTENSION_SUPPORTE = {"mp3","MP3","wav","WAV"};


    @FXML
    void onRetourClick(ActionEvent event){
        pageLoader.loadSubPage(PageLoader.PAG1PATH);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);



        imageBox.setDisable(true);
        imageBox.setVisible(false);

        if(generateurExercice.mediaFilePath != null){
            mediafilePath = generateurExercice.mediaFilePath;
            Media media = new Media(new File(mediafilePath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            mediaView.setMediaPlayer(mediaPlayer);
            mediaSucessLabel.setText("le media " + new File(mediafilePath).getName() + " a été chargé avec succès");

            if(isAudio(new File((mediafilePath)))){
                imageBox.setDisable(false);
                imageBox.setVisible(true);
            } else if(isVideo(new File(mediafilePath))){
                imageBox.setDisable(true);
                imageBox.setVisible(false);
            }

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
        String tempmediafilePath = chooser.showOpenDialog(null).getAbsolutePath();

        try {
            Media media = new Media(new File(tempmediafilePath).toURI().toString());

            mediafilePath = tempmediafilePath;

            MediaPlayer mediaPlayer = new MediaPlayer(media);

            mediaView.setMediaPlayer(mediaPlayer);
            mediaSucessLabel.setText("Le media " + new File(mediafilePath).getName() + " a été chargé avec succès");

            if(isAudio(new File(mediafilePath))){
                imageBox.setVisible(true);
                imageBox.setDisable(false);
            } else if(isVideo(new File(mediafilePath))){
                imageBox.setVisible(false);
                imageBox.setDisable(true);
            }

        }catch (MediaException exception){
            mediaSucessLabel.setText("Erreur avec le media " + new File(tempmediafilePath).getName());
            //Todo mettre une alert box ?

        }

    }

    @FXML
    void OnImportImageClick(ActionEvent event){
        FileChooser chooser = new FileChooser();
        String tempimagefilePath = chooser.showOpenDialog(null).getAbsolutePath();
        Image image = new Image(new File(tempimagefilePath).toURI().toString());
        if(image != null && image.getWidth() != 0){

            imagefilePath = tempimagefilePath;
            imageView.setImage(image);
            imageSucessLabel.setText("L'image " + new File(imagefilePath).getName() + " a été chargée avec succès");

        }else{
            imageSucessLabel.setText("Erreur avec l'image " + new File(tempimagefilePath).getName());
            //Todo mettre une alert box ?

        }


    }

    @FXML
    void onNextPageClick(ActionEvent event){

        if(mediafilePath == null || mediafilePath.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !!");

            alert.setContentText("Tu n'as selectionné aucun media.");

            alert.showAndWait();
            return;
        }

        if(isAudio(new File(mediafilePath)) && (imagefilePath == null || imagefilePath.isEmpty()) ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !!");

            alert.setContentText("Tu n'as selectionné aucune image avec ton audio.");

            alert.showAndWait();
            return;

        }

        generateurExercice.mediaFilePath  = mediafilePath;
        if(imagefilePath != null && !imagefilePath.isEmpty()){
            generateurExercice.imageFilePath =  imagefilePath;
        }

        pageLoader.loadSubPage(PageLoader.PAG3PATH);

    }

    @FXML
    public void mediaTip(){
        Tooltip tooltip = new Tooltip("Cliquez sur le bouton pour importer un fichier audio ou vidéo.");
        Tooltip.install(mediaMark, tooltip);
    }

    @FXML
    public void imageTip(){
        Tooltip tooltip = new Tooltip("Cliquez sur le bouton pour importer une image si votre fichier est un audio");
        Tooltip.install(imageMark, tooltip);
    }

    private boolean isMedia(File fileExercice, String[] audioExtensionSupporte) {
        for(String extension : audioExtensionSupporte){
            System.out.println(getExtensionByStringHandling(fileExercice.toURI().toString()));
            if (getExtensionByStringHandling(fileExercice.getPath()).toString().equals("Optional["+extension+"]")){
                return true;
            }
        }
        return false;
    }

    public boolean isVideo(File file){
        return isMedia(file, VIDEO_EXTENSION_SUPPORTE);
    }


    private boolean isAudio(File file){
        return isMedia(file, AUDIO_EXTENSION_SUPPORTE);
    }

    private Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

}
