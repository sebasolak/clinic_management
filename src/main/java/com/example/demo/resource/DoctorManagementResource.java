package com.example.demo.resource;

import com.example.demo.model.Doctor;
import com.example.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("management/api/v1/doctors")
public class DoctorManagementResource {

    private final DoctorService doctorService;

    @Autowired
    public DoctorManagementResource(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Doctor> getAllDoctors() {
        return doctorService.selectAllDoctors();
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void addNewDoctor(@RequestBody Doctor newDoctor) {
        doctorService.insertNewDoctor(UUID.randomUUID(), newDoctor);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{doctorId}"
    )
    public Doctor getDoctorById(@PathVariable("doctorId") UUID doctorId) {
        return doctorService.selectDoctorById(doctorId);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "{doctorId}"
    )
    public void updateDoctorById(@PathVariable("doctorId") UUID doctorId,
                                 @RequestBody Doctor updateDoctor) {
        doctorService.updateDoctorById(doctorId, updateDoctor);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{doctorId}"
    )
    public void deleteDoctorById(@PathVariable("doctorId") UUID doctorId) {
        doctorService.deleteDoctorById(doctorId);
    }


}
