/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.PatientController;

import Model.UsersModel.Users;
import Model.UsersModel.UsersJpaController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author PC-ASUS
 */
public class RegisterNewPatientController implements Initializable {

    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField firstnameTF;
    @FXML
    private TextField lastnameTF;
    @FXML
    private TextField ageTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField phoneTF;
    @FXML
    private RadioButton maleRadio;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private RadioButton femaleRadio;
    @FXML
    private Button createBTN;
    @FXML
    private Button cancelBTN;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void createBTN_Handler(ActionEvent event) throws IOException {
              EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinic_appointmentsPU");
        Users u = new Users(usernameTF.getText(), passwordTF.getText(), firstnameTF.getText(), lastnameTF.getText(), ageTF.getText(), 
          emailTF.getText(), 
          phoneTF.getText(),
          ((RadioButton)genderGroup.getSelectedToggle()).getText().toLowerCase());
        
        UsersJpaController usersController = new UsersJpaController(emf);
        usersController.create(u);
        View.ViewManager.openPatientLoginPage();
    }

    @FXML
    private void cancelBTN_Handler(ActionEvent event) throws IOException {
        View.ViewManager.openPatientLoginPage();
    }
    
}
