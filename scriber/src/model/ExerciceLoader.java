package model;

import exercice.Exercice;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.io.*;
import java.nio.file.Files;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ExerciceLoader {


    public static final File saveDir = new File(new File(System.getProperty("user.home")), ".scriber");

    //TODO rajouter les extension supporter par MediaView
    private static final String[] MEDIA_EXTENSION_SUPPORTE = {".mp3",".mp4",".avi",".wav"};
    //TODO rajouter les extension supporter par ImageView
    private static final String[] IMAGE_EXTENSION_SUPPORTE = {".jpg",".png",".bmp"};



    public static String actualUnzipedExercice;
    private String mediaPath, imagePath;

    public boolean isFinish;

    public ExerciceLoader() {
        actualUnzipedExercice = "nullfinpasnulltacapté";
        try {
            if(!saveDir.exists())
                Files.createDirectory(saveDir.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public Exercice chargerUnExercice(String pathToFile){

        if(actualUnzipedExercice != null && !actualUnzipedExercice.equals(pathToFile)){
            unzipExerciceFile(pathToFile);
            actualUnzipedExercice = pathToFile;
        }
        // ouverture d'un flux sur un fichier
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(saveDir + "/fichierExerciceInput/exerciceInfo.exera"));

            // désérialization de l'objet
            Exercice exercice = (Exercice) ois.readObject();
            System.out.println(exercice) ;
            return exercice;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public File chargerMediaDepuisExercice(String pathToFile){

        if(actualUnzipedExercice != null && !actualUnzipedExercice.equals(pathToFile)){
            unzipExerciceFile(pathToFile);
            actualUnzipedExercice = pathToFile;
        }

        return new File(mediaPath);
    }

    public Image chargerImageDepuisExercice(String pathToFile){
        if(actualUnzipedExercice != null && !actualUnzipedExercice.equals(pathToFile)){
            unzipExerciceFile(pathToFile);
            actualUnzipedExercice = pathToFile;
        }

        Image image = new Image(new File(imagePath).toURI().toString());

        return image;
    }


    private void unzipExerciceFile(String pathToFile){
        File destDir = new File(saveDir + "/fichierExerciceInput/");
        byte[] buffer = new byte[1024];
        ZipInputStream zis = null;
        try {
            zis = new ZipInputStream(new FileInputStream(pathToFile));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                File newFile = newFile(destDir, zipEntry);
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + newFile);
                    }
                } else {
                    // fix for Windows-created archives
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("Failed to create directory " + parent);
                    }

                    // write file content
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        for (String extension : MEDIA_EXTENSION_SUPPORTE) {
            if(getExtensionByStringHandling(destFilePath).equals(extension)){
                mediaPath = destFilePath;
            }
        }
        for (String extension : IMAGE_EXTENSION_SUPPORTE) {
            if(getExtensionByStringHandling(destFilePath).equals(extension)){
                imagePath = destFilePath;
            }
        }


        return destFile;
    }


    private String getExtensionByStringHandling(String filename) {
        return "." + Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1)).get();
    }
}
