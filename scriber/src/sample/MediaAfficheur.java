package sample;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;

public class MediaAfficheur {

    private Main main;
    private ExerciceLoader exerciceLoader;

    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;

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

        }
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
}
