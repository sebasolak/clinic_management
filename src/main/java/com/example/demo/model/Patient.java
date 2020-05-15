package com.example.demo.model;

import java.time.LocalDate;
import java.util.UUID;

public class Patient {

    private UUID patientId;
    private final String name;
    private final String lastName;
    private final Gender gender;
    private final int age;
    private final BloodGroup bloodGroup;
    private final String disease;
    private final Treatment recommendedTreatment;
    private final String prognosis;


    public Patient(UUID patientId,
                   String name,
                   String lastName,
                   Gender gender,
                   int age,
                   BloodGroup bloodGroup,
                   String disease,
                   Treatment recommendedTreatment,
                   String prognosis) {
        this.patientId = patientId;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.disease = disease;
        this.recommendedTreatment = recommendedTreatment;
        this.prognosis = prognosis;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public String getDisease() {
        return disease;
    }

    public Treatment getRecommendedTreatment() {
        return recommendedTreatment;
    }

    public String getPrognosis() {
        return prognosis;
    }

    public String getFullName() {
        return name + " " + lastName;
    }

    public int getDateOfBirth() {
        return LocalDate.now().minusYears(age).getYear();
    }


}
