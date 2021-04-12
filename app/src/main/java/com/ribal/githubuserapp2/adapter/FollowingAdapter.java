package com.ribal.githubuserapp2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ribal.githubuserapp2.R;
import com.ribal.githubuserapp2.model.Following;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder> {

    private Context context;
    private ArrayList<Following> data;

    public FollowingAdapter(Context context, ArrayList<Following> listFollowing) {
        this.context = context;
        this.data = listFollowing;
    }

    @NonNull
    @Override
    public FollowingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.following_items, parent, false);
        return new FollowingViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowingViewHolder holder, int position) {
        holder.tvUsernameFollowing.setText(data.get(position).getLogin());
        Glide.with(context)
                .load(data.get(position).getAvatarUrl())
                .into(holder.ivAvatarFollowing);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FollowingViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsernameFollowing;
        ImageView ivAvatarFollowing;

        public FollowingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsernameFollowing = itemView.findViewById(R.id.tv_username_following);
            ivAvatarFollowing = itemView.findViewById(R.id.iv_avatar_following);
        }
    }
}
