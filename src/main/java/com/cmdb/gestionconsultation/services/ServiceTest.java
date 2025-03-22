package com.cmdb.gestionconsultation.services;

import com.cmdb.gestionconsultation.dao.ConsultationDao;
import com.cmdb.gestionconsultation.dao.PatientDao;
import com.cmdb.gestionconsultation.entities.Patient;

import java.sql.SQLException;
import java.util.List;

public class ServiceTest {
    public static void main(String[] args) throws SQLException {
        ICabinetService service=new CabinetServiceImpl(new PatientDao(), new ConsultationDao());
       /* Patient patient=new Patient();
        patient.setNom("Bob");
        patient.setPrenoms("Koffi");
        patient.setTel("12345786543");
        service.addPatient(patient);

        List<Patient> patients=service.getPatients();
        patients.forEach(patient->{System.out.println(patient.toString());});*/
        Patient patient=service.getPatientById(1L);
        patient.setNom("Yawovi Bakovi");
        service.updatePatient(patient);
       System.out.println(service.getPatientById(1L).toString());


    }
}
