package com.seng405.work_shop_14_nisan_2022z.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.seng405.work_shop_14_nisan_2022z.Entity.Result;
import com.seng405.work_shop_14_nisan_2022z.R;

public class WaetherDetailActivity extends AppCompatActivity {

    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waether_detail);

        TextView date_textview = findViewById(R.id.date_textview);
        TextView day_textview = findViewById(R.id.day_textview);
        TextView degree_textview = findViewById(R.id.degree_textview);
        Button saveButton = findViewById(R.id.save_button);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null)
        {
            result = bundle.getParcelable("result");
            date_textview.setText(result.getDate());
            day_textview.setText(result.getDay());
            degree_textview.setText(result.getDegree());

        }


        //result tipinde gelen data bilgisini ekranda prop alınarak gösrerilecek.
        //save Button click işleminde DB kaydetme işlemi yapılacak.

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });




    }

    private void saveData()
    {

    }
}