package com.cmdb.gestionconsultation.controllers;

import com.cmdb.gestionconsultation.dao.ConsultationDao;
import com.cmdb.gestionconsultation.dao.PatientDao;
import com.cmdb.gestionconsultation.entities.Consultation;
import com.cmdb.gestionconsultation.entities.Patient;
import com.cmdb.gestionconsultation.services.CabinetServiceImpl;
import com.cmdb.gestionconsultation.services.ICabinetService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConsultationController  implements Initializable {
    @FXML private DatePicker dateConsultations;
    @FXML private ComboBox<Patient> comboPatient;
    @FXML private TextArea textAreaDescription;
    @FXML private TableView<Consultation> tableViewConsultation;
    @FXML private TableColumn<Consultation, Long> idConsultatiom;
    @FXML private TableColumn<Consultation, Date> dateConsultation;
    @FXML private TableColumn<Consultation, String> descConsultation;
    @FXML private TableColumn<Consultation, Patient> patientConsultation;
    private Consultation selectedConsultation;

    ICabinetService cabinetService;
    ObservableList<Patient> patients = FXCollections.observableArrayList();
    ObservableList<Consultation> consultations = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cabinetService=new CabinetServiceImpl(new PatientDao(),new ConsultationDao());
        comboPatient.setItems(patients);
        idConsultatiom.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateConsultation.setCellValueFactory(new PropertyValueFactory<>("date_consultation"));
        descConsultation.setCellValueFactory(new PropertyValueFactory<>("description"));
        patientConsultation.setCellValueFactory(new PropertyValueFactory<>("patient"));

        loadConsultations();
        try {

            tableViewConsultation.setItems(consultations);
            patients.setAll(cabinetService.getPatients());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tableViewConsultation.getSelectionModel().selectedItemProperty().addListener((observable, oldConsultation, newConsultation) -> {
           if (newConsultation != null) {
               textAreaDescription.setText(newConsultation.getDescription());
               dateConsultations.setValue(newConsultation.getDate_consultation().toLocalDate());
               comboPatient.setValue(newConsultation.getPatient());
               selectedConsultation = newConsultation;
           }
        });

    }

    public void addConsultation() {
        Consultation consultation = new Consultation();
        consultation.setDescription(textAreaDescription.getText());
        consultation.setDate_consultation(Date.valueOf(dateConsultations.getValue()));
        consultation.setPatient(comboPatient.getSelectionModel().getSelectedItem());
        try {
            cabinetService.addConsultation(consultation);
            loadConsultations();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void deleteCosultation(){
        Consultation consultation = tableViewConsultation.getSelectionModel().getSelectedItem();

        cabinetService.deleteConsultation(consultation);
        loadConsultations();
    }

    public void updateConsultation(){
        selectedConsultation.setDate_consultation(Date.valueOf(dateConsultations.getValue()));
        selectedConsultation.setPatient(comboPatient.getSelectionModel().getSelectedItem());
        selectedConsultation.setDescription(textAreaDescription.getText());
        cabinetService.updateConsultation(selectedConsultation);
        loadConsultations();
    }

void loadConsultations() {
    try {
        consultations.setAll(cabinetService.getConsultations());
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
}
