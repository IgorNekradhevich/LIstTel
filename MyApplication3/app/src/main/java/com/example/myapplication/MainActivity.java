package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView text;
    ConnectToBase connect;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        text = findViewById(R.id.textView);
        connect = new ConnectToBase();
    }


    public void onClick2(View v )
    {
        try {
            String test = connect.getSite();
            test="";
            text.setText(" ");
            ArrayList<Tel> Tellist = connect.getTelList();
            Tel tel1=Tellist.get(0);
            text.setText(tel1.name);
        }
        catch( Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG ).show();
        }
    }
    public void onClick(View v)
    {
        AddToBase test = new AddToBase();
        Tel tel = new Tel("3","TEST","3242","2000-11-11");
        test.addTel(tel,this);
    }
}
