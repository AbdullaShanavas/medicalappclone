package com.app.staycured.geoutils;


import android.graphics.Bitmap;
import android.widget.TextView;

public class SessionData {

    private static SessionData instance = null;
    private int Click;
    private int Pos;
    private String DrName;
    private String speciality;
    private String patientName;
    private String patientSymptoms;
    private String comments;
    private String rating;

    private String pastDrName;
    private String pastPatientName;
    private String pastPatientSymptoms;

    private String upDrName;
    private String upPatientName;
    private String upPatientSymptoms;

    private String strImage;
    private Bitmap bitmap;
    private TextView txtAppointment;

    private int editClick;
    private String  question;
    private int  Click1;



    public static SessionData getInstance() {
        if (instance == null) {
            instance = new SessionData();
        }
        return instance;
    }

    public int getPos() {
        return Pos;
    }

    public void setPos(int pos) {
        Pos = pos;
    }

    public int getClick() {
        return Click;
    }

    public void setClick(int click) {
        Click = click;
    }

    public String getDrName() {
        return DrName;
    }

    public void setDrName(String drName) {
        DrName = drName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getStrImage() {
        return strImage;
    }

    public void setStrImage(String strImage) {
        this.strImage = strImage;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public TextView getTxtAppointment() {
        return txtAppointment;
    }

    public void setTxtAppointment(TextView txtAppointment) {
        this.txtAppointment = txtAppointment;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSymptoms() {
        return patientSymptoms;
    }

    public void setPatientSymptoms(String patientSymptoms) {
        this.patientSymptoms = patientSymptoms;
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

    public String getPastDrName() {
        return pastDrName;
    }

    public void setPastDrName(String pastDrName) {
        this.pastDrName = pastDrName;
    }

    public String getPastPatientName() {
        return pastPatientName;
    }

    public void setPastPatientName(String pastPatientName) {
        this.pastPatientName = pastPatientName;
    }

    public String getPastPatientSymptoms() {
        return pastPatientSymptoms;
    }

    public void setPastPatientSymptoms(String pastPatientSymptoms) {
        this.pastPatientSymptoms = pastPatientSymptoms;
    }

    public String getUpDrName() {
        return upDrName;
    }

    public void setUpDrName(String upDrName) {
        this.upDrName = upDrName;
    }

    public String getUpPatientName() {
        return upPatientName;
    }

    public void setUpPatientName(String upPatientName) {
        this.upPatientName = upPatientName;
    }

    public String getUpPatientSymptoms() {
        return upPatientSymptoms;
    }

    public void setUpPatientSymptoms(String upPatientSymptoms) {
        this.upPatientSymptoms = upPatientSymptoms;
    }

    public int getEditClick() {
        return editClick;
    }

    public void setEditClick(int editClick) {
        this.editClick = editClick;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getClick1() {
        return Click1;
    }

    public void setClick1(int click1) {
        Click1 = click1;
    }
}



