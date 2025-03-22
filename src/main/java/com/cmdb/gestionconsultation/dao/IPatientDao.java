package com.cmdb.gestionconsultation.dao;

import com.cmdb.gestionconsultation.entities.Patient;

import java.sql.SQLException;
import java.util.List;

public interface IPatientDao extends Dao<Patient, Long>{
    List<Patient> serchPatient(String query) throws SQLException;
}
