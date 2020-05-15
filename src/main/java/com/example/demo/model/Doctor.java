package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Doctor {

    private UUID doctorId;
    private final String name;
    private final String lastName;
    private final String speciality;


    public Doctor(UUID doctorId,
                  String name,
                  String lastName,
                  String speciality) {
        this.doctorId = doctorId;
        this.name = name;
        this.lastName = lastName;
        this.speciality = speciality;

    }

    public UUID getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(UUID doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getTitle() {
        return "Dr "+getName()+" "+getLastName()
                + " with speciality of "+getSpeciality();
    }


}
