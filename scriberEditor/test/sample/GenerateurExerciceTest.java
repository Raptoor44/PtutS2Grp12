package sample;

import exercice.Evaluation;
import exercice.Exercice;
import org.junit.jupiter.api.Test;

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
        generateurExercice.nouveauFichierEntrainement("entrainement1","../.exerSample/video.mp4");
        generateurExercice.nouveauFichierEntrainement("superEntrainement","../.exerSample/audio.mp3");


    }

    @Test
    void testSerialisation(){
        ObjectOutputStream oos = null;

        //on sérialize l'exercice
        final FileOutputStream fichier;
        try {
            fichier = new FileOutputStream("../exerciceInfo.exera");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject((Exercice) new Evaluation("no", "lo", "efe", true, true, 0.5f));
            oos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}