package com.example.demo.dao;

import com.example.demo.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.example.demo.model.BloodGroup.*;
import static com.example.demo.model.Gender.FEMALE;
import static com.example.demo.model.Gender.MALE;
import static com.example.demo.model.Treatment.*;

@Repository("patientDao")
public class PatientFakeDao implements PatientDao {

    private final Map<UUID, Patient> patientDB;

    public PatientFakeDao() {
        patientDB = new HashMap<>();

        UUID patAdrianId = UUID.randomUUID();
        patientDB.put(patAdrianId, new Patient(patAdrianId,
                "Adrian", "Burns",
                MALE, 45,
                AB_POS, "Lung cancer",
                CHEMOTHERAPY, "40%"));

        UUID patTerranceId = UUID.randomUUID();
        patientDB.put(patTerranceId, new Patient(patTerranceId,
                "Terrance", "Jackson",
                MALE, 38,
                AB_NEG, "Glioma",
                SURGERY, "47%"));

        UUID patMarjorieId = UUID.randomUUID();
        patientDB.put(patMarjorieId, new Patient(patMarjorieId,
                "Marjorie", "Stephens",
                FEMALE, 23,
                A_POS, "Breast cancer",
                CHEMOTHERAPY, "91%"));

        UUID patCoryId = UUID.randomUUID();
        patientDB.put(patCoryId, new Patient(patCoryId,
                "Cory", "Edwards",
                MALE, 21,
                A_NEG, "Congenital heart defect",
                SURGERY, "69%"));

        UUID patMaeId = UUID.randomUUID();
        patientDB.put(patMaeId, new Patient(patMaeId,
                "Mae", "Young",
                FEMALE, 32,
                B_POS, "Brain tumor",
                SURGERY, "19%"));

        UUID patPatricId = UUID.randomUUID();
        patientDB.put(patPatricId, new Patient(patPatricId,
                "Patrick", "Matthews",
                MALE, 35,
                B_NEG, "Streptococcus pneumoniae",
                PHARMACOTHERAPY, "87.5%"));

        UUID patValerieId = UUID.randomUUID();
        patientDB.put(patValerieId, new Patient(patValerieId,
                "Valerie", "Bradley",
                FEMALE, 43,
                O_POS, "Pinealoblastoma",
                CHEMOTHERAPY, "50%"));

        UUID patIreneId = UUID.randomUUID();
        patientDB.put(patIreneId, new Patient(patIreneId,
                "Irene", "Hanson",
                FEMALE, 27,
                O_NEG, "Carney triad",
                CHEMOTHERAPY, "73%"));


    }


    @Override
    public List<Patient> selectAllPatients() {
        return new ArrayList<>(patientDB.values());
    }

    @Override
    public int insertNewPatient(UUID patientId, Patient newPatient) {
        newPatient.setPatientId(patientId);
        patientDB.put(patientId, newPatient);
        return 1;
    }

    @Override
    public Patient selectPatientById(UUID patientId) {
        return patientDB.get(patientId);
    }

    @Override
    public int updatePatientById(UUID patientId, Patient updatePatient) {
        updatePatient.setPatientId(patientId);
        patientDB.put(patientId, updatePatient);
        return 1;
    }

    @Override
    public int deletePatientById(UUID patientId) {
        patientDB.remove(patientId);
        return 1;
    }
}
