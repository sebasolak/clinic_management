package com.example.demo.dao;

import com.example.demo.model.BloodGroup;
import com.example.demo.model.Patient;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static com.example.demo.model.BloodGroup.*;
import static com.example.demo.model.Gender.*;
import static com.example.demo.model.Treatment.*;
import static org.assertj.core.api.Assertions.assertThat;


public class PatientFakeDaoTest {

    private PatientFakeDao patientFakeDao;

    @Before
    public void setUp() throws Exception {
        patientFakeDao = new PatientFakeDao();

    }

    @Test
    public void shouldSelectAllPatients() {
        assertThat(patientFakeDao.selectAllPatients()).hasSize(8);

        Patient patient = patientFakeDao.selectAllPatients().get(0);

        assertThat(patient.getPatientId()).isInstanceOf(UUID.class);
        assertThat(patient.getName()).isNotNull();
        assertThat(patient.getLastName()).isNotNull();
        assertThat(patient.getGender()).isIn(MALE, FEMALE);
        assertThat(patient.getAge()).isBetween(21, 45);
        assertThat(patient.getBloodGroup())
                .isInstanceOf(BloodGroup.class);
        assertThat(patient.getRecommendedTreatment())
                .isIn(CHEMOTHERAPY, SURGERY, PHARMACOTHERAPY);
        assertThat(patient.getPrognosis()).isNotNull();
    }

    @Test
    public void shouldInsertNewPatient() {
        UUID patFelixId = UUID.randomUUID();
        Patient felix = new Patient(patFelixId,
                "Felix", "Welch",
                MALE, 33,
                AB_POS, "Kidney cancer",
                CHEMOTHERAPY, "75%");
        assertThat(patientFakeDao.selectAllPatients()).hasSize(8);

        patientFakeDao.insertNewPatient(patFelixId, felix);

        assertThat(patientFakeDao.selectAllPatients()).hasSize(9);
    }

    @Test
    public void shouldSelectPatientById() {
        UUID patFelixId = UUID.randomUUID();
        Patient felix = new Patient(patFelixId,
                "Felix", "Welch",
                MALE, 33,
                AB_POS, "Kidney cancer",
                CHEMOTHERAPY, "75%");


        patientFakeDao.insertNewPatient(patFelixId, felix);
        Patient felixMaybe = patientFakeDao.selectPatientById(patFelixId);
        assertThat(felixMaybe).isEqualToComparingFieldByField(felix);
    }

    @Test
    public void shouldNotSelectPatientByRandomUid() {
        Patient patient = patientFakeDao.selectPatientById(UUID.randomUUID());
        assertThat(patient).isNull();
    }

    @Test
    public void shouldUpdatePatientById() {
        UUID patFelixId = UUID.randomUUID();
        Patient felix = new Patient(patFelixId,
                "Felix", "Welch",
                MALE, 33,
                AB_POS, "Kidney cancer",
                CHEMOTHERAPY, "75%");

        patientFakeDao.insertNewPatient(patFelixId, felix);
        assertThat(patientFakeDao.selectPatientById(patFelixId))
                .isEqualToComparingFieldByField(felix);

        Patient vickie = new Patient(null,
                "Vickie", "Thomas",
                FEMALE, 38,
                B_NEG, "Breast cancer",
                CHEMOTHERAPY, "87%");

        patientFakeDao.updatePatientById(patFelixId, vickie);

        Patient update = patientFakeDao.selectPatientById(patFelixId);

        assertThat(update).isEqualToComparingFieldByField(vickie);

    }

    @Test
    public void shouldDeletePatientById() {
        UUID patFelixId = UUID.randomUUID();
        Patient felix = new Patient(patFelixId,
                "Felix", "Welch",
                MALE, 33,
                AB_POS, "Kidney cancer",
                CHEMOTHERAPY, "75%");
        patientFakeDao.insertNewPatient(patFelixId, felix);

        assertThat(patientFakeDao.selectAllPatients()).hasSize(9);
        assertThat(patientFakeDao.selectPatientById(patFelixId))
                .isEqualToComparingFieldByField(felix);

        int result = patientFakeDao.deletePatientById(patFelixId);

        assertThat(result).isOne();
        assertThat(patientFakeDao.selectAllPatients()).hasSize(8);
        assertThat(patientFakeDao.selectPatientById(patFelixId))
                .isNull();


    }
}