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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.acl.Group;
import java.util.ArrayList;

public class EmergencyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    ArrayList<String> list;
    ArrayList<String>list1;
    ArrayList<String>list2;
    ListView listView;
    Spinner City;
    String city;
    Button Request;
    EditText Name;
    EditText Contact;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        setTitle(R.string.Ttitl);

        Name=(EditText)findViewById(R.id.name);
        Contact=(EditText)findViewById(R.id.contact);
        City=(Spinner)findViewById(R.id.city);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Requesting...");
        Request=(Button)findViewById(R.id.request);
        ArrayAdapter arrayAdapter1=ArrayAdapter.createFromResource(this,R.array.city,android.R.layout.select_dialog_item);
        City.setAdapter(arrayAdapter1);
        City.setOnItemSelectedListener(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner Parent=(Spinner) parent;

         if(Parent.getId() == R.id.city)
        {
            TextView textView=(TextView)view;
            city=textView.getText().toString();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void sub(View v){
        final Intent intent=new Intent(getApplication(),AvailBanks.class);


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

}
