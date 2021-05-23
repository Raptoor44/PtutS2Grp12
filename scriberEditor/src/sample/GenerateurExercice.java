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


    //TODO au lieu que generateur va chercher les info c'est au controller de set les info et de vérif quelle sont bonnes
    private Main main;
    private String mediaFilePath;
    private String imageFilePath;
    private String titreExercice;
    private String consigneExercice;
    private String scriptExercice;
    private boolean sensibiliterAlaCaseActiver;
    private boolean remplacementPartiel;
    private boolean aideAccepter;
    private int tempAlouer;

    public void setTitreExercice(String titreExercice) {
        this.titreExercice = titreExercice;
    }

    public void setTempAlouer(int tempAlouer) {
        this.tempAlouer = tempAlouer;
    }

    public void setScriptExercice(String scriptExercice) {
        this.scriptExercice = scriptExercice;
    }

    public void setConsigneExercice(String consigneExercice) {
        this.consigneExercice = consigneExercice;
    }

    public void setRemplacementPartiel(boolean remplacementPartiel) {
        this.remplacementPartiel = remplacementPartiel;
    }

    public void setSensibiliterAlaCaseActiver(boolean sensibiliterAlaCaseActiver) {
        this.sensibiliterAlaCaseActiver = sensibiliterAlaCaseActiver;
    }

    public void setMediaFilePath(String mediaFilePath) {
        this.mediaFilePath = mediaFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public void setAideAccepter(boolean aideAccepter) {
        this.aideAccepter = aideAccepter;
    }

    public GenerateurExercice() {

        main = Main.getInstance();

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
        Exercice exercice = new Evaluation(
                titreExercice,
                consigneExercice,
                scriptExercice,
                sensibiliterAlaCaseActiver,
                tempAlouer);


        System.out.println(exercice);

        //on sérialize l'objet  Entrainement
        serializeFile(exercice);

        List<String> paths = new ArrayList<>();
        paths.add(savedir  + "/exerciceInfo.exera");
        if(mediaFilePath != null && !mediaFilePath.isEmpty()){
            paths.add(mediaFilePath);
        }
        if(imageFilePath != null && !imageFilePath.isEmpty()){
            paths.add(imageFilePath);

        }
        mergeFile(cheminEnregistrement,paths);


        System.out.println(exercice);
        System.out.println("est créer a l'emplacement ");
        System.out.println(cheminEnregistrement);


    }


    private String getExtensionByStringHandling(String filename) {
        return "." + Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1)).get();
    }
    public void nouveauFichierEntrainement(String cheminEnregistrement){

        //on récupère toutes les paramètre(titre, consigne...)
        //on créer un entrainement
        Exercice exercice = new Entrainement(
                titreExercice,
                consigneExercice,
                scriptExercice,
                sensibiliterAlaCaseActiver,
                aideAccepter,
                remplacementPartiel

        );

        //on sérialize l'objet  Entrainement
        serializeFile(exercice);

        //on créer le fichier exercice avec l'objet exercice et le media

        List<String> paths = new ArrayList<>();
        paths.add(savedir  + "/exerciceInfo.exera");
        if(mediaFilePath != null && !mediaFilePath.isEmpty()){
            paths.add(mediaFilePath);
        }
        if(imageFilePath != null && !imageFilePath.isEmpty()){
            paths.add(imageFilePath);

        }
        mergeFile(cheminEnregistrement,paths);


        System.out.println(exercice);
        System.out.println("est créer a l'emplacement ");
        System.out.println(cheminEnregistrement);
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
                if(mediaFilePath != null &&  fileToZip.getAbsolutePath().equals(mediaFilePath)){
                    nameOfFile = "media" + getExtensionByStringHandling(fileToZip.getPath()) ;
                }
                if(imageFilePath != null &&  fileToZip.getAbsolutePath().equals(imageFilePath)){
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
