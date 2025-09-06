package com.example.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.List;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8000/") // برای شبیه‌ساز اندروید
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);

        api.getMenu().enqueue(new Callback<List<MenuItem>>() {
            @Override
            public void onResponse(Call<List<MenuItem>> call, Response<List<MenuItem>> response) {
                if (response.isSuccessful()) {
                    List<MenuItem> menu = response.body();
                    for (MenuItem item : menu) {
                        Log.d("MENU", item.getName() + " - " + item.getPrice());
                    }
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
