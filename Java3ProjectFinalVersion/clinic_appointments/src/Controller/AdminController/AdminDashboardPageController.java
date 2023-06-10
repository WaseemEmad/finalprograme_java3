/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.AdminController;

import Model.AppointmentsModel.Appointments;
import Model.AppointmentsModel.AppointmentsJpaController;
import Model.BookedAppointments.BookedAppointments;
import Model.BookedAppointments.BookedAppointmentsJpaController;
import Model.DB;
import Model.UsersModel.Users;
import Model.UsersModel.UsersJpaController;
import Model.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * FXML Controller class
 *
 * @author PC-ASUS
 */
public class AdminDashboardPageController extends Stage implements Initializable  {
   
    public static Users selectedPatientToUpdate;
    public static Appointments selectedAppointmentToUpdate;
    public static BookedAppointments selectedBookedAppointmentToUpdate;
    public static Stage updateStage;
    public static Stage AppointmentupdateStage;
    public static Stage BookedAppointmentupdateStage;
    private final ObservableList<Users> patientList = FXCollections.observableArrayList();
    private final ObservableList<Appointments> AppointmentList = FXCollections.observableArrayList();
    private final ObservableList<BookedAppointments> bookedAppointmentList = FXCollections.observableArrayList();
    
    @FXML
    private TableColumn<Users, String> pFirstnameCol;
    @FXML
    private TableColumn<Users, String> pLastnameCol;
    @FXML
    private TableColumn<Users, String> pAgeCol;
    @FXML
    private TableColumn<Users, String> pGenderCol;
    @FXML
    private TableColumn<Users, String> pPhoneCol;
    @FXML
    private TableColumn<Users, String> pEmailCol;
    @FXML
    private TableView<Users> patientTV;
    @FXML
    private Button showAllPatients_BTN;
    @FXML
    private Button createPatientBTN;
    @FXML
    private Button deletePatientBTN;
    @FXML
    private TextField firstName_SearchTF;
    @FXML
    private Button firstName_SearchBTN;
    @FXML
    private Button editPatient_BTN;
   
    
    @FXML
    private TableColumn<Appointments, String> appointment_date_col;
    @FXML
    private TableColumn<Appointments, String> appointment_day_col;
    @FXML
    private TableColumn<Appointments, String> appointment_time_col;
    @FXML
    private TableView<Appointments> AppointmentsTV;
    @FXML
    private TableColumn<Appointments, String> status_col;
    @FXML
    private Button showAllAppointments_BTN;
    @FXML
    private Button createAppointment_BTN;
    @FXML
    private Button editAppointmentBTN;
    @FXML
    private Button deleteAppointmentBTN;
    
   
  
   
    @FXML
    private Button logoutBTN;
    
    @FXML
    private TableView<BookedAppointments> bookedTV;
    @FXML
    private TableColumn<BookedAppointments, Integer> booked_id_col;
    @FXML
    private TableColumn<BookedAppointments, String> booked_status_col;
    @FXML
    private TableColumn<BookedAppointments, String> booked_dComment_col;
    @FXML
    private TextField bookedSearchTF;
    @FXML
    private Button bookedSearchBTN;
    @FXML
    private Button showAllBooked_BTN;
    @FXML
    private Button bookedAction_BTN;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       
          
       
          pFirstnameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
          pLastnameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
          pAgeCol.setCellValueFactory(new PropertyValueFactory<>("age"));
          pGenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
          pPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
          pEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
          patientTV.setItems(patientList);
          
          
             appointment_date_col.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
          appointment_day_col.setCellValueFactory(new PropertyValueFactory<>("appointmentDay"));
          appointment_time_col.setCellValueFactory(new PropertyValueFactory<>("appointmentTime"));
          status_col.setCellValueFactory(new PropertyValueFactory<>("status"));
          AppointmentsTV.setItems(AppointmentList);
          
