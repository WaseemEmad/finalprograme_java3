/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.BookedAppointments;

import Model.AppointmentsModel.Appointments;
import Model.UsersModel.Users;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC-ASUS
 */
@Entity
@Table(name = "booked_appointments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookedAppointments.findAll", query = "SELECT b FROM BookedAppointments b")
    , @NamedQuery(name = "BookedAppointments.findById", query = "SELECT b FROM BookedAppointments b WHERE b.id = :id")
    , @NamedQuery(name = "BookedAppointments.findByStatus", query = "SELECT b FROM BookedAppointments b WHERE b.status = :status")
    , @NamedQuery(name = "BookedAppointments.findByDoctorComment", query = "SELECT b FROM BookedAppointments b WHERE b.doctorComment = :doctorComment")})
public class BookedAppointments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "status")
    private String status;
    @Column(name = "doctor_comment")
    private String doctorComment;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    @ManyToOne
    private Appointments appointmentId;
    

    public BookedAppointments() {
    }

    public BookedAppointments(Integer id) {
        this.id = id;
    }

   
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoctorComment() {
        return doctorComment;
    }

    public void setDoctorComment(String doctorComment) {
        this.doctorComment = doctorComment;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Appointments getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Appointments appointmentId) {
        this.appointmentId = appointmentId;
    }
    
  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookedAppointments)) {
            return false;
        }
        BookedAppointments other = (BookedAppointments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.BookedAppointments.BookedAppointments[ id=" + id + " ]";
    }
    
}
