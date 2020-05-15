package com.example.demo.dao;

import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;

import java.util.List;
import java.util.UUID;

public interface DoctorDao {

    List<Doctor> selectAllDoctors();

    int insertNewDoctor(UUID doctorId, Doctor newDoctor);

    Doctor selectDoctorById(UUID doctorId);

    int updateDoctorById(UUID doctorId, Doctor updateDoctor);

    int deleteDoctorById(UUID doctorId);
}
