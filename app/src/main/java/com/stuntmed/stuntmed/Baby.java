package com.stuntmed.stuntmed;

public class Baby {
    public String nik;
    public String name;
    public String date_of_birth;
    public String country;
    public String gender;
    public String address;

    public Baby(){

    }

    public Baby(String nik, String name, String date_of_birth, String country, String gender, String address){
        this.nik = nik;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.country = country;
        this.gender = gender;
        this.address = address;
    }
}
