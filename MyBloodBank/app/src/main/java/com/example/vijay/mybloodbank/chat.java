package com.example.vijay.mybloodbank;

/**
 * Created by vijay on 12/4/2016.
 */
public class chat {
    String Name;
    String City;
    String Cell;
    String BloodGroup;
    String Age;

    public chat(){

    }

    public chat(String name,String city,String age,String cell,String group){
        this.Name=name;
        this.City=city;
        this.Age=age;
        this.BloodGroup=group;
        this.Cell=cell;

    }


    public String getName(){
        return Name;
    }

    public String getCell(){
        return Cell;
    }

    public String getCity(){
        return  City;
    }

    public String getBloodGroup(){
        return BloodGroup;

    }

    public String getAge(){
        return Age;
    }

}
