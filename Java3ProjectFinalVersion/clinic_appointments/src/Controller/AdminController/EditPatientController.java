/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.AdminController;

import Controller.AdminController.AdminDashboardPageController;
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
public class EditPatientController implements Initializable {
    private Users oldUser;
    @FXML
    private TextField EditusernameTF;
    @FXML
    private TextField EditpasswordTF;
    @FXML
    private TextField EditfirstnameTF;
    @FXML
    private TextField EditlastnameTF;
    @FXML
    private TextField EditageTF;
    @FXML
    private TextField EditemailTF;
    @FXML
    private TextField EditphoneTF;
    @FXML
    private RadioButton EditmaleRadio;
    @FXML
    private ToggleGroup EditGenderGroup;
    @FXML
    private RadioButton EditfemaleRadio;
    @FXML
    private Button EditBTN;
    @FXML
    private Button EditcancelBTN;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.oldUser = AdminDashboardPageController.selectedPatientToUpdate;
       EditusernameTF.setText(oldUser.getUsername());
       EditpasswordTF.setText(oldUser.getPassword());
       EditfirstnameTF.setText(oldUser.getFirstname());
       EditlastnameTF.setText(oldUser.getLastname());
       EditageTF.setText(oldUser.getAge());
       EditemailTF.setText(oldUser.getEmail());
       EditphoneTF.setText(oldUser.getPhone());
        if (oldUser.getGender().equals("female")) {
            EditGenderGroup.selectToggle(EditfemaleRadio);
        }
    }
  

  @FXML
private void EditBTN_Handler(ActionEvent event) throws Exception {
    String username = EditusernameTF.getText();
    String password = EditpasswordTF.getText();
    String firstname = EditfirstnameTF.getText();
    String lastname = EditlastnameTF.getText();
    String age = EditageTF.getText();
    String email = EditemailTF.getText();
    String phone = EditphoneTF.getText();
    String gender = ((RadioButton) EditGenderGroup.getSelectedToggle()).getText().toLowerCase();

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinic_appointmentsPU");
    UsersJpaController usersController = new UsersJpaController(emf);
     oldUser = usersController.findUsers(oldUser.getId());

    if (oldUser != null) {
        oldUser.setUsername(username);
        oldUser.setPassword(password);
        oldUser.setFirstname(firstname);
        oldUser.setLastname(lastname);
        oldUser.setAge(age);
        oldUser.setEmail(email);
        oldUser.setPhone(phone);
        oldUser.setGender(gender);

        try {
            usersController.edit(oldUser);
            oldUser = usersController.getEntityManager().merge(oldUser);
            System.out.println("User information updated successfully.");
        } catch (Exception ex) {
            System.out.println("Error updating user information: " + ex.getMessage());
        }
    } else {
        System.out.println("User not found.");
    }
}


    @FXML
    private void EditcancelBTN_Handler(ActionEvent event) throws IOException {
        AdminDashboardPageController.updateStage.close();
    }
    
}
