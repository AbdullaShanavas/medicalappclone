package com.app.staycured.Object;

public class Doctorlist {
    private int serialNum;
    private String name;
    private String fee;
    private String rating;


    public Doctorlist( String name, String fee, String rating) {

        this.name = name;
        this.fee = fee;
        this.rating = rating;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
