package ressources;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.GenerateurExercice;
import sample.Main;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerPage4OptionEtExport extends SuperController implements Initializable {

    @FXML
    private AnchorPane entrainementAnchorPane;

    @FXML
    private AnchorPane evaluationAnchorPane;

    @FXML
    private CheckBox modeEntrainementCheckBox;

    @FXML
    private CheckBox modeEvaluationCheckBox;

    @FXML
    private ChoiceBox characterChoiceBox;

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

    @FXML
    private CheckBox displayNbWordDiscoverCheckBox;

    @FXML
    private Spinner spinnerNbLetter;

    @FXML
    private CheckBox autoriserAffichageSolutionCheckBox;

    @FXML
    ImageView occulMark;

    @FXML
    ImageView sensitiveMark;

    @FXML
    ImageView partialMark;

    @FXML
    ImageView helpMark;

    @FXML
    ImageView wordDiscoveredMark;

    @FXML
    ImageView solutionMark;

    private static final boolean DEFAULT_EST_UNE_EVALUATION_VALUE = false;
    private static final String CHARACTEROCULTATIONDISPO[] = {"&","#","|","%","=","+","¤","§","~"};


    private boolean estUneEvaluation;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        estUneEvaluation = DEFAULT_EST_UNE_EVALUATION_VALUE;
        if(modeEvaluationCheckBox != null  && modeEntrainementCheckBox != null){
            if(DEFAULT_EST_UNE_EVALUATION_VALUE){
                modeEvaluationCheckBox.setSelected(true);
            }else{
                modeEntrainementCheckBox.setSelected(true);

            }
            updateAnchorPane();
        }

        ObservableList<String> caracDocultation = FXCollections.observableArrayList();

        caracDocultation.addAll(Arrays.asList(CHARACTEROCULTATIONDISPO));

        characterChoiceBox.setItems(caracDocultation);
        characterChoiceBox.getSelectionModel().selectFirst();

        characterChoiceBox.setOnAction(event -> onCharacterOcultationSet((ActionEvent) event));


        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2,3);
        spinnerNbLetter.setValueFactory(valueFactory);


        sensibiliterCaseCheckBox.setSelected(generateurExercice.sensibiliterAlaCaseActiver);
        remplacementPartielCheckBox.setSelected(generateurExercice.remplacementPartiel);
        displayNbWordDiscoverCheckBox.setSelected(generateurExercice.allowDisplayNbWordDiscover);
        autoriserAffichageSolutionCheckBox.setSelected(generateurExercice.allowDisplayingSolution);
        if(generateurExercice.aideText != null)
            aideTextArea.setText(generateurExercice.aideText);
        allowHelpCheckBox.setSelected(generateurExercice.aideAccepter);


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
            generateurExercice.tempAlouer = Integer.valueOf(tempAlouer.getText());
    }

    @FXML
    void onCharacterOcultationSet(ActionEvent event){
        if(characterChoiceBox.getSelectionModel().getSelectedItem() != null)
            generateurExercice.occultationCharacter = characterChoiceBox.getSelectionModel().getSelectedItem().toString().charAt(0);
    }


    @FXML
    void OnCreateExerciceClick(ActionEvent event){
        // vérifier que on a tous les élément
        if(!generateurExercice.canGenerateExercice().isEmpty()){

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("il manque les élément suivant : ");

            for (String string: generateurExercice.canGenerateExercice()
                 ) {
                stringBuilder.append( string + " , ");
            }


            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention !!");

            alert.setContentText(stringBuilder.toString());

            alert.showAndWait();
            return;



        }

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


        generateurExercice.sensibiliterAlaCaseActiver = sensibiliterCaseCheckBox.isSelected();
        if(estUneEvaluation){
            generateurExercice.nouveauFichierEvaluation(chosenFile.getPath());
        }else {
            generateurExercice.remplacementPartiel = remplacementPartielCheckBox.isSelected();
            generateurExercice.allowDisplayNbWordDiscover = displayNbWordDiscoverCheckBox.isSelected();
            generateurExercice.allowDisplayingSolution = autoriserAffichageSolutionCheckBox.isSelected();
            generateurExercice.nbLetterMinimum = (Integer) spinnerNbLetter.getValue();
            generateurExercice.aideText = aideTextArea.getText();
            generateurExercice.aideAccepter = allowHelpCheckBox.isSelected();
            generateurExercice.nouveauFichierEntrainement(chosenFile.getPath());

        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export d'un exercice");
        alert.setHeaderText("Resultat :");
        alert.setContentText("L'" + ((estUneEvaluation)?"evaluation":"entrainement") + " a été créé avec succès !!");
        alert.showAndWait();

        try {
            Desktop.getDesktop().open(new File(chosenFile.getPath().replace(chosenFile.getName(), "")) );
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void onRetourClick(ActionEvent event){
        pageLoader.loadSubPage(PageLoader.PAG3PATH);
    }

    @FXML
    public void occultTip(){
        Tooltip tooltip = new Tooltip("Dans cette liste, vous avez le choix entre différents caractères pour remplacer les lettres");
        Tooltip.install(occulMark, tooltip);
    }

    @FXML
    public void sensitiveTip(){
        Tooltip tooltip = new Tooltip("En sélectionnant cette case, vous activez la sensibilité à la casse");
        Tooltip.install(sensitiveMark, tooltip);
    }

    @FXML
    public void partialTip(){
        Tooltip tooltip = new Tooltip("En sélectionnant cette case, vous permettez à vos élèves de découvrir le début de certains mots en tapant un nombre minimum de lettres.");
        Tooltip.install(partialMark, tooltip);
    }

    @FXML
    public void helpTip(){
        Tooltip tooltip = new Tooltip("En sélectionnant cette case, vous permettez à vos élèves d'avoir accès à une aide. Cette aide sera remplie par vos soins plus bas.");
        Tooltip.install(helpMark, tooltip);
    }

    @FXML
    public void wordTip(){
        Tooltip tooltip = new Tooltip("En sélectionnant cette case, vous permettez à vos élèves de voir leur score en temps réel");
        Tooltip.install(wordDiscoveredMark, tooltip);
    }

    @FXML
    public void solutionTip(){
        Tooltip tooltip = new Tooltip("En sélectionnant cette case, vous permettez à vos élèves de visualiser la solution à la fin de leur exercice.");
        Tooltip.install(solutionMark, tooltip);
    }


}
