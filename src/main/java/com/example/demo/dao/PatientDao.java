package com.example.demo.dao;

import com.example.demo.model.Patient;

import java.util.List;
import java.util.UUID;

public interface PatientDao {

    List<Patient> selectAllPatients();

    int insertNewPatient(UUID patientId, Patient newPatient);

    Patient selectPatientById(UUID patientId);

    int updatePatientById(UUID patientId, Patient updatePatient);

    int deletePatientById(UUID patientId);
}
