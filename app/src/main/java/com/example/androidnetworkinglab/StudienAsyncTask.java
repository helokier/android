package com.example.androidnetworkinglab;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StudienAsyncTask extends AsyncTask <String,Void,String>{
   private Context Context;
private String link,name,socre,kq;
private TextView Resuit;

    public StudienAsyncTask(Context context, String link, String name, String socre, TextView resuit) {
        this.Context = context;
        this.link = link;
        this.name = name;
        this.socre = socre;
        Resuit = resuit;
    }

    @Override
    protected String doInBackground(String... strings) {
        link += "?name="+name+"&score="+socre;
        try {
            URL url = new URL(link);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            kq = buffer.toString();
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        Resuit.setText(kq);
        super.onPostExecute(s);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
