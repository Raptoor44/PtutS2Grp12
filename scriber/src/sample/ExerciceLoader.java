package sample;

import exercice.Evaluation;
import exercice.Exercice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ExerciceLoader {

    public Exercice chargerUnExercice(String pathToFile){

        File fichier = new File(pathToFile);


        // ouverture d'un flux sur un fichier
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(fichier));

            // désérialization de l'objet
            Evaluation m = (Evaluation)ois.readObject() ;
            System.out.println(m) ;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        return null;
    }


    public Exercice chargerMediaDepuisExercice(String pathToFile){
        return null;
    }

}
