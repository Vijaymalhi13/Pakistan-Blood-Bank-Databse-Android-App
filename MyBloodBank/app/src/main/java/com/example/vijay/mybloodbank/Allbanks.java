package com.example.vijay.mybloodbank;

/**
 * Created by vijay on 12/8/2016.
 */
public class Allbanks {
    String BankName;
    String City;
    String Address;
    String Contact;

    public Allbanks(){

    }

    public Allbanks(String name,String city,String add,String cell){
        this.BankName=name;
        this.City=city;
        this.Address=add;
        this.Contact=cell;

    }


    public String getName(){
        return BankName;
    }

    public String getCell(){
        return Contact;
    }

    public String getCity(){
        return  City;
    }

    public String getAddress(){
        return Address;

    }



}
