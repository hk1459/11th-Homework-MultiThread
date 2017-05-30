package com.example.kimja.a11th_homework_multithread;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Loading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Handler mhandler = new Handler();
        mhandler.postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent a = new Intent(Loading.this , MainActivity.class);
                startActivity(a);
                finish();
            }
        }, 3000);
    }
}
