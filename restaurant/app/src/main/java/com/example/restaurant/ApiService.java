package com.example.restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/menu/")
    Call<List<MenuItem>> getMenu();


}
