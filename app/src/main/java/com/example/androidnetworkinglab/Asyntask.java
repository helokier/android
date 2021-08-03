package com.example.androidnetworkinglab;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Asyntask  extends AsyncTask<String, Void , Bitmap> {
    Listener mlistener;
    ProgressDialog progressDialog ;
    public Asyntask(Listener listener, Context context){
        this.mlistener = listener;
        this.progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        if (bitmap!=null){
            mlistener.onImageLoaded(bitmap);
        }else{
            mlistener.onError();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Downloading Image.....");
        progressDialog.show();
    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        try {
            return BitmapFactory.decodeStream((InputStream) new URL(strings[0]).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

