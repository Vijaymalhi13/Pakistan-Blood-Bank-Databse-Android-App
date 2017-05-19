package com.example.vijay.mybloodbank;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.realtime.util.StringListReader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;




import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenerateRequest extends AppCompatActivity {


    String Name;
    String Contact;
    String Group;
    String City;
    ArrayList<String>list;
    ArrayList<String>list1;
    ArrayList<String>list2;
    ArrayList<String>list3;
    ListView listView;
    String name;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_generate_request);
         Name = getIntent().getStringExtra("Name");
         Contact=getIntent().getStringExtra("Contact");
         Group=getIntent().getStringExtra("BloodGroup");
         City=getIntent().getStringExtra("City");
         list=new ArrayList<String>();
         list1=new ArrayList<String>();
         list2=new ArrayList<String>();
         list3=new ArrayList<String>();
         listView=(ListView)findViewById(R.id.listView);


         Firebase.setAndroidContext(this);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User");
        ref.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {



                        for(DataSnapshot child:dataSnapshot.getChildren()){
                            chat obj=child.getValue(chat.class);

                            if(obj.getBloodGroup().equals(Group) && obj.getCity().equals(City)){
                                list.add(obj.getName());
                                list1.add(obj.getBloodGroup());
                                list2.add(obj.getCity());
                                list3.add(obj.getCell());
                            }

                        }


                        CustomListAdapter adapter=new CustomListAdapter(GenerateRequest.this, list.toArray(new String[list.size()]),list1.toArray(new String[list1.size()]),list2.toArray(new String[list2.size()]),list3.toArray(new String[list3.size()]));
                     //   ArrayAdapter<String>adapter= new ArrayAdapter<String>(GenerateRequest.this,android.R.layout.simple_list_item_1,list);
                        listView.setAdapter(adapter);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(GenerateRequest.this,"It is called",Toast.LENGTH_LONG).show();

                    }

                });




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
