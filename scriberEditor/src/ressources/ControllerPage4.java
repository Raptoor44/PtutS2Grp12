package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import sample.GenerateurExercice;
import sample.Main;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPage4 implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private AnchorPane entrainementAnchorPane;

    @FXML
    private AnchorPane evaluationAnchorPane;

    @FXML
    private CheckBox modeEntrainementCheckBox;

    @FXML
    private CheckBox modeEvaluationCheckBox;

    @FXML
    private TextField characterTextFied;

    @FXML
    private TextArea aideTextArea;

    @FXML
    private TextField tempAlouer;


    @FXML
    private CheckBox allowHelpCheckBox;

    @FXML
    private CheckBox sensibiliterCaseCheckBox;

    @FXML
    private CheckBox remplacementPartielCheckBox;


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
        AnchorPane.setBottomAnchor(anchorPane, 0d);
        AnchorPane.setLeftAnchor(anchorPane, 0d);
        AnchorPane.setRightAnchor(anchorPane, 0d);
        AnchorPane.setTopAnchor(anchorPane, 0d);


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
    void OnTempAlouerEvent(KeyEvent event){
        if(!tempAlouer.getText().isEmpty())
            generateurExercice.setTempAlouer(
                    Integer.valueOf(tempAlouer.getText())
            );
    }

    @FXML
    void onCharacterOcultationSet(KeyEvent event){
        if(characterTextFied.getText() != null && !characterTextFied.getText().isEmpty()){
            characterTextFied.setText(characterTextFied.getText().charAt(0) + "");
            generateurExercice.setOccultationCharacter(characterTextFied.getText().charAt(0));
        }else{
            System.out.println("vide");
        }
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


        generateurExercice.setSensibiliterAlaCaseActiver(sensibiliterCaseCheckBox.isSelected());
        if(estUneEvaluation){
            generateurExercice.nouveauFichierEvaluation(chosenFile.getPath());
        }else {
            generateurExercice.setAideText(aideTextArea.getText());
            generateurExercice.setSensibiliterAlaCaseActiver(sensibiliterCaseCheckBox.isSelected());
            generateurExercice.setAideAccepter(allowHelpCheckBox.isSelected());
            generateurExercice.nouveauFichierEntrainement(chosenFile.getPath());

        }
    }


}
