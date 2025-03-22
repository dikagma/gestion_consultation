package com.cmdb.gestionconsultation.dao;

import com.cmdb.gestionconsultation.entities.Consultation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDao implements  IConsultationDao{
    @Override
    public void create(Consultation consultation) {
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO consultation(date_consultation, description, Id_patients) VALUES (?,?,?)");
            ps.setDate(1, consultation.getDate_consultation());
            ps.setString(2, consultation.getDescription());
            ps.setLong(3,consultation.getPatient().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Consultation consultation) throws SQLException {
    Connection con = DBConnection.getConnection();
    PreparedStatement ps = con.prepareStatement("UPDATE consultation SET date_consultation=?, description=?, Id_patients=? WHERE Id_consultation=?");
    ps.setDate(1, consultation.getDate_consultation());
    ps.setString(2, consultation.getDescription());
    ps.setLong(3, consultation.getPatient().getId());
    ps.setLong(4,consultation.getId() );
    ps.executeUpdate();
    }

    @Override
    public void delete(Consultation consultation) {
    Connection con = DBConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM consultation WHERE Id_consultation=?");
            ps.setLong(1, consultation.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Consultation findById(Long id) {
        return null;
    }

    @Override
    public List<Consultation> findAll() {
        List<Consultation> consultations = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement ps=con.prepareStatement("SELECT * FROM consultation");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Consultation consultation=new Consultation();
                consultation.setDate_consultation(rs.getDate("date_consultation"));
                consultation.setId(rs.getInt("Id_consultation"));
                consultation.setDescription(rs.getString("description"));
                consultation.setPatient(new PatientDao().findById(rs.getLong("Id_patients")));
                consultations.add(consultation);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return consultations;
    }
}
