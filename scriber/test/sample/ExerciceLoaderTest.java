package sample;

import exercice.Exercice;
import model.ExerciceLoader;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

class ExerciceLoaderTest {


    private static final String PATHTOEXERFILE = "C:/Users/theol/.scriberEditor/superEntrainement.exer";


    @org.junit.jupiter.api.Test
    void chargerUnExercice() {

        ExerciceLoader exerciceLoader = new ExerciceLoader();

        exerciceLoader.chargerUnExercice(PATHTOEXERFILE);


    }
    /*
    @org.junit.jupiter.api.Test
    void chargerMediaDepuisExercice() {


        ExerciceLoader exerciceLoader = new ExerciceLoader();
        File mediaFile = exerciceLoader.chargerMediaDepuisExercice(PATHTOEXERFILE);

        try {
            Desktop.getDesktop().open(mediaFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


    } */

    @Test
    void testSerialisation(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("../exerciceInfo.exera"));

            // désérialization de l'objet
            Exercice exercice = (Exercice) ois.readObject();
            System.out.println(exercice) ;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}