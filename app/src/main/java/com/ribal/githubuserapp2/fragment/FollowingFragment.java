package com.ribal.githubuserapp2.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ribal.githubuserapp2.Activity.DetailActivity;
import com.ribal.githubuserapp2.R;
import com.ribal.githubuserapp2.adapter.FollowingAdapter;
import com.ribal.githubuserapp2.adapter.UserGitAdapter;
import com.ribal.githubuserapp2.model.Following;
import com.ribal.githubuserapp2.model.User;
import com.ribal.githubuserapp2.retrofit.ApiClient;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowingFragment extends Fragment {

    RecyclerView rvFollowing;
    User dataUser;

    public FollowingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_following, container, false);
    }

    @Override
    public void onViewCreated(@NotNull final View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        DetailActivity detailUserActivity = (DetailActivity) getActivity();
        Bundle bundle = detailUserActivity.getIntent().getBundleExtra(UserGitAdapter.DATA_EXTRA);
        dataUser = Parcels.unwrap(bundle.getParcelable(UserGitAdapter.DATA_USER));

        rvFollowing = view.findViewById(R.id.rv_following);
        rvFollowing.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Call<List<Following>> request = ApiClient.getApiService().getFollowingUser(dataUser.getLogin());
        request.enqueue(new Callback<List<Following>>() {
            @Override
            public void onResponse(@NotNull Call<List<Following>> call, @NotNull Response<List<Following>> response) {
                ArrayList<Following> listFollowing = new ArrayList<>();
                if (response.isSuccessful()){
                    if (response.body() != null){
                        listFollowing.addAll(response.body());
                        Log.d("TAG", "onResponse: " +listFollowing.size());
                        rvFollowing.setAdapter(new FollowingAdapter(getContext(), listFollowing));
                    }
                }
                else {
                    Toast.makeText(getContext(), "Request Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Following>> call, Throwable t) {
                Toast.makeText(getContext(), "Request Gagal"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
