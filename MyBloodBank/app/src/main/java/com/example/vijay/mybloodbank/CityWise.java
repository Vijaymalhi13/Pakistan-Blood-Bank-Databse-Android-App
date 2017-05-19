package com.example.vijay.mybloodbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CityWise extends AppCompatActivity {

    String Group;
    String City;
    ArrayList<String> list;
    ArrayList<String>list2;
    ArrayList<String>list3;
    ArrayList<String>list4;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_wise);
        Group=getIntent().getStringExtra("BloodGroup");
        City=getIntent().getStringExtra("City");
        setTitle("CityWise Donor");
        list=new ArrayList<String>();
        list2=new ArrayList<String>();
        list3=new ArrayList<String>();
        list4=new ArrayList<String>();
        listView=(ListView)findViewById(R.id.listView);

        Firebase.setAndroidContext(this);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User");
        ref.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Toast.makeText(CityWise.this,"Name",Toast.LENGTH_LONG).show();

                        for(DataSnapshot child:dataSnapshot.getChildren()){
                            chat obj=child.getValue(chat.class);

                            if(obj.getBloodGroup().equals(Group) && obj.getCity().equals(City)){
                                list.add(obj.getName());
                                list2.add(obj.getBloodGroup());
                                list3.add(obj.getCity());
                                list4.add(obj.getCell());
                            }

                        }

                        CustomListAdapter adapter=new CustomListAdapter(CityWise.this, list.toArray(new String[list.size()]),list2.toArray(new String[list2.size()]),list3.toArray(new String[list3.size()]),list4.toArray(new String[list4.size()]));
                        //   ArrayAdapter<String>adapter= new ArrayAdapter<String>(GenerateRequest.this,android.R.layout.simple_list_item_1,list);
                        listView.setAdapter(adapter);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(CityWise.this,"It is called",Toast.LENGTH_LONG).show();

                    }

                });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }
}
