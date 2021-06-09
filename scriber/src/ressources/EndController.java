package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Main;
import sample.Score;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
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
        scoreLabel.setText(String.format("Score : %d / %d", score.getPoints(),score.getNbWords()));
        int score20 = (20 * score.getPoints()) / score.getNbWords();
        scoreOn20.setText(String.format("Score : %d / %d", score20, 20));
    }


    @FXML
    void onExportClick(ActionEvent event){
        Score score = main.getScore();


        try {
            FileWriter myWriter = new FileWriter(saveDir + "score.txt");
            myWriter.write("Nom : " + score.getNom());
            myWriter.write("Point : " + score.getPoints());
            myWriter.write("temp " + String.format("%d:%d",
                    TimeUnit.MILLISECONDS.toMinutes(score.getTimePassed()),
                    TimeUnit.MILLISECONDS.toSeconds(score.getTimePassed()) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(score.getTimePassed()))
            ));
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
