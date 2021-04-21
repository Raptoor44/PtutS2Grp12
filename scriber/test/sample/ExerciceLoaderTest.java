package sample;

import static org.junit.jupiter.api.Assertions.*;

class ExerciceLoaderTest {

    @org.junit.jupiter.api.Test
    void chargerUnExercice() {

        ExerciceLoader exerciceLoader = new ExerciceLoader();

        exerciceLoader.chargerUnExercice("../.exerSample/newFichierExercice.exer");


    }

    @org.junit.jupiter.api.Test
    void chargerMediaDepuisExercice() {
    }
}