package com.app.staycured.Object;

public class MyImage {
    private String  path;



    public void setPath(String path) { this.path = path; }


    public String getPath() { return path; }

    @Override public String toString() {
        return "\nPath:" + path;
    }
}
