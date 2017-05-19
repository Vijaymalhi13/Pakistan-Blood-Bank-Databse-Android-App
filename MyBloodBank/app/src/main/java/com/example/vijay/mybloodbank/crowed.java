package com.example.vijay.mybloodbank;

import com.firebase.client.Firebase;

/**
 * Created by vijay on 12/3/2016.
 */
public class crowed extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
