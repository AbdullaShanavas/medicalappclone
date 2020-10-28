package com.app.staycured.Object;

public class SpecialistObject {

    private int slno;
    private String drname;
    private String drfee;
    private String drrating;
    private int splst;

    public SpecialistObject(int splst, int slno, String drname, String drfee, String drrating) {
        this.slno = slno;
        this.drname = drname;
        this.drfee = drfee;
        this.drrating = drrating;
        this.splst = splst;
    }

    public int getSlno() {
        return slno;
    }

    public void setSlno(int slno) {
        this.slno = slno;
    }

    public String getDrname() {
        return drname;
    }

    public void setDrname(String drname) {
        this.drname = drname;
    }

    public String getDrfee() {
        return drfee;
    }

    public void setDrfee(String drfee) {
        this.drfee = drfee;
    }

    public String getDrrating() {
        return drrating;
    }

    public void setDrrating(String drrating) {
        this.drrating = drrating;
    }

    public int getSplst() {
        return splst;
    }

    public void setSplst(int splst) {
        this.splst = splst;
    }
}


