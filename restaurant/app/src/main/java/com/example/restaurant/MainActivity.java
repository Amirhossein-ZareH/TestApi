package com.example.restaurant;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewMenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.5:8000/") // بدون api/ در Base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        ApiService api = retrofit.create(ApiService.class);

        api.getMenu().enqueue(new Callback<List<MenuItem>>() {
            @Override
            public void onResponse(Call<List<MenuItem>> call, Response<List<MenuItem>> response) {
                if (response.isSuccessful()) {
                    List<MenuItem> menu = response.body();

                    // 🔹 اضافه کردن لاگ برای بررسی داده‌ها
                    if (menu != null) {
                        Log.d("API", "Received items: " + menu.size());
                        for (MenuItem item : menu) {
                            Log.d("API", "Item: " + item.getName() + " - " + item.getPrice());
                        }
                    } else {
                        Log.d("API", "Received null list");
                    }

                    adapter = new MenuAdapter(menu);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("API_ERROR", "Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<MenuItem>> call, Throwable t) {
                Log.e("API_ERROR", t.getMessage());
            }
        });
    }
}
