package model;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.util.Optional;

public class MediaAfficheur {

    private Main main;
    private ExerciceLoader exerciceLoader;

    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private Image image;

    private static final String[] VIDEO_EXTENSION_SUPPORTE = {"mp4","avi"};
    private static final String[] AUDIO_EXTENSION_SUPPORTE = {"mp3","wav"};

    public void setMedia(Media media) {
        this.media = media;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void setMediaView(MediaView mediaView) {
        this.mediaView = mediaView;
    }

    public MediaAfficheur(){
        main = Main.getInstance();
        exerciceLoader = main.getExerciceLoader();
    }


    public void initializeMediaVideo(File fileExercice){
        if(exerciceLoader.chargerMediaDepuisExercice(fileExercice.getPath()) != null){
            media = new Media(exerciceLoader.chargerMediaDepuisExercice(fileExercice.getPath()).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaView.setMediaPlayer(mediaPlayer);
        }
    }

    public void initializeMediaAudio(File fileExercice){
        if(exerciceLoader.chargerMediaDepuisExercice(fileExercice.getPath()) != null && exerciceLoader.chargerImageDepuisExercice(fileExercice.getPath()) != null){
            media = new Media(exerciceLoader.chargerMediaDepuisExercice(fileExercice.getPath()).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaView.setMediaPlayer(mediaPlayer);
            image = exerciceLoader.chargerImageDepuisExercice(fileExercice.getPath());
        }
    }

    public boolean isAudio(File fileExercice){
        return isMedia(fileExercice, AUDIO_EXTENSION_SUPPORTE);
    }

    public boolean isVideo(File fileExercice){
        return isMedia(fileExercice, VIDEO_EXTENSION_SUPPORTE);
    }

    private boolean isMedia(File fileExercice, String[] audioExtensionSupporte) {
        for(String extension : audioExtensionSupporte){
             if (getExtensionByStringHandling(exerciceLoader.chargerMediaDepuisExercice(fileExercice.getPath()).getPath()).toString().equals("Optional["+extension+"]")){
                return true;
            }
        }
        return false;
    }

    private Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public void pauseMedia(){
        mediaPlayer.pause();
    }

    public void playMedia(){
        mediaPlayer.play();
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public Media getMedia() {
        return media;
    }

    public Image getImage() {
        return image;
    }

    public void closeMedia(){
        mediaPlayer.stop();
    }

}
