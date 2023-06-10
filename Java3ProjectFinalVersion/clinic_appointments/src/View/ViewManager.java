/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.AdminController.AdminDashboardPageController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author PC-ASUS
 */
public class ViewManager extends Stage {

    private ViewManager() {

    }
    public static Stage stage;
    public static Scene adminLoginScene;
    public static Scene patientLoginScene;
    public static Scene AdminDashboardScene;
    public static Scene PatientDashboardScene;
    public static Scene createPatientScene;
    public static Scene editPatientScene;
    public static Scene createAppointmentScene;
    public static Scene patient_CreatePatientScene;

    public static void openAdminLoginPage() throws IOException {
        if (adminLoginScene == null) {

            Parent root = FXMLLoader.load(ViewManager.class.getResource("/View/AdminView/AdminLoginPage.fxml"));
            adminLoginScene = new Scene(root);
            stage.setScene(adminLoginScene);
            stage.setTitle("Admin Login");
            stage.show();
        } else {
            stage.setScene(adminLoginScene);
            stage.show();
        }

    }

    public static void closeAdminLoginPage() {
        if (adminLoginScene != null) {
            stage.close();

        }
    }

    public static void openPatientLoginPage() throws IOException {
        if (patientLoginScene == null) {

            Parent root = FXMLLoader.load(ViewManager.class.getResource("/View/PatientView/PatientLoginPage.fxml"));
            patientLoginScene = new Scene(root);
            stage.setScene(patientLoginScene);
            stage.setTitle("Patient Login");
            stage.show();
        } else {
            stage.setScene(patientLoginScene);
            stage.show();
        }

    }

    public static void closePatientLoginPage() {
        if (patientLoginScene != null) {
            stage.close();
        }
    }

    public static void openAdminDashboardPage() throws IOException {
        if (AdminDashboardScene == null) {

            Parent root = FXMLLoader.load(ViewManager.class.getResource("/View/AdminView/AdminDashboardPage.fxml"));
            AdminDashboardScene = new Scene(root);
            stage.setScene(AdminDashboardScene);
            stage.setTitle("Admin Dashboard");
            stage.show();
        } else {
            stage.setScene(AdminDashboardScene);
            stage.show();
        }

    }

    public static void closeAdminDashboardPage() {
        if (AdminDashboardScene != null) {
            stage.close();

        }
    }

    public static void openPatientDashboardPage() throws IOException {
        if (PatientDashboardScene == null) {

            Parent root = FXMLLoader.load(ViewManager.class.getResource("/View/PatientView/PatientDashboardPage.fxml"));
            PatientDashboardScene = new Scene(root);
            stage.setScene(PatientDashboardScene);
            stage.setTitle("Patient Dashboard");
            stage.show();
        } else {
            stage.setScene(PatientDashboardScene);
            stage.show();
        }

    }

    public static void closePatientDashboardPage() {
        if (PatientDashboardScene != null) {
            stage.close();

        }
    }

    public static void openCreatePatient() throws IOException {
        if (createPatientScene == null) {

            Parent root = FXMLLoader.load(ViewManager.class.getResource("/View/AdminView/CreatePatient.fxml"));
            createPatientScene = new Scene(root);
            stage.setScene(createPatientScene);
            stage.setTitle("Create Patient");
            stage.show();
        } else {
            stage.setScene(createPatientScene);
            stage.show();
        }

    }

    public static void closeCreatePatientPage() {
        if (createPatientScene != null) {
            stage.close();

        }
    }

    public static void openEditPatient() throws IOException {
        if (editPatientScene == null) {

            Parent root = FXMLLoader.load(ViewManager.class.getResource("/View/AdminView/EditPatient.fxml"));
            editPatientScene = new Scene(root);
            stage.setScene(editPatientScene);
            stage.setTitle("Edit Patient");
            stage.show();
        } else {
            stage.setScene(editPatientScene);
            stage.show();
        }

    }

    public static void closeEditPatientPage() {
        if (editPatientScene != null) {
            stage.close();

        }
    }

    public static void openCreateAppointment() throws IOException {
        if (createAppointmentScene == null) {

            Parent root = FXMLLoader.load(ViewManager.class.getResource("/View/AdminView/createAppointment.fxml"));
            createAppointmentScene = new Scene(root);
            stage.setScene(createAppointmentScene);
            stage.setTitle("Create Appointment");
            stage.show();
        } else {
            stage.setScene(createAppointmentScene);
            stage.show();
        }

    }

    public static void closeCreateAppointment() {
        if (createAppointmentScene != null) {
            stage.close();

        }
    }

    public static void openPatientCreatePatient() throws IOException {
        if (patient_CreatePatientScene == null) {

            Parent root = FXMLLoader.load(ViewManager.class.getResource("/View/PatientView/registerNewPatient.fxml"));
            patient_CreatePatientScene = new Scene(root);
            stage.setScene(patient_CreatePatientScene);
            stage.setTitle("Register As New Patient");
            stage.show();
        } else {
            stage.setScene(patient_CreatePatientScene);
            stage.show();
        }

    }
    
      public static void closePatientCreatePatient() {
        if (patient_CreatePatientScene != null) {
            stage.close();

        }
    }


}
