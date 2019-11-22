package com.example.soap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.ksoap2.transport.HttpTransportSE;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataLoader dl = new DataLoader();
        dl.execute();

    }
}

