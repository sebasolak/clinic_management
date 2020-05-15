package com.example.demo.resource;

import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/patients")
public class PatientResource {

    private final PatientService patientService;

    @Autowired
    public PatientResource(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Patient> getAllPatients(@QueryParam("group")String gender) {
        return patientService.selectAllPatients(Optional.ofNullable(gender));
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void addNewPatient(@RequestBody Patient newPatient) {
        patientService.insertNewPatient(UUID.randomUUID(), newPatient);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{patientId}"
    )
    public Patient getPatientById(@PathVariable("patientId") UUID patientId) {
        return patientService.selectPatientById(patientId);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "{patientId}"
    )
    public void updatePatientById(@PathVariable("patientId") UUID patientId,
                                  @RequestBody Patient updatePatient) {
        patientService.updatePatientById(patientId, updatePatient);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{patientId}"
    )
    public void deletePatientById(@PathVariable("patientId") UUID patientId){
        patientService.deletePatientById(patientId);
    }



}
