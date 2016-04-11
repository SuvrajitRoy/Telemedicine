package com.cste.nstu.suvro.telemedicine;


public class Doctor {
    public int doctor_id;
    public String doctor_name = "";
    public String designation = "";
    public String qualification = "";
    public String specialist = "";
    public String location = "";
    public String number = "";

    public Doctor(int doctor_id, String doctor_name, String designation, String qualification, String specialist, String location, String number) {
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.designation = designation;
        this.qualification = qualification;
        this.specialist = specialist;
        this.location = location;
        this.number = number;
    }

    public Doctor(){

    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}