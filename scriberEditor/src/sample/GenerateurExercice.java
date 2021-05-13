package sample;

import exercice.Entrainement;
import exercice.Evaluation;
import exercice.Exercice;
import ressources.Controller;


import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    public void nouveauFichierEvaluation(String cheminEnregistrement){


        //on récupère toutes les paramètre(titre, consigne...)
        //on créer une Evaluation
        Controller  controller = Main.controller;
        Exercice exercice = new Evaluation(
                controller.getTitre(),
                controller.getConsigne(),
                controller.getScript(),
                controller.isRemplacementPartiel(),
                controller.isSensibiliterALaCaseActiver(),
                controller.gettempAlouer());

        System.out.println(controller);

        System.out.println(exercice);

        //on sérialize l'objet  Entrainement
        serializeFile(exercice);

        List<String> paths = new ArrayList<>();
        paths.add(savedir  + "/exerciceInfo.exera");
        if(controller.getMediaFilePath() != null && !controller.getMediaFilePath().isEmpty()){
            paths.add(controller.getMediaFilePath());
        }
        if(controller.getImageFilePath() != null && !controller.getImageFilePath().isEmpty()){
            paths.add(controller.getImageFilePath());

        }
        mergeFile(cheminEnregistrement,paths);
    }


    private String getExtensionByStringHandling(String filename) {
        return "." + Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1)).get();
    }
    public void nouveauFichierEntrainement(String cheminEnregistrement){

        //on récupère toutes les paramètre(titre, consigne...)
        Controller  controller = Main.controller;
        //on créer un entrainement
        Exercice exercice = new Entrainement(
                controller.getTitre(),
                controller.getConsigne(),
                controller.getScript(),
                controller.isRemplacementPartiel(),
                controller.isSensibiliterALaCaseActiver(),
                true
        );

        //on sérialize l'objet  Entrainement
        serializeFile(exercice);

        //on créer le fichier exercice avec l'objet exercice et le media

        List<String> paths = new ArrayList<>();
        paths.add(savedir  + "/exerciceInfo.exera");
        if(controller.getMediaFilePath() != null && !controller.getMediaFilePath().isEmpty()){
            paths.add(controller.getMediaFilePath());
        }
        if(controller.getImageFilePath() != null && !controller.getImageFilePath().isEmpty()){
            paths.add(controller.getImageFilePath());

        }
        mergeFile(cheminEnregistrement,paths);

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


    private void mergeFile(String cheminEnregistrement, List<String> elements){
        Controller controller = Main.controller;

        List<String> srcFiles = elements;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(cheminEnregistrement + ".exer");

            ZipOutputStream zipOut = new ZipOutputStream(fos);
            //zipOut.setLevel(Deflater.NO_COMPRESSION);

            for (String srcFile : srcFiles) {



                File fileToZip = new File(srcFile);
                FileInputStream fis = new FileInputStream(fileToZip);
                String nameOfFile = fileToZip.getName();
                if(controller.getMediaFilePath() != null &&  fileToZip.getAbsolutePath().equals(controller.getMediaFilePath())){
                    nameOfFile = "media" + getExtensionByStringHandling(fileToZip.getPath()) ;
                }
                if(controller.getImageFilePath() != null &&  fileToZip.getAbsolutePath().equals(controller.getImageFilePath())){
                    nameOfFile = "image" + getExtensionByStringHandling(fileToZip.getPath());
                }
                ZipEntry zipEntry = new ZipEntry(nameOfFile);
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
