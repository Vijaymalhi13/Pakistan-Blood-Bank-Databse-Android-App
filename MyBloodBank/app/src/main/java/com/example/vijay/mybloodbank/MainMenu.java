package com.example.vijay.mybloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainMenu extends AppCompatActivity implements View.OnClickListener{
    ImageView Donor;
    ImageView Request;
    ImageView Bank;
    ImageView Emergency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTitle(R.string.MainMenu);
        setContentView(R.layout.gird);
        Donor=(ImageView)findViewById(R.id.donate);
        Request=(ImageView)findViewById(R.id.request);
        Bank=(ImageView)findViewById(R.id.search);
        Emergency=(ImageView)findViewById(R.id.emergency);
        Donor.setOnClickListener(this);
        Request.setOnClickListener(this);
        Bank.setOnClickListener(this);
        Emergency.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==Donor){
            Intent activity=new Intent(getApplicationContext(),BecomeDonor.class);
            startActivity(activity);
        }
        else if(v==Request){

            Intent activity=new Intent(getApplicationContext(),RequestBlood.class);
            startActivity(activity);
        }
        if(v==Bank){
            Intent activity=new Intent(getApplicationContext(),Bank.class);
            startActivity(activity);
        }

        if(v==Emergency){
            Intent activity=new Intent(getApplicationContext(),EmergencyActivity.class);
            startActivity(activity);
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }
}
