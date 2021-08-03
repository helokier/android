package com.example.androidnetworkinglab;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class AsynTaskB4 extends AsyncTask<String,Void,String> {
private ProgressDialog dialog;
    Context context; TextView view; EditText time;
    String kq;
public AsynTaskB4(Context context, TextView view, EditText time){
this.context = context;
this.view = view;
this.time = time;
}

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(context,"Title","Pleas Wait..."+time.getText().toString()+"seconds");
    }

    @Override
    protected String doInBackground(String... strings) {
//    publishProgress("Sleeping.....");
    try{
         int times = Integer.parseInt(strings[0]+100);
         Thread.sleep(times);
         kq = "Sleeping..."+strings[0]+"seconds";
    }catch(Exception e){
        e.printStackTrace();
        kq = e.getMessage();
    }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
    if (dialog.isShowing()){
        dialog.dismiss();
    }
    view.setText(s);
        super.onPostExecute(s);
    }
}
