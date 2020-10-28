package com.app.staycured.Object;

public class MedicationObject {
    String name;
    String dosage;
    String durationFrom;
    String durationTo;
    String frequency;
    String rdFood;

    public MedicationObject(String name, String dosage, String durationFrom, String durationTo, String frequency,String rdFood) {
        this.name = name;
        this.dosage = dosage;
        this.durationFrom = durationFrom;
        this.durationTo = durationTo;
        this.frequency = frequency;
        this.rdFood = rdFood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDurationFrom() {
        return durationFrom;
    }

    public void setDurationFrom(String durationFrom) {
        this.durationFrom = durationFrom;
    }

    public String getDurationTo() {
        return durationTo;
    }

    public void setDurationTo(String durationTo) {
        this.durationTo = durationTo;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getRdFood() {
        return rdFood;
    }

    public void setRdFood(String rdFood) {
        this.rdFood = rdFood;
    }
}
