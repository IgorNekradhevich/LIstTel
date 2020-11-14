package com.example.myapplication;

import android.app.DownloadManager;
import android.content.Context;
import android.provider.Settings;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Dictionary;
import java.util.List;

public class AddToBase {

    public void addTel (Tel telephone, Context x)
    {
        try
        {
            URL url = new URL("https://bousy-patch.000webhostapp.com/insert.php");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            OutputStream stream =connection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            GsonBuilder builder = new GsonBuilder();
            Gson gson= builder.create();
            String text =gson.toJson(telephone);
            writer.write(text);
            writer.flush();
            writer.close();

        }
        catch (Exception e)
        {
            Toast.makeText(x, e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
