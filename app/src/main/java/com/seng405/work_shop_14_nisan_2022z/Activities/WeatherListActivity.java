package com.seng405.work_shop_14_nisan_2022z.Activities;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.seng405.work_shop_14_nisan_2022z.Adapter.WeatherResultAdapter;
import com.seng405.work_shop_14_nisan_2022z.Entity.Result;
import com.seng405.work_shop_14_nisan_2022z.Entity.WeatherResult;
import com.seng405.work_shop_14_nisan_2022z.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherListActivity extends AppCompatActivity {

    private String cityName;
    private RecyclerView weather_list_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);

        weather_list_recyclerview = findViewById(R.id.weather_list_recyclerview);

        // Şehir ismi bilgisini Bundle üzerinden alacak.
        Bundle bundle = getIntent().getExtras();

        cityName = "";

        if(bundle != null)
        {
            cityName = bundle.getString("city_name");
        }

        getWeatherListFromNetwork(cityName);

        // network data alınacak.

        // recycler view içerisinde data gösterecek.


    }

    private void getWeatherListFromNetwork(String cityName)
    {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://api.collectapi.com/weather/getWeather?data.lang=tr&data.city=" + cityName)
                .method("GET", null)
                .addHeader("authorization", "apikey 2Stxlk4jSx7jEwkTpUOGi9:2hTZLp49lZk7vzGNpf3ccy")
                .addHeader("content-type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, "onFailure:");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful())
                {
                    final String responseBody = response.body().string();
                    WeatherResult weatherResult = new Gson().fromJson(responseBody,WeatherResult.class);

                    WeatherListActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setAdapterRecyclerView(weatherResult.getResult());
                        }
                    });

                    // recycleer view içerisinde kullanacağım data kaynağı weatherResult result değişkeni.

                    Log.d(TAG, "onResponse: ");
                }
            }
        });
    }

    private void setAdapterRecyclerView(List<Result> resultList)
    {
        WeatherResultAdapter adapter = new WeatherResultAdapter(resultList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        weather_list_recyclerview.setLayoutManager(mLayoutManager);
        weather_list_recyclerview.setAdapter(adapter);
        //weather_list_recyclerview
        // adapter
    }



}