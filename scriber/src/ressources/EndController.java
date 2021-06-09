package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import sample.Main;
import sample.Score;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class EndController implements Initializable {


    public static final File saveDir = new File(new File(System.getProperty("user.home")), ".scriber");


    @FXML
    Label scoreLabel;

    @FXML
    Label scoreOn20;

    @FXML
    TextField surname;

    @FXML
    TextField name;

    @FXML
    TextField group;



    private Score score;
    private Main main;

    public EndController(){
        main = Main.getInstance();
        score = main.getScore();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scoreLabel.setText(String.format("%d / %d", score.getPoints(),score.getNbWords()));
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        float score20 = Math.round((20 * (float) score.getPoints()) / (float) score.getNbWords());
        scoreOn20.setText(decimalFormat.format(score20) + "/ 20");
    }


    @FXML
    void onExportClick(ActionEvent event){

        Score score = main.getScore();

        System.out.println("export du score ...");

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("All Files", "*.*");
        fileChooser.getExtensionFilters().add(extentionFilter);

        fileChooser.setTitle("Enregistrer score");


        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);
        if(!userDirectory.canRead()) {
            userDirectory = new File("c:/");
        }
        fileChooser.setInitialDirectory(userDirectory);

        //on recupère le fichier choisi
        File chosenFile = fileChooser.showSaveDialog(null);
        //on verif que le fichier est pas nul
        String path;
        if(chosenFile != null) {
            path = chosenFile.getPath();
        } else {
            //default return value
            path = null;
        }

        try {
            FileWriter myWriter = new FileWriter(chosenFile.getPath() + ".txt");
            myWriter.write("Nom : " + surname.getText() + "\n");
            myWriter.write("Prenom : " + name.getText()+ "\n");
            myWriter.write("Classe : " + group.getText() + "\n");
            myWriter.write("Point : " + score.getPoints() + "\n");
            myWriter.write("temps " + String.format("%d:%d",
                    TimeUnit.MILLISECONDS.toMinutes(score.getTotalTimePased()),
                    TimeUnit.MILLISECONDS.toSeconds(score.getTotalTimePased()) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(score.getTotalTimePased()))
            ));
            myWriter.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export d'un score");
            alert.setHeaderText("Resultat :");
            alert.setContentText("Le score a été créé avec succès !!");
            alert.showAndWait();


            try {
                Desktop.getDesktop().open(new File(chosenFile.getPath().replace(chosenFile.getName(), "")) );
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }
}
