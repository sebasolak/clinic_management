package com.example.demo.dao;

import com.example.demo.model.Doctor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("doctorDao")
public class DoctorFakeDao implements DoctorDao {

    private final Map<UUID, Doctor> doctorDB;

    public DoctorFakeDao() {
        doctorDB = new HashMap<>();

        UUID docSavannahId = UUID.randomUUID();
        doctorDB.put(docSavannahId, new Doctor(docSavannahId,
                "Savannah", "Carroll",
                "Surgeon"));

        UUID docPriscillaId = UUID.randomUUID();
        doctorDB.put(docPriscillaId, new Doctor(docPriscillaId,
                "Priscilla", "Hernandez",
                "Internists"));

        UUID docAlfredId = UUID.randomUUID();
        doctorDB.put(docAlfredId, new Doctor(docAlfredId,
                "Alfred", "Simpson",
                "Oncologist"));


    }

    @Override
    public List<Doctor> selectAllDoctors() {
        return new ArrayList<>(doctorDB.values());
    }

    @Override
    public int insertNewDoctor(UUID doctorId, Doctor newDoctor) {
        newDoctor.setDoctorId(doctorId);
        doctorDB.put(doctorId, newDoctor);
        return 1;
    }

    @Override
    public Doctor selectDoctorById(UUID doctorId) {
        return doctorDB.get(doctorId);
    }

    @Override
    public int updateDoctorById(UUID doctorId, Doctor updateDoctor) {
        updateDoctor.setDoctorId(doctorId);
        doctorDB.put(doctorId, updateDoctor);
        return 1;
    }

    @Override
    public int deleteDoctorById(UUID doctorId) {
        doctorDB.remove(doctorId);
        return 1;
    }
}
