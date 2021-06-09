package sample;

import exercice.Entrainement;
import exercice.Evaluation;
import exercice.Exercice;


import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GenerateurExercice {


    public static final File savedir = new File(new File(System.getProperty("user.home")), ".scriberEditor");

    private static final char DEFAULTOCULTATIONCHARACTER = '#';
    private static final String DEFAULTAIDETEXT = "texte d'aide par défault";

    private Main main;
    private String mediaFilePath;
    private String imageFilePath;
    private String titreExercice;
    private String consigneExercice;
    private String scriptExercice;
    private boolean sensibiliterAlaCaseActiver;
    private boolean remplacementPartiel;
    private boolean aideAccepter;
    private String aideText;
    private int tempAlouer;
    private char occultationCharacter;
    private int nbLetterMinimum;
    private boolean allowDisplayingSolution;
    private boolean allowDisplayNbWordDiscover;



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

    public void setAideText(String aideText) {
        this.aideText = aideText;
    }

    public void setNbLetterMinimum(int nbLetterMinimum) {
        this.nbLetterMinimum = nbLetterMinimum;
    }

    public void setAllowDisplayingSolution(boolean allowDisplayingSolution) {
        this.allowDisplayingSolution = allowDisplayingSolution;
    }

    public void setAllowDisplayNbWordDiscover(boolean allowDisplayNbWordDiscover) {
        this.allowDisplayNbWordDiscover = allowDisplayNbWordDiscover;
    }

    public void setOccultationCharacter(char occultationCharacter) {
        System.out.println("occultationCharacter = " + occultationCharacter);
        this.occultationCharacter = occultationCharacter;
    }

    public GenerateurExercice() {

        main = Main.getInstance();

        try {
            if(!savedir.exists())
                Files.createDirectory(savedir.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }


        occultationCharacter = DEFAULTOCULTATIONCHARACTER;
        aideText = DEFAULTAIDETEXT;

    }

    public void nouveauFichierEvaluation(String cheminEnregistrement){


        //on récupère toutes les paramètre(titre, consigne...)
        //on créer une Evaluation
        Exercice exercice = new Evaluation(
                titreExercice,
                consigneExercice,
                scriptExercice,
                occultationCharacter,
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

    //renvoie les élément manquant si c'est empty il manque rien
    public List<String> canGenerateExercice(){

        List<String> stringList = new ArrayList<>();

        if(mediaFilePath == null || mediaFilePath.isEmpty() || !mediaFilePath.matches(".*\\w.*")) stringList.add("un media");
        if(imageFilePath == null || imageFilePath.isEmpty() || !imageFilePath.matches(".*\\w.*")) stringList.add("une image");
        if(titreExercice == null || titreExercice.isEmpty() || !titreExercice.matches(".*\\w.*") ) stringList.add("un titre pour l'exercice");
        if(consigneExercice  == null || consigneExercice.isEmpty() || !consigneExercice.matches(".*\\w.*")) stringList.add("un consigne");
        if(scriptExercice == null || scriptExercice.isEmpty() || !scriptExercice.matches(".*\\w.*")) stringList.add("un script");

        return stringList;
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
                aideText,
                occultationCharacter,
                sensibiliterAlaCaseActiver,
                aideAccepter,
                remplacementPartiel,
                nbLetterMinimum,
                allowDisplayingSolution,
                allowDisplayNbWordDiscover
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
