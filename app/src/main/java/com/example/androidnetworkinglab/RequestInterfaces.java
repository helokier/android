package com.example.androidnetworkinglab;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterfaces {
    @GET("android/jsonandroid")
    Call<JSONResponse> getJSON();
}
