package com.example.demo.service;

import com.example.demo.dao.PatientDao;
import com.example.demo.model.Gender;
import com.example.demo.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientDao patientDao;

    @Autowired
    public PatientService(@Qualifier("patientDao") PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public List<Patient> selectAllPatients(Optional<String> gender) {

        List<Patient> patients = patientDao.selectAllPatients();
        if (!gender.isPresent()) {
            return patients;
        }

        Gender theGender=Gender.valueOf(gender.get().toUpperCase());
        return patients.stream()
                .filter(patient -> patient.getGender().equals(theGender))
                .collect(Collectors.toList());

    }

    public int insertNewPatient(UUID patientId, Patient newPatient) {
        UUID patientUid = patientId == null ? UUID.randomUUID() : patientId;
        newPatient.setPatientId(patientUid);
        return patientDao.insertNewPatient(patientUid, newPatient);
    }

    public Patient selectPatientById(UUID patientId) {
        return patientDao.selectPatientById(patientId);
    }

    public int updatePatientById(UUID patientId, Patient updatePatient) {
        updatePatient.setPatientId(patientId);
        return patientDao.updatePatientById(patientId, updatePatient);
    }

    public int deletePatientById(UUID patientId) {
        return patientDao.deletePatientById(patientId);
    }
}
