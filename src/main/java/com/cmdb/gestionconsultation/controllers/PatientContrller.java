package com.cmdb.gestionconsultation.controllers;

import com.cmdb.gestionconsultation.dao.ConsultationDao;
import com.cmdb.gestionconsultation.dao.PatientDao;
import com.cmdb.gestionconsultation.entities.Consultation;
import com.cmdb.gestionconsultation.entities.Patient;
import com.cmdb.gestionconsultation.services.CabinetServiceImpl;
import com.cmdb.gestionconsultation.services.ICabinetService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PatientContrller implements Initializable {

    @FXML private TextField textFieldNom;
    @FXML private TextField textFieldPrenoms;
    @FXML private TextField textFieldTel;
    @FXML private TextField textFieldRecherche;
    @FXML private TableView<Patient> tablePatients;
    @FXML private TableColumn<Patient, Long> columnId;
    @FXML private TableColumn<Patient, String> columnNom;
    @FXML private TableColumn<Patient, String> columnPrenoms;
    @FXML private TableColumn<Patient, String> columnTel;
    private ICabinetService cabinetService;
    private Patient selectedPatient;
    private ObservableList<Patient> patients= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cabinetService=new CabinetServiceImpl(new PatientDao(), new ConsultationDao());
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnPrenoms.setCellValueFactory(new PropertyValueFactory<>("prenoms"));
        columnTel.setCellValueFactory(new PropertyValueFactory<>("tel"));

        try {
            loadPatient()  ;
            tablePatients.setItems(patients);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        textFieldRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                patients.setAll(cabinetService.serchPatientByQuery(newValue));

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });

        tablePatients.getSelectionModel().selectedItemProperty().addListener((observable, oldPatient, newPatient) -> {
          if(newPatient!=null){
            textFieldNom.setText(newPatient.getNom());
            textFieldPrenoms.setText(newPatient.getPrenoms());
            textFieldTel.setText(newPatient.getTel());
            selectedPatient=newPatient;
          }
        });


    }
    public void addPatient()  {
          Patient patient = new Patient();
          patient.setNom(textFieldNom.getText());
          patient.setPrenoms(textFieldPrenoms.getText());
          patient.setTel(textFieldTel.getText());
        try
        {
            cabinetService.addPatient(patient);
            loadPatient();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deletePatient()  {
        Patient patient = tablePatients.getSelectionModel().getSelectedItem();
        try {
            cabinetService.deletePatient(patient);
            loadPatient();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadPatient() throws SQLException {
        patients.setAll(cabinetService.getPatients());

    }
    public void updatePatient()  {

        selectedPatient.setNom(textFieldNom.getText());
        selectedPatient.setPrenoms(textFieldPrenoms.getText());
        selectedPatient.setTel(textFieldTel.getText());
        try {
            cabinetService.updatePatient(selectedPatient);
            loadPatient();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

