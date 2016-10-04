package com.newrdev.photolibrary.ui.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.newrdev.photolibrary.BuildConfig;
import com.newrdev.photolibrary.R;
import com.newrdev.photolibrary.data.model.Album;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class HomeActivity extends AppCompatActivity implements HomeView {

    private HomePresenter presenter;

    @BindView(R.id.cloudRecyclerView) RecyclerView cloudRecyclerView;
    @BindView(R.id.localRecyclerView) RecyclerView localRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        this.presenter = new HomePresenterImpl();

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        cloudRecyclerView.setLayoutManager(horizontalLayoutManagaer);
//        localRecyclerView.setLayoutManager(horizontalLayoutManagaer);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    public void onCloudAlbumsFetched(List<Album> albums) {
        Timber.d("Total albums: %d", albums.size());
        cloudRecyclerView.setAdapter(new CloudRecyclerAdapter(albums));
    }

    @Override
    public void onLocalPhotosFetched() {

    }

    @Override
    protected void onResume() {
        super.onResume();

        this.presenter.bindView(this);

        // TODO - Check if we already cached this, and don't call this
        this.presenter.fetchCloudAlbums();
    }

    @Override
    protected void onPause() {
        super.onPause();

        this.presenter.bindView(this);
    }
}