package com.cste.nstu.suvro.telemedicine;


public class Medicine {
    private String medName;
    private String genName;
    private String ind;
    private String dos;
    private String action;
    private String contra;
    private String side;
    private String company;
    private String price;


    public Medicine(String medName, String genName, String ind, String dos, String action, String contra, String side, String company,String price) {
        this.medName = medName;
        this.genName = genName;
        this.ind = ind;
        this.dos = dos;
        this.action = action;
        this.contra = contra;
        this.side = side;
        this.company=company;
        this.price = price;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getGenName() {
        return genName;
    }

    public void setGenName(String genName) {
        this.genName = genName;
    }

    public String getInd() {
        return ind;
    }

    public void setInd(String ind) {
        this.ind = ind;
    }

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
