package com.ribal.githubuserapp2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.ribal.githubuserapp2.R;
import com.ribal.githubuserapp2.adapter.PageAdapter;
import com.ribal.githubuserapp2.adapter.UserGitAdapter;
import com.ribal.githubuserapp2.model.DetailUser;
import com.ribal.githubuserapp2.model.User;
import com.ribal.githubuserapp2.retrofit.ApiClient;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    DetailUser dataDetailUser;
    User dataUser;
    TextView tvName, tvUsername, tvLocation, tvCompany;
    ImageView ivAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle bundle = getIntent().getBundleExtra(UserGitAdapter.DATA_EXTRA);
        dataUser = Parcels.unwrap(bundle.getParcelable(UserGitAdapter.DATA_USER));
        tvUsername = findViewById(R.id.tv_username);
        tvName = findViewById(R.id.tv_name);
        tvLocation = findViewById(R.id.tv_location);
        tvCompany = findViewById(R.id.tv_company);
        ivAvatar = findViewById(R.id.iv_avatar);

        final ProgressDialog progress = new ProgressDialog(DetailActivity.this);
        progress.setMessage(getString(R.string.loading));
        progress.show();

        Glide.with(DetailActivity.this)
                .load(dataUser.getAvatarUrl())
                .into(ivAvatar);
        tvUsername.setText(dataUser.getLogin());

        Call<DetailUser> request = ApiClient.getApiService().getDetailUser(dataUser.getLogin());
        request.enqueue(new Callback<DetailUser>() {
            @Override
            public void onResponse(Call<DetailUser> call, @NotNull Response<DetailUser> response) {
                dataDetailUser = response.body();
                tvName.setText(dataDetailUser.getName());
                tvLocation.setText(dataDetailUser.getLocation());
                tvCompany.setText(dataDetailUser.getCompany());
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<DetailUser> call, Throwable t) {

            }
        });

        PageAdapter pageAdapter = new PageAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(pageAdapter);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }
}