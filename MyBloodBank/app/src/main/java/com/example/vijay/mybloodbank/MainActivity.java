package com.example.vijay.mybloodbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    MediaPlayer music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final Intent activity=new Intent(getApplicationContext(),MainMenu.class);

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().getRoot();
        DatabaseReference root;

        root=FirebaseDatabase.getInstance().getReference().child("Banks");

        String temp_key;
        String key2;
        temp_key=root.push().getKey();
        DatabaseReference reference = root.child(temp_key);
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("BankName", "Hussaini Blood Bank Karachi");
        map1.put("Address", "Address: 43 Britto Rd, Karachi");
        map1.put("Contact","Phone:(021) 111 111 422");
        map1.put("City","Karachi");
        reference.updateChildren(map1);




                Thread timerThread = new Thread(){
                    public void run(){
                        try{

                            sleep(5000);
                            music= MediaPlayer.create(MainActivity.this, R.raw.sound);
                            music.start();

                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                        finally{

                            startActivity(activity);
                            music.stop();

                        }
                    }
                };
                timerThread.start();


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }
}
