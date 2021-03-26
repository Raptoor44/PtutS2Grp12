package application;
	
import java.io.Console;
import java.io.InputStream;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 1920, 1080);
        primaryStage.setScene(scene);
        
        
        //icon
        InputStream iconStream = getClass().getResourceAsStream("..\\data\\duck.jpg");
        if(iconStream == null) {
        	System.out.println("pas trouvé");
        }else {
        	Image img = new Image(iconStream);
            primaryStage.getIcons().add(img);
            
        }
        
        
        
        primaryStage.show();

        
    }
	
	
	public static void main(String[] args) throws Exception {
		launch(args);
	
		
	}
}
