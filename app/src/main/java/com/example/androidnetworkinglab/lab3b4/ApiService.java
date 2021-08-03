package com.example.androidnetworkinglab.lab3b4;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("json_data.json")
    Call<ContactList> getMyJSON();
}