          booked_id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
          booked_status_col.setCellValueFactory(new PropertyValueFactory<>("status"));
          booked_dComment_col.setCellValueFactory(new PropertyValueFactory<>("doctorComment"));
          bookedTV.setItems(bookedAppointmentList);
    
          

          
 

        
                 
    }
    
    







   @FXML
    private void showAllPatientsBTN_Handler(ActionEvent event) {
        patientList.clear();
         EntityManagerFactory emf =  Persistence.createEntityManagerFactory("clinic_appointmentsPU");
        UsersJpaController usersController = new UsersJpaController(emf);
        List<Users> userList = usersController.getEntityManager().createQuery("SELECT u FROM Users u WHERE u.role = 'patient'", Users.class).getResultList();
       patientList.addAll(userList);
       patientTV.setItems(patientList);
    }
    
   
    
    
    @FXML
    private void createPatientBTN_Handler(ActionEvent event) throws IOException {
        patientList.clear();
        View.ViewManager.closeAdminDashboardPage();
        View.ViewManager.openCreatePatient();
      
       
        
         
    }

  

  @FXML
private void deletePatientBTN_Handler(ActionEvent event) throws NonexistentEntityException {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinic_appointmentsPU");

    
    Users selectedPatient = patientTV.getSelectionModel().getSelectedItem();

    if (selectedPatient != null) {
        
        int patientId = selectedPatient.getId();

       
        UsersJpaController usersController = new UsersJpaController(emf);

        try {
           
            usersController.destroy(patientId);
            System.out.println("Patient with ID " + patientId + " has been deleted.");
            patientList.clear();
        } catch (NonexistentEntityException e) {
            System.out.println("Error deleting patient: " + e.getMessage());
        }
    } else {
        System.out.println("No patient selected.");
    }
}

 @FXML
