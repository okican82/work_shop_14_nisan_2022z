package com.seng405.work_shop_14_nisan_2022z.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.seng405.work_shop_14_nisan_2022z.Entity.Result;
import com.seng405.work_shop_14_nisan_2022z.R;

import java.util.List;

public class WeatherResultAdapter extends RecyclerView.Adapter<WeatherResultAdapter.ViewHolder> {
    private List<Result> weatherResultList;

    public WeatherResultAdapter(List<Result> weatherResultList) {
        this.weatherResultList = weatherResultList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.weather_list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Result result = weatherResultList.get(position);

        holder.date_textview.setText(result.getDate());
        holder.status_textview.setText(result.getStatus());
        holder.degree_value_textview.setText(result.getDegree());
        holder.degree_textview.setText("Derece");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return weatherResultList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView date_textview;
        private TextView status_textview;
        private TextView degree_value_textview;
        private TextView degree_textview;

        public ViewHolder(View v) {
            super(v);

            date_textview = v.findViewById(R.id.date_textview);
            status_textview = v.findViewById(R.id.status_textview);
            degree_value_textview = v.findViewById(R.id.degree_value_textview);
            degree_textview = v.findViewById(R.id.degree_textview);

        }
    }

}
