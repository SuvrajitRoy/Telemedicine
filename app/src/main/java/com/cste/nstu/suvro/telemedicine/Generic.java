package com.cste.nstu.suvro.telemedicine;

/**
 * Created by SUVRO on 06-04-16.
 */
public class Generic {

    public int _id;
    public String gen_name = "";
    public String indication = "";
    public String dosage = "";
    public String contraindication = "";
    public String sideEffect = "";
    public String action = "";
    public String size_price = "";

    public Generic(int _id, String gen_name, String indication, String dosage, String contraindication, String sideEffect, String action, String size_price) {
        this._id = _id;
        this.gen_name = gen_name;
        this.indication = indication;
        this.dosage = dosage;
        this.contraindication = contraindication;
        this.sideEffect = sideEffect;
        this.action = action;
        this.size_price = size_price;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setGen_name(String gen_name) {
        this.gen_name = gen_name;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setContraindication(String contraindication) {
        this.contraindication = contraindication;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setSize_price(String size_price) {
        this.size_price = size_price;
    }

    public int get_id() {

        return _id;
    }

    public String getGen_name() {
        return gen_name;
    }

    public String getIndication() {
        return indication;
    }

    public String getDosage() {
        return dosage;
    }

    public String getContraindication() {
        return contraindication;
    }

    public String getSideEffect() {
        return sideEffect;
    }

    public String getAction() {
        return action;
    }

    public String getSize_price() {
        return size_price;
    }


    public Generic(){

    }
}
