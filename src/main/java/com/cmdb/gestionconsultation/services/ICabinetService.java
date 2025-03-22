package com.cmdb.gestionconsultation.services;

import com.cmdb.gestionconsultation.entities.Consultation;
import com.cmdb.gestionconsultation.entities.Patient;

import java.sql.SQLException;
import java.util.List;

public interface ICabinetService {
    void addPatient(Patient patient) throws SQLException;
    void updatePatient(Patient patient) throws SQLException;
    void deletePatient(Patient patient) throws SQLException;
    List<Patient> getPatients() throws SQLException;
    Patient getPatientById(Long id);
    List<Patient> serchPatientByQuery(String query) throws SQLException;
    void addConsultation(Consultation consultation) throws SQLException;
    void updateConsultation(Consultation consultation);
    void deleteConsultation(Consultation consultation);
    List<Consultation> getConsultations() throws SQLException;
    Consultation getConsultationById(int id);
}
