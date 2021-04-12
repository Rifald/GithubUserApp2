package com.ribal.githubuserapp2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ribal.githubuserapp2.R;
import com.ribal.githubuserapp2.model.Follower;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class FollowerAdapter extends RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder> {

    private Context context;
    private ArrayList<Follower> data;

    public FollowerAdapter(Context context, ArrayList<Follower> listFollower) {
        this.context = context;
        this.data = listFollower;
    }

    @NonNull
    @Override
    public FollowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.follower_items, parent, false);
        return new FollowerViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowerViewHolder holder, int position) {
        holder.tvUsernameFollower.setText(data.get(position).getLogin());
        Glide.with(context)
                .load(data.get(position).getAvatarUrl())
                .into(holder.ivAvatarFollower);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FollowerViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsernameFollower;
        ImageView ivAvatarFollower;

        public FollowerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsernameFollower = itemView.findViewById(R.id.tv_username_follower);
            ivAvatarFollower = itemView.findViewById(R.id.iv_avatar_follower);

        }
    }
}
