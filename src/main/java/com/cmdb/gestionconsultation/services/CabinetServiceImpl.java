package com.cmdb.gestionconsultation.services;

import com.cmdb.gestionconsultation.dao.ConsultationDao;
import com.cmdb.gestionconsultation.dao.IConsultationDao;
import com.cmdb.gestionconsultation.dao.IPatientDao;
import com.cmdb.gestionconsultation.entities.Consultation;
import com.cmdb.gestionconsultation.entities.Patient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CabinetServiceImpl implements ICabinetService {
    private IPatientDao ipatientDao;
    private IConsultationDao iconsultationDao;

    public CabinetServiceImpl(IPatientDao ipatientDao, IConsultationDao iconsultationDao) {
        this.ipatientDao = ipatientDao;
        this.iconsultationDao = iconsultationDao;
    }

    @Override
    public void addPatient(Patient patient) throws SQLException {
        ipatientDao.create(patient);
    }

    @Override
    public void updatePatient(Patient patient) throws SQLException {
        ipatientDao.update(patient);
    }

    @Override
    public void deletePatient(Patient patient) throws SQLException {
        ipatientDao.delete(patient);
    }

    @Override
    public List<Patient> getPatients() throws SQLException {
        List<Patient> patients ;
        patients=ipatientDao.findAll();
        return patients;
    }

    @Override
    public Patient getPatientById(Long id)  {
        Patient patient=null;
        try {
            patient=ipatientDao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patient;
    }

    @Override
    public List<Patient> serchPatientByQuery(String query) throws SQLException {
        List<Patient> lespatiets;
        lespatiets=ipatientDao.serchPatient(query);
        return lespatiets;
    }

    @Override
    public void addConsultation(Consultation consultation) {
        try {
        iconsultationDao.create(consultation);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    @Override
    public void updateConsultation(Consultation consultation) {
        try {
            iconsultationDao.update(consultation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteConsultation(Consultation consultation) {

        try {
            iconsultationDao.delete(consultation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Consultation> getConsultations() throws SQLException {
        List<Consultation> consultations;
        consultations=iconsultationDao.findAll();
        return consultations;
    }

    @Override
    public Consultation getConsultationById(int id) {
        return null;
    }
}
