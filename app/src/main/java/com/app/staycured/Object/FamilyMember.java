package com.app.staycured.Object;

public class FamilyMember {

    private String name;
    private String rlship;
    public FamilyMember (String name, String rlship) {
        this.name = name;
        this.rlship = rlship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRlship() {
        return rlship;
    }

    public void setRlship(String rlship) {
        this.rlship = rlship;
    }


}
