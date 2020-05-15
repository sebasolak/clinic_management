package com.example.demo.dao;

import com.example.demo.model.Doctor;
import org.junit.*;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


public class DoctorFakeDaoTest {

    private DoctorFakeDao doctorFakeDao;

    @Before
    public void setUp() throws Exception {
        doctorFakeDao = new DoctorFakeDao();
    }

    @Test
    public void shouldSelectAllDoctors() {
        assertThat(doctorFakeDao.selectAllDoctors()).hasSize(3);

        Doctor doctor = doctorFakeDao.selectAllDoctors().get(0);
        assertThat(doctor.getDoctorId()).isInstanceOf(UUID.class);
        assertThat(doctor.getName()).isIn("Savannah", "Priscilla", "Alfred");
        assertThat(doctor.getLastName()).isIn("Carroll", "Hernandez", "Simpson");
        assertThat(doctor.getSpeciality()).isIn("Surgeon", "Internists", "Oncologist");
    }

    @Test
    public void insertNewDoctor() {

        UUID docJenniferId = UUID.randomUUID();
        Doctor jennifer = new Doctor(docJenniferId,
                "Jennifer", "Alexander",
                "Cardiologist");

        assertThat(doctorFakeDao.selectAllDoctors()).hasSize(3);
        int result = doctorFakeDao.insertNewDoctor(docJenniferId, jennifer);
        assertThat(result).isOne();
        assertThat(doctorFakeDao.selectAllDoctors()).hasSize(4);
        assertThat(doctorFakeDao.selectDoctorById(docJenniferId))
                .isEqualToComparingFieldByField(jennifer);

    }

    @Test
    public void shouldSelectDoctorById() {

        UUID docJenniferId = UUID.randomUUID();
        Doctor jennifer = new Doctor(docJenniferId,
                "Jennifer", "Alexander",
                "Cardiologist");
        doctorFakeDao.insertNewDoctor(docJenniferId, jennifer);
        assertThat(doctorFakeDao.selectAllDoctors()).hasSize(4);

        Doctor jenniferMaybe = doctorFakeDao.selectDoctorById(docJenniferId);
        assertThat(jenniferMaybe).isEqualToComparingFieldByField(jennifer);

    }

    @Test
    public void shouldNotSelectDoctorByRandomId() {

        Doctor doctor = doctorFakeDao.selectDoctorById(UUID.randomUUID());
        assertThat(doctor).isNull();
    }

    @Test
    public void shouldUpdateDoctorById() {
        UUID docJenniferId = UUID.randomUUID();
        Doctor jennifer = new Doctor(docJenniferId,
                "Jennifer", "Alexander",
                "Cardiologist");
        doctorFakeDao.insertNewDoctor(docJenniferId, jennifer);
        assertThat(doctorFakeDao.selectAllDoctors()).hasSize(4);

        Doctor update = new Doctor(null,
                "Christina", "Johnston",
                "Neurologist");
        int result = doctorFakeDao.updateDoctorById(docJenniferId, update);

        assertThat(result).isOne();

        Doctor doctor = doctorFakeDao.selectDoctorById(docJenniferId);

        assertThat(update).isEqualToComparingFieldByField(doctor);


    }

    @Test
    public void deleteDoctorById() {

        UUID docJenniferId = UUID.randomUUID();
        Doctor jennifer = new Doctor(docJenniferId,
                "Jennifer", "Alexander",
                "Cardiologist");

        assertThat(doctorFakeDao.selectAllDoctors()).hasSize(3);

        int insertResult
                = doctorFakeDao.insertNewDoctor(docJenniferId, jennifer);
        assertThat(insertResult).isOne();
        assertThat(doctorFakeDao.selectAllDoctors()).hasSize(4);
        Doctor jenniferMaybe = doctorFakeDao.selectDoctorById(docJenniferId);
        assertThat(jenniferMaybe).isEqualToComparingFieldByField(jennifer);

        int deleteResult = doctorFakeDao.deleteDoctorById(docJenniferId);
        assertThat(deleteResult).isOne();
        assertThat(doctorFakeDao.selectAllDoctors()).hasSize(3);
        assertThat(doctorFakeDao.selectDoctorById(docJenniferId)).isNull();
    }
}