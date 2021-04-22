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
        generateurExercice.nouveauFichierEvaluation("../.exerSample/video.mp4");


    }

    @org.junit.jupiter.api.Test
    void nouveauFichierEntrainement() {


    }
}