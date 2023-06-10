/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.AdminController;

import Model.AppointmentsModel.Appointments;
import Model.AppointmentsModel.AppointmentsJpaController;
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
public class EditAppointmentController implements Initializable {
    private Appointments oldAppointment;
    @FXML
    private TextField Editapp_dateTF;
    @FXML
    private TextField Editapp_dayTF;
    @FXML
    private TextField Editapp_timeTF;
    @FXML
    private RadioButton EditfreeRadio;
    @FXML
    private ToggleGroup EditStatusGroup;
    @FXML
    private RadioButton EditbookedRadio;
    @FXML
    private Button EditApp_BTN;
    @FXML
    private Button cancelApp_BTN;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.oldAppointment = AdminDashboardPageController.selectedAppointmentToUpdate;
        Editapp_dateTF.setText(oldAppointment.getAppointmentDate());
        Editapp_dayTF.setText(oldAppointment.getAppointmentDay());
        Editapp_timeTF.setText(oldAppointment.getAppointmentTime());
          if (oldAppointment.getStatus().equals("booked")) {
            EditStatusGroup.selectToggle(EditbookedRadio);
        }
    }    

    @FXML
    private void EditApp_BTN_Handler(ActionEvent event) {
        String app_date = Editapp_dateTF.getText();
        String app_day = Editapp_dayTF.getText();
        String app_time = Editapp_timeTF.getText();
        String status = ((RadioButton) EditStatusGroup.getSelectedToggle()).getText().toLowerCase();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinic_appointmentsPU");
        AppointmentsJpaController appointmentsController = new AppointmentsJpaController(emf);
        oldAppointment = appointmentsController.findAppointments(oldAppointment.getId());
        if (oldAppointment != null) {
            oldAppointment.setAppointmentDate(app_date);
            oldAppointment.setAppointmentDay(app_day);
            oldAppointment.setAppointmentTime(app_time);
            oldAppointment.setStatus(status);
              try {
            appointmentsController.edit(oldAppointment);
            oldAppointment = appointmentsController.getEntityManager().merge(oldAppointment);
            System.out.println("Appointment information updated successfully.");
        } catch (Exception ex) {
            System.out.println("Error updating appointment information: " + ex.getMessage());
        }
        }else {
        System.out.println("Appointment not found.");
    }
        
    }

    @FXML
    private void cancelApp_BTN_Handler(ActionEvent event) {
        AdminDashboardPageController.AppointmentupdateStage.close();
    }
    
}
