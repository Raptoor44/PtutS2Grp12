package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import sample.GenerateurExercice;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller  implements Initializable {

    @FXML
    Button button;

    @FXML
    AnchorPane anchorPane;

    @FXML
    TextField titreExerciceTextField;

    @FXML
    TextField tempsAloueTextField;

    @FXML
    TextArea scriptTextArea;

    @FXML
    TextArea consigneTextArea;

    @FXML
    CheckBox remplacementPartielCheckBox;

    @FXML
    CheckBox modeEvaluationCheckBox;

    @FXML
    CheckBox modeEntrainementCheckBox;

    @FXML
    CheckBox sensibiliteALaCaseActiveeCheckBox;

    private String mediaFilePath, imageFilePath, script, titre, consigne;

    private boolean estUneEvaluation,isRemplacementPartiel,isSensibiliterALaCaseActiver;
    private float tempAlouer;

    private static final boolean DEFAULT_EST_UNE_EVALUATION_VALUE = true;

    private static final String[] PAGEPATHS = {
            "page1.fxml",
            "page2.fxml",
            "page3.fxml",
            "page4.fxml"
    };

    private int pageIndex;



    //getter setter
    public boolean estUneEvaluation(){
        return estUneEvaluation;
    }

    public boolean isRemplacementPartiel() {
        return isRemplacementPartiel;

    }

    public String getMediaFilePath() {
        return mediaFilePath;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public String getTitre() {
        return titre;
    }

    public String getScript() {
        return script;
    }

    public String getConsigne() {
        return consigne;
    }

    public boolean isSensibiliterALaCaseActiver(){
        return isSensibiliterALaCaseActiver;
    }

    //TODO vérifier que le textField est pas nul et mettre de base une valeur par défault ou bloquer si la valeur est null
    public float gettempAlouer(){
        if(tempAlouer <= 0){
            return -1;
        }
        return tempAlouer;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        estUneEvaluation = DEFAULT_EST_UNE_EVALUATION_VALUE;
        pageIndex = 0;
        if(modeEvaluationCheckBox != null  && modeEntrainementCheckBox != null){
            if(DEFAULT_EST_UNE_EVALUATION_VALUE){
                modeEvaluationCheckBox.setSelected(true);
            }else{
                modeEntrainementCheckBox.setSelected(true);
            }
        }

    }

    //Todo sa va pas les nouvelles page chargé créer leur propre instance de Controller avec anchorPane = null ducoup créer une class controller pour chaque page et centrailiser le chargement des page dans la class FXMLoader
    @FXML
    private void onNextButton(ActionEvent event) {
        try {
            AnchorPane pane = null;
            pane = FXMLLoader.load(getClass().getResource(PAGEPATHS[pageIndex]));
            pageIndex++;
            anchorPane.getChildren().setAll(pane);
       } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void OnImportMediaClick(ActionEvent event){
        FileChooser chooser = new FileChooser();
        mediaFilePath = chooser.showOpenDialog(null).getAbsolutePath();
        //TODO indiquer que le media est bien charger sur l'interface  et faire une vérif que le chemin obtenue est pas null
    }

    @FXML
    void OnImportImageClick(ActionEvent event){
        FileChooser chooser = new FileChooser();
        imageFilePath = chooser.showOpenDialog(null).getAbsolutePath();
        //TODO indiquer que le media est bien charger sur l'interface  et faire une vérif que le chemin obtenue est pas null
    }


    //lorsqu'on clique sur une des check box mode exercice appeler cette événement
    @FXML
    void OnModeExerciceClick(ActionEvent event){
        //série de if et else pour s'asurer que une des checkbox est toujours coché
        if(!modeEntrainementCheckBox.isSelected() && !modeEvaluationCheckBox.isSelected()){
            //TODO sélectionner une checkbox par défault
            if(estUneEvaluation){
                estUneEvaluation = false;
                modeEntrainementCheckBox.setSelected(true);
            }else{
                estUneEvaluation = true;
                modeEvaluationCheckBox.setSelected(true);
            }
        }else{

            if(!modeEvaluationCheckBox.isSelected()){
                estUneEvaluation = false;
            }

            if(modeEvaluationCheckBox.isSelected() && modeEntrainementCheckBox.isSelected() && !estUneEvaluation){
                estUneEvaluation = true;
                modeEntrainementCheckBox.setSelected(false);
            }else{
                estUneEvaluation = false;
                modeEvaluationCheckBox.setSelected(false);

            }

            if(!modeEntrainementCheckBox.isSelected()){
                estUneEvaluation = true;
            }

        }



    }

    @FXML
    void OnTempAlouerEvent(KeyEvent event){
        if(!tempsAloueTextField.getText().isEmpty())
            tempAlouer = Float.valueOf(tempsAloueTextField.getText());
    }

    @FXML
    void OnTitreEvent(KeyEvent event){
        titre = titreExerciceTextField.getText();
    }


    @FXML
    void OnRemplacementPartielClick(ActionEvent event){
        isRemplacementPartiel = remplacementPartielCheckBox.isSelected();
    }

    @FXML
    void OnSensibiliterALaCaseClick(ActionEvent event){
        isSensibiliterALaCaseActiver = sensibiliteALaCaseActiveeCheckBox.isSelected();
    }

    @FXML
    void OnScriptEvent(KeyEvent event){
        script = scriptTextArea.getText();
    }
    @FXML
    void OnConsigneEvent(KeyEvent event){
        consigne = consigneTextArea.getText();
    }


    @FXML
    void OnCreateExerciceClick(ActionEvent event){
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

        GenerateurExercice generateurExercice = new GenerateurExercice();
        if(estUneEvaluation){

            generateurExercice.nouveauFichierEvaluation(chosenFile.getPath());

        }else {
            generateurExercice.nouveauFichierEntrainement(chosenFile.getPath());

        }
    }

    @Override
    public String toString() {
        return "Controller{" +
                "button=" + button +
                ", anchorPane=" + anchorPane +
                ", TitreExerciceTextField=" + titreExerciceTextField +
                ", tempsAlouerTextField=" + tempsAloueTextField +
                ", ScriptTextArea=" + scriptTextArea +
                ", ConsigneTextArea=" + consigneTextArea +
                ", remplacementPartielCheckBox=" + remplacementPartielCheckBox +
                ", modeEvaluationCheckBox=" + modeEvaluationCheckBox +
                ", modeEntrainementCheckBox=" + modeEntrainementCheckBox +
                ", sensibiliterALaCaseActiverCheckBox=" + sensibiliteALaCaseActiveeCheckBox +
                ", mediaFilePath='" + mediaFilePath + '\'' +
                ", imageFilePath='" + imageFilePath + '\'' +
                ", script='" + script + '\'' +
                ", titre='" + titre + '\'' +
                ", consigne='" + consigne + '\'' +
                ", estUneEvaluation=" + estUneEvaluation +
                ", isRemplacementPartiel=" + isRemplacementPartiel +
                ", isSensibiliterALaCaseActiver=" + isSensibiliterALaCaseActiver +
                ", tempAlouer=" + tempAlouer +
                '}';
    }
}
