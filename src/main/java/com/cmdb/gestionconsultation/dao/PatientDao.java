package com.cmdb.gestionconsultation.dao;

import com.cmdb.gestionconsultation.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDao implements IPatientDao{
    @Override
    public void create(Patient patient) throws SQLException {
       Connection con = DBConnection.getConnection();
       PreparedStatement ps = con.prepareStatement("INSERT INTO patients(nom, prenoms, tel) VALUES (?,?,?)");
       ps.setString(1, patient.getNom());
       ps.setString(2, patient.getPrenoms());
       ps.setString(3, patient.getTel());
       ps.executeUpdate();
    }

    @Override
    public void update(Patient patient) throws SQLException {
    Connection con = DBConnection.getConnection();
    PreparedStatement ps = con.prepareStatement("UPDATE patients SET nom=?, prenoms=?, tel=? WHERE id_patients=?");
    ps.setString(1, patient.getNom());
    ps.setString(2, patient.getPrenoms());
    ps.setString(3, patient.getTel());
    ps.setLong(4, patient.getId());
    ps.executeUpdate();

    }

    @Override
    public void delete(Patient patient) throws SQLException {
      Connection con = DBConnection.getConnection();
      PreparedStatement ps = con.prepareStatement("DELETE FROM patients WHERE id_patients= ?");
        ps.setLong(1, patient.getId());
        ps.executeUpdate();
    }

    @Override
    public Patient findById(Long id) throws SQLException {
        Patient patient = new Patient();
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM patients WHERE id_patients=?");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            patient.setId(rs.getLong("id_patients"));
            patient.setNom(rs.getString("nom"));
            patient.setPrenoms(rs.getString("prenoms"));
            patient.setTel(rs.getString("tel"));
        }
        return patient;
    }


    @Override
    public List<Patient> findAll() throws SQLException {
        List<Patient> patients = new ArrayList<Patient>();
        Connection con = DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("SELECT * FROM patients");
        ResultSet rs=ps.executeQuery();
        while (rs.next()) {
            Patient patient = new Patient();
            patient.setId(rs.getLong("id_patients"));
            patient.setNom(rs.getString("nom"));
            patient.setPrenoms(rs.getString("prenoms"));
            patient.setTel(rs.getString("tel"));
            patients.add(patient);
        }

        return patients;

    }

    @Override
    public List<Patient> serchPatient(String query) throws SQLException {
        List<Patient> patients = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("SELECT * FROM patients WHERE  nom LIKE ? OR prenoms LIKE ?");
        ps.setString(1, "%"+query+"%");
        ps.setString(2, "%"+query+"%");
        ResultSet rs=ps.executeQuery();
        while (rs.next()) {
            Patient patient = new Patient();
            patient.setId(rs.getLong("id_patients"));
            patient.setNom(rs.getString("nom"));
            patient.setPrenoms(rs.getString("prenoms"));
            patient.setTel(rs.getString("tel"));
            patients.add(patient);
        }
        return patients;
    }
}
