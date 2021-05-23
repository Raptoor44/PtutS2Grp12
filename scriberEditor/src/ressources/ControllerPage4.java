package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import sample.GenerateurExercice;
import sample.Main;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPage4 implements Initializable {

    @FXML
    private AnchorPane entrainementAnchorPane;

    @FXML
    private AnchorPane evaluationAnchorPane;

    @FXML
    private CheckBox modeEntrainementCheckBox;

    @FXML
    private CheckBox modeEvaluationCheckBox;

    private static final boolean DEFAULT_EST_UNE_EVALUATION_VALUE = false;

    private Main main;
    private PageLoader pageLoader;
    private GenerateurExercice generateurExercice;
    private boolean estUneEvaluation;


    public ControllerPage4(){
        main = Main.getInstance();
        pageLoader = main.pageLoader;
        generateurExercice = main.generateurExercice;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        estUneEvaluation = DEFAULT_EST_UNE_EVALUATION_VALUE;
        if(modeEvaluationCheckBox != null  && modeEntrainementCheckBox != null){
            if(DEFAULT_EST_UNE_EVALUATION_VALUE){
                modeEvaluationCheckBox.setSelected(true);
            }else{
                modeEntrainementCheckBox.setSelected(true);

            }
            updateAnchorPane();
        }


    }

    private void updateAnchorPane(){

        if(estUneEvaluation){


            evaluationAnchorPane.setDisable(false);
            evaluationAnchorPane.setOpacity(1.0f);

            entrainementAnchorPane.setDisable(true);
            entrainementAnchorPane.setOpacity(0.0f);

        }else{

            evaluationAnchorPane.setDisable(true);
            evaluationAnchorPane.setOpacity(0.0f);

            entrainementAnchorPane.setDisable(false);
            entrainementAnchorPane.setOpacity(1.0f);

        }

    }



    //lorsqu'on clique sur une des check box mode exercice appeler cette événement
    @FXML
    void OnModeExerciceClick(ActionEvent event) {
        //série de if et else pour s'asurer que une des checkbox est toujours coché
        if (!modeEntrainementCheckBox.isSelected() && !modeEvaluationCheckBox.isSelected()) {
            //TODO sélectionner une checkbox par défault
            if (estUneEvaluation) {
                estUneEvaluation = false;
                modeEntrainementCheckBox.setSelected(true);
            } else {
                estUneEvaluation = true;
                modeEvaluationCheckBox.setSelected(true);
            }
        } else {

            if (!modeEvaluationCheckBox.isSelected()) {
                estUneEvaluation = false;
            }

            if (modeEvaluationCheckBox.isSelected() && modeEntrainementCheckBox.isSelected() && !estUneEvaluation) {
                estUneEvaluation = true;
                modeEntrainementCheckBox.setSelected(false);
            } else {
                estUneEvaluation = false;
                modeEvaluationCheckBox.setSelected(false);

            }

            if (!modeEntrainementCheckBox.isSelected()) {
                estUneEvaluation = true;
            }


        }
        updateAnchorPane();

    }


        @FXML
    void OnCreateExerciceClick(ActionEvent event){
        //TOdo verifié que c une eval ou un entrainement
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("All Files", "*.*");
        fileChooser.getExtensionFilters().add(extentionFilter);

        fileChooser.setTitle("Enregistrer un" + ((estUneEvaluation)?"e évaluation":" entrainement"));


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

        if(estUneEvaluation){

            generateurExercice.nouveauFichierEvaluation(chosenFile.getPath());

        }else {
            generateurExercice.nouveauFichierEntrainement(chosenFile.getPath());

        }
    }


}
