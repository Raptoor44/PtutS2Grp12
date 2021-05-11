package ressources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import sample.GenerateurExercice;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller  implements Initializable {

    @FXML
    public Button button;

    @FXML
    public AnchorPane anchorPane;

    @FXML
    public TextField TitreExerciceTextField, tempsAlouerTextField;

    @FXML
    public TextArea ScriptTextArea, ConsigneTextArea;

    @FXML
    public CheckBox remplacementPartielCheckBox, modeEvaluationCheckBox, modeEntrainementCheckBox, sensibiliterALaCaseActiverCheckBox;

    public String mediaFilePath, imageFilePath;

    private boolean estUneEvaluation,isRemplacementPartiel,isSensibiliterALaCaseActiver;
    private float tempAlouer;

      //TODO mettre une  valeur par défault et cocher un des deux mode sur l'interface par défault
    public boolean estUneEvaluation(){
            //TODO prévenir que on a un probleme et force pour qu'il y est au moins une check box de cocher
        return estUneEvaluation;
    }

    public boolean isRemplacementPartiel() {
        return isRemplacementPartiel;

    }

    public boolean isSensibiliterALaCaseActiver(){
        return isSensibiliterALaCaseActiver;
    }

    //TODO vérifier que le textField est pas nul et mettre de base une valeur par défault ou bloquer si la valeur est null
    public float gettempAlouer(){
        return tempAlouer;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onNextButton(ActionEvent event) throws IOException{
        System.out.println("salut");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("page1.fxml"));
        anchorPane.getChildren().setAll(pane);
    }

    @FXML
    void onNextButton(MouseEvent event){
        System.out.println("salut toi");
    }


    @FXML
    void OnImportMediaClick(ActionEvent event){
        FileChooser chooser = new FileChooser();
        mediaFilePath = chooser.showOpenDialog(null).getPath();
        //TODO indiquer que le media est bien charger sur l'interface  et faire une vérif que le chemin obtenue est pas null
    }

    @FXML
    void OnImportImageClick(ActionEvent event){
        FileChooser chooser = new FileChooser();
        imageFilePath = chooser.showOpenDialog(null).getPath();
        //TODO indiquer que le media est bien charger sur l'interface  et faire une vérif que le chemin obtenue est pas null
    }


    //lorsqu'on clique sur une des check box mode exercice appeler cette événement
    @FXML
    void OnModeExerciceClick(ActionEvent event){
        if(!modeEntrainementCheckBox.isSelected() && !modeEvaluationCheckBox.isSelected()){
            //TODO sélectionner une checkbox par défault
        }else{

            if(!modeEvaluationCheckBox.isSelected()){
                estUneEvaluation = false;
            }

            if(!modeEntrainementCheckBox.isSelected()){
                estUneEvaluation = true;
            }
            if(modeEvaluationCheckBox.isSelected() && modeEntrainementCheckBox.isSelected()){
                modeEntrainementCheckBox.setSelected(false);
            }
        }



    }

    @FXML
    void OnTempAlouerEvent(ActionEvent event){
        tempAlouer = Float.valueOf(tempsAlouerTextField.getText());
    }

    @FXML
    void OnRemplacementPartielClick(ActionEvent event){
        isRemplacementPartiel = remplacementPartielCheckBox.isSelected();
    }

    @FXML
    void OnSensibiliterALaCaseClick(ActionEvent event){
        isSensibiliterALaCaseActiver = sensibiliterALaCaseActiverCheckBox.isSelected();
    }

    @FXML
    void OnCreateExerciceClick(ActionEvent event){
        FileChooser chooser = new FileChooser();
        String filePAth = chooser.showOpenDialog(null).getPath();
        GenerateurExercice generateurExercice = new GenerateurExercice();
        generateurExercice.nouveauFichierEvaluation("evalSwag", filePAth);
    }

}
