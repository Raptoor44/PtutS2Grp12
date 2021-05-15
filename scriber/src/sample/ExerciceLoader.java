package sample;


import exercice.Exercice;
import javafx.scene.image.Image;

import java.io.*;
import java.nio.file.Files;
import java.util.Optional;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ExerciceLoader {


    public static final File savedir = new File(new File(System.getProperty("user.home")), ".scriber");

    public static String actualUnzipedExercice;
    private String mediaPath, imagePath;

    //metada tu dernier exercice charger
    private String artist;
    private float year;
    private String album;
    private String genre;
    private String title;

    public boolean isFinish;

    public float getYear() {
        return year;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }



    public ExerciceLoader() {
        actualUnzipedExercice = "nullfinpasnulltacapté";
        try {
            if(!savedir.exists())
                Files.createDirectory(savedir.toPath());
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
            ois = new ObjectInputStream(new FileInputStream(savedir + "/fichierExerciceInput/exerciceInfo.exera"));

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

    public Image chagerImageDepuisExercice(String pathToFile){
        if(actualUnzipedExercice != null && !actualUnzipedExercice.equals(pathToFile)){
            unzipExerciceFile(pathToFile);
            actualUnzipedExercice = pathToFile;
        }

        Image image = new Image(new File(imagePath).toURI().toString());

        return image;
    }

    public void loadMediaData(File media){
        try {



            if(!getExtensionByStringHandling(mediaPath).equals(".mp3")){


                title = "titre ";
                artist = "artist";
                genre = "genre";
                album = "album";
                year =  0;


            }else{

                // Retrieve the necessary info from metadata
                // Names - title, xmpDM:artist etc. - mentioned below may differ based


                InputStream input = new FileInputStream(media);
                ContentHandler handler = new DefaultHandler();
                Metadata metadata = new Metadata();
                Parser parser = new Mp3Parser();
                ParseContext parseCtx = new ParseContext();
                parser.parse(input,handler, metadata, parseCtx);
                input.close();


                if(metadata.get("title") != null)
                    title = metadata.get("title");
                if(metadata.get("xmpDM:artist") != null)
                    artist = metadata.get("xmpDM:artist");
                if(metadata.get("xmpDM:genre") != null)
                    genre =metadata.get("xmpDM:genre");
                if(metadata.get("xmpDM:album") != null)
                    album = metadata.get("xmpDM:album");
                if(metadata.get("xmpDM:releaseDate") != null)
                    year =  Float.valueOf(metadata.get("xmpDM:releaseDate"));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }

    }



    private void unzipExerciceFile(String pathToFile){
        File destDir = new File(savedir + "/fichierExerciceInput/");
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


        if(destFile.getName().replace(getExtensionByStringHandling(destFilePath), "").equals("media")){
            mediaPath = destFilePath;
        }

        if(destFile.getName().replace(getExtensionByStringHandling(destFilePath), "").equals("image")){
            imagePath = destFilePath;
        }
        return destFile;
    }


    private String getExtensionByStringHandling(String filename) {
        return "." + Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1)).get();
    }
}
