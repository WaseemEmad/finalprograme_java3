
package Model.UsersModel;

import Model.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class User {
  
    private int id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String age;
    private String email;
    private String phone;
    private String gender;
    private String role;

    public User(String username, String password, String firstname, String lastname, String age, String email, String phone, String gender, String role) {
        
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.role = role;
    }

    public User() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public boolean checkAdminCreds(String username, String password) throws SQLException, ClassNotFoundException {        
       Connection conn = DB.getInstance().getConnection();   
        String query = "SELECT COUNT(*) FROM users WHERE BINARY username = ? AND BINARY password = ? AND role = 'admin'";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
       
        resultSet.next();
        int count = resultSet.getInt(1);
        
        resultSet.close();
        statement.close();
        conn.close();
        
        return count > 0;
}
    
    public boolean checkPatientCreds(String username, String password) throws SQLException, ClassNotFoundException {        
       Connection conn = DB.getInstance().getConnection();   
        String query = "SELECT COUNT(*) FROM users WHERE BINARY username = ? AND BINARY password = ? AND role = 'patient'";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
       
        resultSet.next();
        int count = resultSet.getInt(1);
        
        resultSet.close();
        statement.close();
        conn.close();
        
        return count > 0;
}
    
    
    
    
    
}
