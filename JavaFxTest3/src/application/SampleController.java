package application;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class SampleController {

	

    public Label textcool, textLabel;
    
    
    public ImageView imgOubah;
    
    @FXML
    private StackPane content;
    
    private boolean isActive = false;
    
    public void sayHelloWorld(ActionEvent actionEvent) {
    	
    	if(isActive) {
    		isActive = false;
            textcool.setText("");
            textLabel.setText("Coucou");

    	}else {
			isActive = true;
            textcool.setText("Coucou");
            textLabel.setText("");
    	}
        
    }
    
    public void lanceJeu(ActionEvent actionEvent) {
    	
    	System.out.println("Lacement Du jeu");

    }
    
    
    
    
    
    
    
    
}