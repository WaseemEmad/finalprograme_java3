/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.PatientController;

import Model.AppointmentsModel.Appointments;
import Model.AppointmentsModel.AppointmentsJpaController;
import Model.BookedAppointments.BookedAppointments;
import Model.DB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author PC-ASUS
 */
public class PatientDashboardPageController implements Initializable {
   private String pUsername;
   private String pPassword;
    private final ObservableList<Appointments> AppointmentList = FXCollections.observableArrayList();
    private final ObservableList<BookedAppointments> BookedAppointmentList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Appointments, String> appointment_date_col;
    @FXML
    private TableColumn<Appointments, String> appointment_day_col;
    @FXML
    private TableColumn<Appointments, String> appointment_time_col;
    @FXML
    private TableColumn<Appointments, String> status_col;
    @FXML
    private TableView<Appointments> FreeAppointmentsTV;
    @FXML
    private Button ShowAllFree_BTN;
    @FXML
    private Button Book_BTN;
    @FXML
    private TableView<BookedAppointments> BookedTV;
    @FXML
    private TableColumn<BookedAppointments, Integer> booked_id_col;
    @FXML
    private TableColumn<BookedAppointments, String> booked_status_col;
    @FXML
    private TableColumn<BookedAppointments, String> booked_drcomment_col;
    @FXML
    private Button showWaitingBTN;
    @FXML
    private Button showFinishedBTN;
    @FXML
    private Button logoutBTN;
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
             appointment_date_col.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
          appointment_day_col.setCellValueFactory(new PropertyValueFactory<>("appointmentDay"));
          appointment_time_col.setCellValueFactory(new PropertyValueFactory<>("appointmentTime"));
          status_col.setCellValueFactory(new PropertyValueFactory<>("status"));
          FreeAppointmentsTV.setItems(AppointmentList);
          
          booked_id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
          booked_status_col.setCellValueFactory(new PropertyValueFactory<>("status"));
          booked_drcomment_col.setCellValueFactory(new PropertyValueFactory<>("doctorComment"));
          BookedTV.setItems(BookedAppointmentList);
          
          pUsername = PatientLoginPageController.loginUsername;
          pPassword = PatientLoginPageController.loginPassword;
          System.out.println(pUsername);
          System.out.println(pPassword);
    }    

    @FXML
    private void ShowAllFree_BTN_Handler(ActionEvent event) {
              AppointmentList.clear();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinic_appointmentsPU");
    AppointmentsJpaController appointmentsController = new AppointmentsJpaController(emf);
    List<Appointments> apposList = appointmentsController.getEntityManager()
            .createQuery("SELECT a FROM Appointments a WHERE a.status = 'free' ", Appointments.class)
            .getResultList();
    
    AppointmentList.addAll(apposList);
    FreeAppointmentsTV.setItems(AppointmentList);
    }

    @FXML
    private void Book_BTN_Handler(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(FreeAppointmentsTV.getSelectionModel().getSelectedItem() != null){
           Connection conn = DB.getInstance().getConnection(); 
               
            Appointments selectedAppointment = FreeAppointmentsTV.getSelectionModel().getSelectedItem();   
            PreparedStatement pstmt = conn.prepareStatement("UPDATE appointments SET status = 'booked' WHERE id = ?"); 
            pstmt.setInt(1, selectedAppointment.getId());
            pstmt.executeUpdate();
            
            
            String sql = "SELECT users.id FROM users WHERE users.username = '" + PatientLoginPageController.loginUsername +
            "' AND users.password = '" + PatientLoginPageController.loginPassword + "'";
            
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if(rs.next()){
               String sql2 = "INSERT INTO booked_appointments (appointment_id, user_id, status) "
               + "VALUES (" + FreeAppointmentsTV.getSelectionModel().getSelectedItem().getId() + ", " + rs.getInt("id") + ", 'waiting')";
                Statement stat2 = conn.createStatement();
                stat2.executeUpdate(sql2);
            }
            
          
        }
    }

    @FXML
    private void showWaitingBTN_Handler(ActionEvent event) {
        BookedAppointmentList.clear();
        String sql = "SELECT * " +
        "FROM booked_appointments " +
        "INNER JOIN users ON booked_appointments.user_id = users.id " +
        "WHERE users.username = ? AND users.password = ? AND booked_appointments.status = 'waiting'";

try (Connection conn = DB.getInstance().getConnection();
     PreparedStatement pstmt = conn.prepareStatement(sql)) {
    pstmt.setString(1, pUsername);
    pstmt.setString(2, pPassword);
    ResultSet rs = pstmt.executeQuery();
    while(rs.next()){
      BookedAppointments ba = new BookedAppointments();
      ba.setId(rs.getInt("id"));
      ba.setStatus(rs.getString("status"));
      ba.setDoctorComment(rs.getString("doctor_comment"));
      BookedAppointmentList.add(ba);
    }
} catch (SQLException | ClassNotFoundException ex) {
    System.out.println(ex.getMessage() + " : Error");
}

        
        
        BookedTV.setItems(BookedAppointmentList);
    }

    @FXML
    private void showFinishedBTN_Handler(ActionEvent event) {
             BookedAppointmentList.clear();
        String sql = "SELECT * " +
        "FROM booked_appointments " +
        "INNER JOIN users ON booked_appointments.user_id = users.id " +
        "WHERE users.username = ? AND users.password = ? AND booked_appointments.status = 'finished'";

try (Connection conn = DB.getInstance().getConnection();
     PreparedStatement pstmt = conn.prepareStatement(sql)) {
    pstmt.setString(1, pUsername);
    pstmt.setString(2, pPassword);
    ResultSet rs = pstmt.executeQuery();
    while(rs.next()){
      BookedAppointments ba = new BookedAppointments();
      ba.setId(rs.getInt("id"));
      ba.setStatus(rs.getString("status"));
      ba.setDoctorComment(rs.getString("doctor_comment"));
      BookedAppointmentList.add(ba);
    }
} catch (SQLException | ClassNotFoundException ex) {
    System.out.println(ex.getMessage() + " : Error");
}

        
        
        BookedTV.setItems(BookedAppointmentList);
    }

    @FXML
    private void logoutBTN_Handler(ActionEvent event) throws IOException {
        View.ViewManager.closePatientDashboardPage();
        View.ViewManager.openPatientLoginPage();
    }
    
    
    
    
    
}
