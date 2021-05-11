package sample;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class GenerateurExerciceTest {

    @org.junit.jupiter.api.Test
    void nouveauFichierEvaluation() {

        GenerateurExercice generateurExercice = new GenerateurExercice();
        generateurExercice.nouveauFichierEvaluation("evaluation1","../.exerSample/video.mp4");
        generateurExercice.nouveauFichierEvaluation("superEvaluation","../.exerSample/audio.mp3");


    }

    @org.junit.jupiter.api.Test
    void nouveauFichierEntrainement() {

        GenerateurExercice generateurExercice = new GenerateurExercice();
        generateurExercice.nouveauFichierEntrainement("entrainement1","../.exerSample/audio.mp3");
        generateurExercice.nouveauFichierEntrainement("superEntrainement","../.exerSample/audio.mp3");


    }
}