private void firstName_SearchBTN_Handler(ActionEvent event) {
    if( !(firstName_SearchTF.getText()).isEmpty()  ){
    patientList.clear();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinic_appointmentsPU");
    UsersJpaController usersController = new UsersJpaController(emf);
    List<Users> userList = usersController.getEntityManager()
            .createQuery("SELECT u FROM Users u WHERE u.firstname LIKE '%" + firstName_SearchTF.getText().toLowerCase() + "%' AND u.role = 'patient'", Users.class)
            .getResultList();
    patientList.addAll(userList);
    patientTV.setItems(patientList);
    }else{patientList.clear(); System.out.println("Please Enter a First Name To Search For.");}
}

    @FXML
    private void editPatient_BTN_Handler(ActionEvent event) throws IOException {
        if(patientTV.getSelectionModel().getSelectedItem() != null){
        selectedPatientToUpdate = patientTV.getSelectionModel().getSelectedItem();
          FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/View/AdminView/EditPatient.fxml"));
        Parent rootUpdate = loaderUpdate.load();     
        Scene updateUserScene = new Scene(rootUpdate); 
        updateStage = new Stage();
        updateStage.setScene(updateUserScene);
        updateStage.setTitle("Edit Patient " + selectedPatientToUpdate.getFirstname() + " " + selectedPatientToUpdate.getLastname());
        updateStage.show();
       
        }
    }



    @FXML
    private void showAllAppointments_BTN_Handler(ActionEvent event) {
          AppointmentList.clear();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinic_appointmentsPU");
    AppointmentsJpaController appointmentsController = new AppointmentsJpaController(emf);
    List<Appointments> apposList = appointmentsController.getEntityManager()
            .createQuery("SELECT a FROM Appointments a ", Appointments.class)
            .getResultList();
    
    AppointmentList.addAll(apposList);
    AppointmentsTV.setItems(AppointmentList);
    }

    @FXML
    private void createAppointment_BTN_Handler(ActionEvent event) throws IOException {
        AppointmentList.clear();
        View.ViewManager.closeAdminDashboardPage();
        View.ViewManager.openCreateAppointment();
        
    }

    @FXML
    private void editAppointmentBTN_Handler(ActionEvent event) throws IOException {
            if(AppointmentsTV.getSelectionModel().getSelectedItem() != null){
        selectedAppointmentToUpdate = AppointmentsTV.getSelectionModel().getSelectedItem();
          FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/View/AdminView/editAppointment.fxml"));
        Parent rootUpdate = loaderUpdate.load();     
        Scene updateAppointmentScene = new Scene(rootUpdate); 
        AppointmentupdateStage = new Stage();
        AppointmentupdateStage.setScene(updateAppointmentScene);
        AppointmentupdateStage.setTitle("Edit Appointment " + selectedAppointmentToUpdate.getAppointmentDate());
        AppointmentupdateStage.show();
       
        }
        
    }

    @FXML
    private void deleteAppointmentBTN_Handler(ActionEvent event) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinic_appointmentsPU");

    
    Appointments selectedAppointment = AppointmentsTV.getSelectionModel().getSelectedItem();

    if (selectedAppointment != null) {
        
        int appointmentId = selectedAppointment.getId();

       
        AppointmentsJpaController appointmentsController = new AppointmentsJpaController(emf);

        try {
           
            appointmentsController.destroy(appointmentId);
            System.out.println("Appointment with ID " + appointmentId + " has been deleted.");
            AppointmentList.clear();
        } catch (NonexistentEntityException e) {
            System.out.println("Error deleting Appointment: " + e.getMessage());
        }
    } else {
        System.out.println("No appointment selected.");
    }
    }


    @FXML
    private void logoutBTN_Handler(ActionEvent event) throws IOException {
        View.ViewManager.closeAdminDashboardPage();
        View.ViewManager.openAdminLoginPage();
    }

    @FXML
    private void bookedSearchBTN_Handler(ActionEvent event) throws SQLException, ClassNotFoundException {
         if( !(bookedSearchTF.getText()).isEmpty()  ){
    bookedAppointmentList.clear();
    Connection conn = DB.getInstance().getConnection();
    String sql = "SELECT booked_appointments.id,booked_appointments.status,booked_appointments.doctor_comment FROM booked_appointments INNER JOIN users\n" +
"ON booked_appointments.user_id = users.id\n" +
"WHERE users.firstname LIKE lower('%"+bookedSearchTF.getText()+"%')";
Statement stat = conn.createStatement();
ResultSet rs = stat.executeQuery(sql);
while(rs.next()){
   BookedAppointments ba = new BookedAppointments();
   ba.setId(rs.getInt("id"));
   ba.setStatus(rs.getString("status"));
   ba.setDoctorComment(rs.getString("doctor_comment"));
   List<BookedAppointments> bookedAppointments = new ArrayList<>();
   bookedAppointments.add(ba);
   bookedAppointmentList.addAll(bookedAppointments);
   bookedTV.setItems(bookedAppointmentList);
}
    }else{
             bookedAppointmentList.clear(); 
             System.out.println("Please Enter a First Name To Search For.");}
    }

    @FXML
    private void showAllBooked_BTN_Handler(ActionEvent event) {
          bookedAppointmentList.clear();
         EntityManagerFactory emf =  Persistence.createEntityManagerFactory("clinic_appointmentsPU");
        BookedAppointmentsJpaController bAppointmentsController = new BookedAppointmentsJpaController(emf);
        List<BookedAppointments> bookedAppointments = bAppointmentsController.getEntityManager().createQuery("SELECT b FROM BookedAppointments b", BookedAppointments.class).getResultList();
       bookedAppointmentList.addAll(bookedAppointments);
      bookedTV.setItems(bookedAppointmentList);


    }

    @FXML
    private void bookedAction_BTN_Handler(ActionEvent event) throws IOException {
               if(bookedTV.getSelectionModel().getSelectedItem() != null){
        selectedBookedAppointmentToUpdate = bookedTV.getSelectionModel().getSelectedItem();
          FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/View/AdminView/bookedActionPage.fxml"));
        Parent rootUpdate = loaderUpdate.load();     
        Scene updateBookedAppointment = new Scene(rootUpdate); 
        BookedAppointmentupdateStage = new Stage();
        BookedAppointmentupdateStage.setScene(updateBookedAppointment);
        BookedAppointmentupdateStage.setTitle("Take Action On Booked Appointment " + selectedBookedAppointmentToUpdate.getId());
        BookedAppointmentupdateStage.show();
       
        }
    }

 
  
    
    

}




    
    


    
 

  
  
    
    
    
    

