/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.AdminController;

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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author PC-ASUS
 */
public class AdminLoginPageController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TextField admin_usernameTF;
    @FXML
    private PasswordField admin_passwordTF;
    @FXML
    private Button loginAsPatient;
    @FXML
    private Label statusLabel;
    @FXML
    private Button admin_LoginBTN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   

    @FXML
    private void loginAsPatient_Handler(ActionEvent event) throws IOException {
        ViewManager.closeAdminLoginPage();
        ViewManager.openPatientLoginPage();
    }

    @FXML
    private void admin_LoginBTN_Handler(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
            User user = new User();
        if(user.checkAdminCreds(admin_usernameTF.getText(), admin_passwordTF.getText())){
            ViewManager.closeAdminLoginPage();
            ViewManager.openAdminDashboardPage();
        }else{
            statusLabel.setText("Invalid username, password, or role.");
        }
        
    }
    
    
    
    
}
