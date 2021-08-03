package com.example.androidnetworkinglab;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class HCNAsyncTask extends AsyncTask<Void,Void,Void>{
    Context context;
    String width, height, result;
    TextView tv_result;
    ProgressDialog progressDialog;

    String link = Lab2Bai2.SERVER_NAME;

    public HCNAsyncTask(Context context, String width, String height, TextView tv_result) {
        this.context = context;
        this.width = width;
        this.height = height;
        this.tv_result = tv_result;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            //Connect to server
            URL url = new URL(link);
            String param = "width=" + URLEncoder.encode(width,"utf-8") + "&height="
                    +URLEncoder.encode(height,"utf-8");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setFixedLengthStreamingMode(param.getBytes().length);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded" );

            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            printWriter.print(param);
            printWriter.close();

            //Read result from server
            String line ="";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }

            //Set result
            result = stringBuffer.toString();

            //Disconnect
            httpURLConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Vui long cho trong giay lat...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        tv_result.setText(result);
    }
}
