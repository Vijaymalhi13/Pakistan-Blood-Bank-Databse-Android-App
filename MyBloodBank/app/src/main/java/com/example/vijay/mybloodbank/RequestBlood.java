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

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class RequestBlood extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner Group;
    Spinner City;
    String Blood;
    String city;
    Button Request;
    EditText Name;
    EditText Contact;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.Ttitl);
        setContentView(R.layout.activity_request_blood);
        Group=(Spinner)findViewById(R.id.group);
        Name=(EditText)findViewById(R.id.name);
        Contact=(EditText)findViewById(R.id.contact);
        City=(Spinner)findViewById(R.id.city);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Requesting...");
        Request=(Button)findViewById(R.id.request);
        ArrayAdapter arrayAdapter=ArrayAdapter.createFromResource(this,R.array.group,android.R.layout.select_dialog_item);
        ArrayAdapter arrayAdapter1=ArrayAdapter.createFromResource(this,R.array.city,android.R.layout.select_dialog_item);
        Group.setAdapter(arrayAdapter);
        City.setAdapter(arrayAdapter1);
        Group.setOnItemSelectedListener(this);
        City.setOnItemSelectedListener(this);


    }


    public void sub(View v){
       final Intent intent=new Intent(getApplication(),GenerateRequest.class);
        intent.putExtra("Name",Name.getText().toString());
        intent.putExtra("Contact",Contact.getText().toString());
        intent.putExtra("BloodGroup",Blood);
        intent.putExtra("City",city);
        progressDialog.show();

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{

                    startActivity(intent);
                    progressDialog.dismiss();
                }
            }
        };
        timerThread.start();




    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner Parent=(Spinner) parent;
        if(Parent.getId() == R.id.group)
        {
            TextView textView=(TextView)view;
            Blood=textView.getText().toString();

        }
        else if(Parent.getId() == R.id.city)
        {
            TextView textView=(TextView)view;
            city=textView.getText().toString();
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
