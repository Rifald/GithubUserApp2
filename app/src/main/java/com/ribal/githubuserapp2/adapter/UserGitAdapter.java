package com.ribal.githubuserapp2.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.ribal.githubuserapp2.Activity.DetailActivity;
import com.ribal.githubuserapp2.R;
import com.ribal.githubuserapp2.model.User;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserGitAdapter extends RecyclerView.Adapter<UserGitAdapter.MyViewHolder> {
    public static final String DATA_USER = "datauser";
    public static final String DATA_EXTRA = "dataextra";
    private Context context;
    private List<User> data;

    public UserGitAdapter(Context context, List<User> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tvUsername.setText(data.get(position).getLogin());
        Glide.with(context)
                .load(data.get(position).getAvatarUrl())
                .into(holder.ivAvatar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveDetailActivity = new Intent(context, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(DATA_USER, Parcels.wrap(data.get(position)));
                moveDetailActivity.putExtra(DATA_EXTRA, bundle);
                context.startActivity(moveDetailActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername;
        ImageView ivAvatar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_name);
            ivAvatar = itemView.findViewById(R.id.iv_picture);
        }
    }
}
