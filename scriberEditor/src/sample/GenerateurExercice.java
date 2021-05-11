package sample;

import exercice.Entrainement;
import exercice.Evaluation;
import exercice.Exercice;
import ressources.Controller;


import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GenerateurExercice {


    public static final File savedir = new File(new File(System.getProperty("user.home")), ".scriberEditor");



    public GenerateurExercice() {
        try {
            if(!savedir.exists())
                Files.createDirectory(savedir.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void nouveauFichierEvaluation(String exerciceName, String ressourceFilePath){


        //on récupère toutes les paramètre(titre, consigne...)
        //on créer une Evaluation
        Controller  controller = Main.controller;
        Exercice exercice = new Evaluation(
                controller.TitreExerciceTextField.getText(),
                controller.ConsigneTextArea.getText(),
                controller.ScriptTextArea.getText(),
                controller.isRemplacementPartiel(),
                controller.isSensibiliterALaCaseActiver(),
                controller.gettempAlouer());


        //on sérialize l'objet  Entrainement
        serializeFile(exercice);

        //on créer le fichier exercice avec l'objet exercice et le media
        mergeFile(exerciceName,  ressourceFilePath, savedir  + "/exerciceInfo.exera");

    }

    public void nouveauFichierEntrainement(String exerciceName, String ressourceFilePath){

        //on récupère toutes les paramètre(titre, consigne...)


        //on créer un entrainement
        Exercice exercice = new Entrainement(
                "titre de l'entrainement",
                "consigne de l'entrainement",
                "efe",
                true,
                true,
                true
        );

        //on sérialize l'objet  Entrainement
        serializeFile(exercice);

        //on créer le fichier exercice avec l'objet exercice et le media
        mergeFile(exerciceName, savedir  + "/exerciceInfo.exera", ressourceFilePath );

    }

    private void serializeFile(Exercice exercice){
        ObjectOutputStream oos = null;

        //on sérialize l'exercice
        final FileOutputStream fichier;
        try {
            fichier = new FileOutputStream(savedir  + "/exerciceInfo.exera");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(exercice);
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


    private void mergeFile(String exerciceName, String pathExercice, String pathVideo){
        List<String> srcFiles = Arrays.asList(pathExercice, pathVideo);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(savedir + "/" + exerciceName + ".exer");

            ZipOutputStream zipOut = new ZipOutputStream(fos);
            //zipOut.setLevel(Deflater.NO_COMPRESSION);

            for (String srcFile : srcFiles) {
                File fileToZip = new File(srcFile);
                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                fis.close();
            }
            zipOut.close();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
