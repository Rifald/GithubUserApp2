package com.ribal.githubuserapp2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.ribal.githubuserapp2.R;
import com.ribal.githubuserapp2.adapter.UserGitAdapter;
import com.ribal.githubuserapp2.model.RequestGit;
import com.ribal.githubuserapp2.model.User;
import com.ribal.githubuserapp2.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<User> userGit = new ArrayList<>();
    RecyclerView rvUser;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);

        rvUser = findViewById(R.id.rv_users);
        rvUser.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchManager != null) {
            SearchView searchView = (SearchView) findViewById(R.id.git_sv);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.name));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    loading(true);
                    if (s != null) {
                        getData(s);
                    } else {
                        Toast.makeText(MainActivity.this, "Masukkan username", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                @Override
                public boolean onQueryTextChange(String s) {
                    return true;
                }
            });
        }
    }

    private void loading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void getData(final String usernames) {
        Call<RequestGit> request = ApiClient.getApiService().getSearchUser(usernames);
        request.enqueue(new Callback<RequestGit>() {
            @Override
            public void onResponse(Call<RequestGit> call, Response<RequestGit> response) {
                if (response.isSuccessful()) {
                    userGit = response.body().getItems();
                    rvUser.setAdapter(new UserGitAdapter(MainActivity.this, userGit));
                    loading(false);

                } else {
                    Toast.makeText(MainActivity.this, "Request Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RequestGit> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Request Gagal" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}