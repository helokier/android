package com.example.androidnetworkinglab.lab3b4;

import com.google.firebase.database.annotations.NotNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
   // http://192.168.1.6/NetWorking/json_data.json
    private static  final String ROOT_URL = "http://192.168.1.6/NetWorking/json_data.json";
    private static Retrofit getRetrofitInstance(){
        return  new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    @NotNull
    public static ApiService getApiService(
    ){
        return getRetrofitInstance().create(ApiService.class);
    }


}
