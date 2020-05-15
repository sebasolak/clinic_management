package com.example.demo.service;

import com.example.demo.dao.DoctorDao;
import com.example.demo.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {

    private final DoctorDao doctorDao;

    @Autowired
    public DoctorService(@Qualifier("doctorDao") DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    public List<Doctor> selectAllDoctors() {
        return doctorDao.selectAllDoctors();
    }

    public int insertNewDoctor(UUID doctorId, Doctor newDoctor) {
        UUID doctorUid = doctorId == null ? UUID.randomUUID() : doctorId;
        return doctorDao.insertNewDoctor(doctorUid, newDoctor);
    }

    public Doctor selectDoctorById(UUID doctorId) {
        return doctorDao.selectDoctorById(doctorId);
    }

    public int updateDoctorById(UUID doctorId, Doctor updateDoctor) {
        updateDoctor.setDoctorId(doctorId);
        return doctorDao.updateDoctorById(doctorId, updateDoctor);
    }

    public int deleteDoctorById(UUID doctorId) {
        return doctorDao.deleteDoctorById(doctorId);
    }

}
