
package MainApp;

import View.ViewManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
    
     ViewManager.stage = stage;
     View.ViewManager.openPatientLoginPage();
       
    }

  
    public static void main(String[] args) {
        launch(args);
    }
    
}
