package com.cste06.nstu.suvro.telemedicine;

/**
 * Created by SUVRO on 11-04-16.
 */
public class Medicine {

    public int med_id;
    public String med_name ="";
    public String com_name ="";
    public String gen_name ="";

    public Medicine(int med_id, String med_name, String com_name,String gen_name) {
        this.med_id = med_id;
        this.med_name = med_name;
        this.com_name = com_name;
        this.gen_name = gen_name;
    }

    public Medicine(){

    }
    public int getMed_id() {
        return med_id;
    }

    public void setMed_id(int med_id) {
        this.med_id = med_id;
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

    public String getGen_name() {
        return gen_name;
    }

    public void setGen_name(String gen_name) {
        this.gen_name = gen_name;
    }
}
