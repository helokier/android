package com.example.androidnetworkinglab;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class PTB2AsyncTask extends AsyncTask<Void,Void,Void> {
    Context context;
    TextView tv_KetQua;
    String strKetqua,a,b,c;
    String link = Lab2Bai4.SERVER_NAME;
    ProgressDialog dialog;

    public PTB2AsyncTask(Context context, TextView tv_KetQua, String a, String b, String c) {
        this.context = context;
        this.tv_KetQua = tv_KetQua;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL(link);
            String param = "a=" + URLEncoder.encode(a,"utf-8")+"&b=" + URLEncoder.encode(b,"utf-8")+"&c=" + URLEncoder.encode(c,"utf-8");
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
