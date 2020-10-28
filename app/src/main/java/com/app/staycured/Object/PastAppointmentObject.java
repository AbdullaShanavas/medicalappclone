package com.app.staycured.Object;

public class PastAppointmentObject {
    private String name;
    private String drSpec;
    private String date;
    private String time;
    public PastAppointmentObject(String name, String drSpec, String date, String time) {
        this.name = name;
        this.drSpec = drSpec;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrSpec() {
        return drSpec;
    }

    public void setDrSpec(String drSpec) {
        this.drSpec = drSpec;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

