/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.AdminController;

import Model.AppointmentsModel.Appointments;
import Model.AppointmentsModel.AppointmentsJpaController;
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
public class CreateAppointmentController implements Initializable {

    @FXML
    private TextField app_dateTF;
    @FXML
    private TextField app_dayTF;
    @FXML
    private TextField app_timeTF;
    @FXML
    private RadioButton freeRadio;
    @FXML
    private ToggleGroup statusGroup;
    @FXML
    private RadioButton bookedRadio;
    @FXML
    private Button createApp_BTN;
    @FXML
    private Button cancelApp_BTN;

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
    private void createApp_BTN_Handler(ActionEvent event) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinic_appointmentsPU");
        Appointments appointment = new Appointments( app_dateTF.getText(), app_dayTF.getText()
                , app_timeTF.getText()
                , ((RadioButton)statusGroup.getSelectedToggle()).getText().toLowerCase() );
        AppointmentsJpaController appointmentsController = new AppointmentsJpaController(emf);
        appointmentsController.create(appointment);
    }

    @FXML
    private void cancelApp_BTN_Handler(ActionEvent event) throws IOException {
        View.ViewManager.openAdminDashboardPage();
    }
    
}
