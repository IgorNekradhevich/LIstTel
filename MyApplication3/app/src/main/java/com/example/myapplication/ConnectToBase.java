package com.example.myapplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ConnectToBase {

    String url ="http://itstepteru.temp.swtest.ru/";
    URL site = null;
    HttpURLConnection conn;
    String HTMLpage="";
    public ConnectToBase()
    {
        try
        {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    getSiteSecondThread();
                }
            });
            thread.start();

        } catch (Exception e) { }
    }

    public ArrayList<Tel> getTelList()
    {

        ArrayList<Tel> ListTel= new ArrayList<Tel>();
        GsonBuilder builder = new GsonBuilder();
        Gson gson= builder.create();
        int index = HTMLpage.indexOf("{\"id");
        while (index!=-1)
        {
            String json = HTMLpage.substring(index,HTMLpage.indexOf("}"+1) );
            ListTel.add(gson.fromJson(json,Tel.class));
        }
        return ListTel;
    }
    private void  getSiteSecondThread()
    {

        try
        {
            HTMLpage="";
            site = new URL(url);
            conn = (HttpURLConnection) site.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla-5.0");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setReadTimeout(10000);
            conn.connect();
            //***************************************************
            InputStream stream = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            Scanner scanner = new Scanner(reader);
            scanner.useDelimiter("\\A");
            while (scanner.hasNext())
            {
                String temp =scanner.nextLine();
                HTMLpage+=temp;
            }
        }
        catch (Exception e) { HTMLpage = e.getMessage();
        };
    }

    public String getSite()
    {
           return  HTMLpage;
        //return  "Test";
    }

    public void Disconnect ()
    {
      conn.disconnect();
    }
}
