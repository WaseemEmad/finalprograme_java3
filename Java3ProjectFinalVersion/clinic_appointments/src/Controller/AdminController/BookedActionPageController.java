/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.AdminController;

import Model.BookedAppointments.BookedAppointments;
import Model.BookedAppointments.BookedAppointmentsJpaController;
import Model.DB;
import Model.UsersModel.UsersJpaController;
import com.mysql.cj.protocol.Resultset;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class BookedActionPageController implements Initializable {
     private BookedAppointments oldBookedAppointment;
    @FXML
    private Label patient_name_label;
    @FXML
    private Label bAppointment_status_Label;
    @FXML
    private Label drComment_Label;
    @FXML
    private TextField drCommentTF;
    @FXML
    private RadioButton finishedRadio;
    @FXML
    private ToggleGroup statusWFGroup;
    @FXML
    private RadioButton waitingRadio;
    @FXML
    private Button saveActionBTN;
    @FXML
    private Button cancelBTN;
    @FXML
    private Label patient_gender_label;
    @FXML
    private Label patient_age_col;
    @FXML
    private Label app_date_col;
    
    
  @Override
public void initialize(URL url, ResourceBundle rb) {
    this.oldBookedAppointment = AdminDashboardPageController.selectedBookedAppointmentToUpdate;
    String sql = "SELECT concat(users.firstname, \" \", users.lastname) AS name, users.gender, users.age from users INNER JOIN booked_appointments\n" +
            "ON users.id = booked_appointments.user_id WHERE booked_appointments.id =" + oldBookedAppointment.getId();
    String sql2 = "SELECT concat(appointments.appointment_date,\" / \",appointments.appointment_day,\" / \",appointments.appointment_time) AS app FROM appointments INNER JOIN booked_appointments \n" +
            "ON appointments.id = booked_appointments.appointment_id WHERE booked_appointments.appointment_id =" + oldBookedAppointment.getAppointmentId().getId();

    try (Connection conn = DB.getInstance().getConnection();
         Statement stat = conn.createStatement();
         ResultSet rs = stat.executeQuery(sql);
         Statement stat2 = conn.createStatement();
         ResultSet rs2 = stat2.executeQuery(sql2)) {

        if (rs.next()) {
            patient_name_label.setText(rs.getString("name"));
            patient_gender_label.setText(rs.getString("gender"));
            patient_age_col.setText(rs.getString("age"));
            if (rs2.next()) {
                app_date_col.setText(rs2.getString("app"));
            }
        }
    } catch (SQLException | ClassNotFoundException ex) {
        System.out.println(ex.getMessage() + " : Error");
    }

    bAppointment_status_Label.setText(oldBookedAppointment.getStatus());
    drComment_Label.setText(oldBookedAppointment.getDoctorComment());
    drCommentTF.setText(oldBookedAppointment.getDoctorComment());
    if (oldBookedAppointment.getStatus().equals("finished")) {
        statusWFGroup.selectToggle(finishedRadio);
    } else {
        statusWFGroup.selectToggle(waitingRadio);
    }
}
  
   

    


    @FXML
    private void saveActionBTN_Handler(ActionEvent event) {
    String comment = drCommentTF.getText();
    String status = ((RadioButton) statusWFGroup.getSelectedToggle()).getText().toLowerCase();

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinic_appointmentsPU");
        BookedAppointmentsJpaController bappointmentController = new BookedAppointmentsJpaController(emf);
     oldBookedAppointment = bappointmentController.findBookedAppointments(oldBookedAppointment.getId());

    if (oldBookedAppointment != null) {
        oldBookedAppointment.setDoctorComment(comment);
        oldBookedAppointment.setStatus(status);
       

        try {
            bappointmentController.edit(oldBookedAppointment);
            oldBookedAppointment = bappointmentController.getEntityManager().merge(oldBookedAppointment);
            System.out.println("Appointment information updated successfully.");
        } catch (Exception ex) {
            System.out.println("Error updating appointment information: " + ex.getMessage());
        }
    } else {
        System.out.println("Appointment not found.");
    }
        
    }

    
    
    
    @FXML
    private void cancelBTN_Handler(ActionEvent event) {
        AdminDashboardPageController.BookedAppointmentupdateStage.close();
    }
 
    
}
