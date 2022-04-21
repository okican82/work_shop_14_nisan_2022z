package com.seng405.work_shop_14_nisan_2022z.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.seng405.work_shop_14_nisan_2022z.R;

public class MainActivity extends Activity {

    private EditText city_input_edit_text;
    private Button show_saved_data_btn;
    private Button show_data_btn;

    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city_input_edit_text = findViewById(R.id.city_input_edit_text);
        show_saved_data_btn = findViewById(R.id.show_saved_data_btn);
        show_data_btn = findViewById(R.id.show_data_btn);

        show_saved_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateRecordedWaetherActivity();
            }
        });

        show_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCityNameToLocalDataSource(city_input_edit_text.getText().toString());
                navigateWeatherListActivity();
            }
        });


        cityName = getCityNameFromLocalDataSource();

        if(cityName.length() > 0)
        {
            navigateWeatherListActivity();
        }
    }

    private void navigateRecordedWaetherActivity()
    {
        Intent recordedWeatherIntent = new Intent(MainActivity.this,RecordedWeatherActivity.class);
        startActivity(recordedWeatherIntent);
    }

    private void saveCityNameToLocalDataSource(String cityName)
    {
        // Validation

        this.cityName = cityName;

        // CityName bilgisini lokal data source save
        String CONST_DATA = "CITY_NAME";
        SharedPreferences preferences = this.getSharedPreferences(CONST_DATA, getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CONST_DATA,String.valueOf(cityName));
        editor.apply();

    }

    private String getCityNameFromLocalDataSource()
    {
        String result;
        String CONST_DATA = "CITY_NAME";
        SharedPreferences preferences = this.getSharedPreferences(CONST_DATA, getApplicationContext().MODE_PRIVATE);
        result = preferences.getString(CONST_DATA, "");

        return result;
    }

    private void navigateWeatherListActivity()
    {
        Intent weatherListIntent = new Intent(MainActivity.this,WeatherListActivity.class);
        weatherListIntent.putExtra("city_name",cityName);
        startActivity(weatherListIntent);
    }

}