package com.app.staycured.Object;

public class PaymentHistoryObject {
    private String docName;
    private String date;
    private String amount;

    public PaymentHistoryObject(String docName, String date, String amount) {
        this.docName = docName;
        this.date = date;
        this.amount = amount;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
