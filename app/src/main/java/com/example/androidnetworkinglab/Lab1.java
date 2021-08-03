package com.example.androidnetworkinglab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Lab1 extends AppCompatActivity implements Listener{
    private Button btnload, btnload2, btnload3,btnload4;
    private TextView TV;
    private ImageView img;
    private Bitmap bitmap = null;
    private ProgressDialog progressDialog;
    private String url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTe1wECpQyLLMnFrQGeT-VOhv1D9Q_3if6joPqICs3syVGzpB7F683xVbwNNk8OpAJHQA&usqp=CAU";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1);
        btnload = (Button) findViewById(R.id.button);
        btnload2 = (Button) findViewById(R.id.button2);
        btnload3 = (Button) findViewById(R.id.button3);
        btnload4 = (Button) findViewById(R.id.button4);
        TV = findViewById(R.id.textView);
        img = (ImageView) findViewById(R.id.imageView2);

        btnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        bitmap = loadimg("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4Flf5zlGtTR2ekzI5rUFD6jeBvfbUdMnPPeLFpZHPbwlZcMRC_asUN_VQHYNflNIKjsI&usqp=CAU");
                        img.post(new Runnable() {

                            @Override
                            public void run() {
                                TV.setText("UpLoad Succes");
                                img.setImageBitmap(bitmap);
                            }
                        });
                    }
                });

                thread.start();
            }
        });
        btnload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(Lab1.this, "Download", "Downloading");
                Runnable arunnable = new Runnable() {
                    @Override
                    public void run() {
                        bitmap = Download(url);
                        Message msg = messageHandler.obtainMessage();
                        Bundle bundle = new Bundle();
                        String Messaged = "Download Succes";
                        bundle.putString("Message", Messaged);
                        msg.setData(bundle);
                        messageHandler.sendMessage(msg);
                    }
                };

                Thread th = new Thread(arunnable);
                th.start();

            }
        });
        btnload3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button3:
                       new Asyntask(Lab1.this,Lab1.this).execute("https://cdn.pixabay.com/photo/2020/06/30/22/34/dog-5357794__340.jpg");
                }
            }
        });
        btnload4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent  (Lab1.this,TimeAsynTask.class);
                startActivity(intent);
            }
        });
    }

    private Handler messageHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String message = bundle.getString("Message");
            TV.setText(message);
            img.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }
    };

    public static Bitmap loadimg(String Link) {
        URL url;
        Bitmap bitmap = null;
        try {
            url = new URL(Link);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Bitmap Download(String Links) {
        try {
            URL url = new URL(Links);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void onImageLoaded(Bitmap bitmap){
        TV.setText("IMAGES DOWNLLOADING");
        img.setImageBitmap(bitmap);
    }
    public void onError(){
        TV.setText("DOWNLOAD IMAGES FAILL");
    }
}
