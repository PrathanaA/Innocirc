package com.proficiency.innocirc;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.MyViewHolder> {
    public  List<Brand_bean> brandList = new ArrayList<>();
    Activity activity;

    public BrandAdapter(Activity activity,List<Brand_bean> brandList) {
        this.activity = activity;
        this.brandList = brandList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView brand_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.brand_icon);
            brand_name=itemView.findViewById(R.id.brand_name);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.brand_item, parent, false);
        return new MyViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Brand_bean brands = brandList.get(position);

        holder.brand_name.setText(brands.getName());

        Glide.with(activity).load(brands.getImage())

                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.icon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.brand_details);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(true);

                ImageView image = dialog.findViewById(R.id.brand_icon);
                TextView brand_text = dialog.findViewById(R.id.brand_name);
                brand_text.setText(brands.getName());

                //Glide image loading
                Glide.with(activity).load(brands.getImage())

                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(image);
                dialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }


}
