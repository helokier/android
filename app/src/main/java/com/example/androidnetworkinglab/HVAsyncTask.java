package com.example.androidnetworkinglab;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class HVAsyncTask extends AsyncTask<Void,Void,Void> {
    Context context;
    TextView tv_KetQua;
    String strKetqua,canh;
    String link = Lab2Bai3.SERVER_NAME;
    ProgressDialog dialog;

    public HVAsyncTask(Context context, TextView tv_KetQua,String canh) {
        this.context = context;
        this.tv_KetQua = tv_KetQua;
        this.canh = canh;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL(link);
            String param = "canh=" + URLEncoder.encode(canh,"utf-8");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded" );
            urlConnection.setFixedLengthStreamingMode(param.getBytes().length);

            //pront
            PrintWriter writer = new PrintWriter(urlConnection.getOutputStream());
            writer.print(param);
            writer.close();

            String line = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
            strKetqua = sb.toString();
            urlConnection.disconnect();
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
        dialog= new ProgressDialog(context);
        dialog.setMessage("Cacutating.....");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (dialog.isShowing()){
            dialog.dismiss();
        }
        tv_KetQua.setText(strKetqua);
    }
}
