package com.app.staycured.Object;

public class CommentsObject {
    private String comments;
    private String rating;
    private String patient;
    private String symptoms;
    private String description;
    private String time;
    private String date;
    private String drname;
    private String speciality;

    public CommentsObject(String comments, String rating, String patient, String symptoms, String description, String time, String date, String drname, String speciality) {
        this.comments = comments;
        this.rating = rating;
        this.patient = patient;
        this.symptoms = symptoms;
        this.description = description;
        this.time = time;
        this.date = date;
        this.drname = drname;
        this.speciality = speciality;
    }

    public CommentsObject() {

    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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
