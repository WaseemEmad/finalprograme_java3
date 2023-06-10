/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.PatientController;

import Model.UsersModel.User;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author PC-ASUS
 */
public class PatientLoginPageController implements Initializable {

    @FXML
    private TextField patient_usernameTF;
    @FXML
    private PasswordField patient_passwordTF;
    @FXML
    private Button loginAsAdmin;
    @FXML
    private Button Patient_LoginBTN;
    @FXML
    private Label statusLabel;
    
   public static String loginUsername;
   public static String loginPassword;
    @FXML
    private Button newPatientRegisterBTN;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void loginAsAdmin_Handler(ActionEvent event) throws IOException {
        ViewManager.closePatientLoginPage();
        ViewManager.openAdminLoginPage();
    }

    @FXML
    private void Patient_LoginBTN_Handler(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
             User user = new User();
        if(user.checkPatientCreds(patient_usernameTF.getText(), patient_passwordTF.getText())){
            loginUsername = patient_usernameTF.getText();
            loginPassword = patient_passwordTF.getText();
            ViewManager.closePatientLoginPage();
            ViewManager.openPatientDashboardPage();
            
        }else{
            statusLabel.setText("Invalid username, password, or role.");
           
        }
        
    }

    @FXML
    private void newPatientRegisterBTN_Handler(ActionEvent event) throws IOException {
        View.ViewManager.closePatientLoginPage();
       View.ViewManager.openPatientCreatePatient();
    }
    
}
