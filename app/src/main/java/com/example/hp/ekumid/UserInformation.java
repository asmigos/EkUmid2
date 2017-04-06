package com.example.hp.ekumid;

/**
 * Created by hp on 27-03-2017.
 */

public class UserInformation {

    public String name;
    public String address;
    public String age;
    public String contact;
    public String blood_group;
    public String gender;


    public UserInformation(String name, String address, String age, String contact, String blood_group, String gender) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.contact = contact;
        this.blood_group = blood_group;
        this.gender = gender;
    }
}