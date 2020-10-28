package com.app.staycured.Object;

public class PatientDetailsObject {
    private String patient;
    private String symptoms;
    private String description;
    private String time;
    private String date;
    private String drname;
    private String speciality;

    public PatientDetailsObject(String patient, String symptoms, String description, String date, String time,String drname,String speciality) {
        this.patient = patient;
        this.symptoms = symptoms;
        this.description = description;
        this.time = time;
        this.date = date;
    }

    public PatientDetailsObject() {

    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDrname() {
        return drname;
    }

    public void setDrname(String drname) {
        this.drname = drname;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
