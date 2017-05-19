package com.example.vijay.mybloodbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AvailBanks extends AppCompatActivity {

    ArrayList<String> list;
    ArrayList<String>list1;
    ArrayList<String>list2;
    ArrayList<String>list3;
    ListView listView;
    String City;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avail_banks);
        City=getIntent().getStringExtra("City");
        list=new ArrayList<String>();
        list1=new ArrayList<String>();
        list2=new ArrayList<String>();
        list3=new ArrayList<String>();
        listView=(ListView)findViewById(R.id.listView);
        Firebase.setAndroidContext(this);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Banks");
        ref.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {



                        for(DataSnapshot child:dataSnapshot.getChildren()){
                            Allbanks obj=child.getValue(Allbanks.class);

                                list.add(obj.getName());
                                list1.add(obj.getAddress());
                                list2.add(obj.getCity());
                                list3.add(obj.getCell());



                        }


                        CustomListAdapter adapter=new CustomListAdapter(AvailBanks.this, list.toArray(new String[list.size()]),list1.toArray(new String[list1.size()]),list2.toArray(new String[list2.size()]),list3.toArray(new String[list3.size()]));
                        //   ArrayAdapter<String>adapter= new ArrayAdapter<String>(GenerateRequest.this,android.R.layout.simple_list_item_1,list);
                        listView.setAdapter(adapter);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(AvailBanks.this,"It is called",Toast.LENGTH_LONG).show();

                    }

                });





    }
}
