package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ressources.Controller;
import ressources.FXMLoader;

public class Main extends Application {

    public static Parent parent;
    public static Scene scene;
    public static Controller controller;


    private static final String INDEXPATH = "../ressources/Index.fxml";
    private static final String PAG1PATH = "../ressources/page1.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLoader fxmLoader = new FXMLoader();
        parent = fxmLoader.load("Index.fxml");
        primaryStage.setTitle("Scriber Editor");
        scene = new Scene(parent, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        controller = fxmLoader.getController();


        //je set les value du controller pour le test
        CheckBox checkBox = new CheckBox();
        checkBox.setSelected(true);
        controller.modeEntrainementCheckBox = checkBox;
        CheckBox checkBox2 = new CheckBox();
        checkBox.setSelected(false);
        controller.modeEvaluationCheckBox = checkBox2;
        controller.remplacementPartielCheckBox = checkBox;
        controller.sensibiliterALaCaseActiverCheckBox = checkBox;
        TextField textField34 = new TextField();
        textField34.setText("34");
        controller.tempsAlouerTextField = textField34;
        TextField textField = new TextField();
        textField.setText("le texte du titre");
        controller.TitreExerciceTextField = textField;
        TextArea textArea = new TextArea();
        textArea.setText("le texte d'un TextArea");
        controller.ConsigneTextArea = textArea;
        controller.ScriptTextArea = textArea;





    }


    public static void main(String[] args) {
        launch(args);
    }




}
