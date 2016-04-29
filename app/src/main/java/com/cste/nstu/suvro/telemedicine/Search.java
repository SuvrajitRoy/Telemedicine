package com.cste06.nstu.suvro.telemedicine;

/**
 * Created by SUVRO on 24-04-16.
 */
public class Search {
    public int med_id;
    public String gen_name = "";
    public String indication = "";
    public String dosage = "";
    public String contraindication = "";
    public String sideEffect = "";
    public String action = "";
    public String size_price = "";
    public String med_name ="";
    public String com_name ="";

    public Search( int med_id,String gen_name, String indication, String dosage, String contraindication, String sideEffect, String action, String size_price, String med_name, String com_name) {
        this.med_id = med_id;
        this.gen_name = gen_name;
        this.indication = indication;
        this.dosage = dosage;
        this.contraindication = contraindication;
        this.sideEffect = sideEffect;
        this.action = action;
        this.size_price = size_price;
        this.med_name = med_name;
        this.com_name = com_name;
    }

    public Search(){

    }

    public int getMed_id() {
        return med_id;
    }

    public void setMed_id(int med_id) {
        this.med_id = med_id;
    }

    public String getGen_name() {
        return gen_name;
    }

    public void setGen_name(String gen_name) {
        this.gen_name = gen_name;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getContraindication() {
        return contraindication;
    }

    public void setContraindication(String contraindication) {
        this.contraindication = contraindication;
    }

    public String getSideEffect() {
        return sideEffect;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSize_price() {
        return size_price;
    }

    public void setSize_price(String size_price) {
        this.size_price = size_price;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }
}
