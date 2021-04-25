package sample;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ExerciceLoaderTest {


    private static final String PATHTOEXERFILE = "../.exerSample/exercice.exer";


    @org.junit.jupiter.api.Test
    void chargerUnExercice() {

        ExerciceLoader exerciceLoader = new ExerciceLoader();

        exerciceLoader.chargerUnExercice(PATHTOEXERFILE);


    }

    @org.junit.jupiter.api.Test
    void chargerMediaDepuisExercice() {


        ExerciceLoader exerciceLoader = new ExerciceLoader();

        File mediaFile = new File(exerciceLoader.chargerMediaDepuisExercice(PATHTOEXERFILE) );

        try {
            Desktop.getDesktop().open(mediaFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}