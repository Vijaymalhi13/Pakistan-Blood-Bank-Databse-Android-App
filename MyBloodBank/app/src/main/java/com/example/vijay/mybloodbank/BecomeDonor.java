package com.example.vijay.mybloodbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BecomeDonor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinnergroup;
    Spinner spCity;
    Spinner Gender;
    Spinner Age;
    ProgressDialog progressDialog;
    EditText Name;
    EditText Cell;
    EditText City;
    Button Submit;
    String Blood;
    String city;
    String gender;
    String age;

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().getRoot();
    DatabaseReference root;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_donor);
        setTitle(R.string.Donor);

        spinnergroup=(Spinner)findViewById(R.id.group);
        spCity =(Spinner)findViewById(R.id.city);
        Gender=(Spinner)findViewById(R.id.gender);
        Age=(Spinner)findViewById(R.id.age);
        ArrayAdapter arrayAdapter=ArrayAdapter.createFromResource(this,R.array.group,android.R.layout.select_dialog_item);
        ArrayAdapter arrayAdapter1=ArrayAdapter.createFromResource(this,R.array.city,android.R.layout.select_dialog_item);
        ArrayAdapter arrayAdapter2=ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.select_dialog_item);
        ArrayAdapter arrayAdapter3=ArrayAdapter.createFromResource(this,R.array.age,android.R.layout.select_dialog_item);
        spinnergroup.setAdapter(arrayAdapter);
        spCity.setAdapter(arrayAdapter1);
        Gender.setAdapter(arrayAdapter2);
        Age.setAdapter(arrayAdapter3);
        spinnergroup.setOnItemSelectedListener(this);
        spCity.setOnItemSelectedListener(this);
        Gender.setOnItemSelectedListener(this);
        Age.setOnItemSelectedListener(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Registering...");
        Name=(EditText)findViewById(R.id.name);
        Cell=(EditText)findViewById(R.id.cell);
        Submit=(Button)findViewById(R.id.submit);
        root=FirebaseDatabase.getInstance().getReference().child("User");



        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String temp=root.push().getKey();
            final  DatabaseReference ref=root.child(temp);
                final Map<String,Object>map1=new HashMap<String, Object>();
                map1.put("Name",Name.getText().toString());
                map1.put("Cell",Cell.getText().toString());
                map1.put("City",city);
                map1.put("BloodGroup",Blood);
                map1.put("Gender",gender);
                map1.put("Age",age);
                progressDialog.show();

                Thread timerThread = new Thread(){
                    public void run(){
                        try{
                            sleep(3000);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }finally{

                            ref.updateChildren(map1);
                            progressDialog.dismiss();
                        }
                    }
                };
                timerThread.start();

            }
        });

    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner Parent=(Spinner) parent;
        if(Parent.getId() == R.id.group)
        {
            TextView textView=(TextView)view;
            Blood=textView.getText().toString();
            Toast.makeText(BecomeDonor.this,Blood,Toast.LENGTH_LONG).show();
        }
        else if(Parent.getId() == R.id.city)
        {
            TextView textView=(TextView)view;
            city=textView.getText().toString();
        }

        else if(Parent.getId() == R.id.city)
        {
            TextView textView=(TextView)view;
            gender=textView.getText().toString();
        }

        else if(Parent.getId() == R.id.age)
        {
            TextView textView=(TextView)view;
            age=textView.getText().toString();
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.action){
            Intent act=new Intent(getApplicationContext(),MainMenu.class);
            startActivity(act);
        }
        return super.onOptionsItemSelected(item);
    }
}
