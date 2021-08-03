package com.example.androidnetworkinglab;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHandler {
    private static final String Tag = HttpHandler.class.getSimpleName();
    public HttpHandler(){

    }
    public  String convertStreamToString (String Reurl){
        String reposne = "";
        try {
       URL url  = new URL(Reurl);
            HttpURLConnection  urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null){
                   sb.append(line);
            }
           reposne = sb.toString();
            urlConnection.disconnect();
        }catch(Exception e){
            e.printStackTrace();
        }
        return reposne;
    }
}
