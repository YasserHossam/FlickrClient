package com.inmolby.flickrclient.view.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.inmolby.flickrclient.R;
import com.inmolby.flickrclient.data.model.network.FlickrImage;
import com.inmolby.flickrclient.presenter.HomePresenter;
import com.inmolby.flickrclient.view.adapter.ImagesAdapter;
import com.inmolby.flickrclient.view.adapter.callback.AdapterToHomeCallbacks;
import com.inmolby.flickrclient.view.contract.IHomeView;
import com.inmolby.flickrclient.view.customs.EndlessRecyclerViewScrollListener;
import com.inmolby.flickrclient.view.fragment.FullScreenFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements IHomeView {

    HomePresenter homePresenter;

    EndlessRecyclerViewScrollListener scrollListener;

    ArrayList<FlickrImage> allImages;

    ImagesAdapter imagesAdapter;

    HomeActivity activity;

    @BindView(R.id.home_recyclerview_flickrImages)
    RecyclerView imagesRecyclerView;
    @BindView(R.id.home_swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.home_progressbar)
    ProgressBar homeProgressBar;
    @BindView(R.id.home_framelayout_parentLayout)
    FrameLayout parentFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        homePresenter = new HomePresenter(this, getApplicationContext());

        setupRecyclerView();

        setupScrollListener();

        setupSwipeRefreshLayout();

        homePresenter.initialImageLoading();

        activity = this;
    }

    private void setupRecyclerView() {
        allImages = new ArrayList<>();
        imagesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        imagesAdapter = new ImagesAdapter(this, allImages, homeCallbacks);
        imagesRecyclerView.setAdapter(imagesAdapter);
    }

    private void setupScrollListener() {
        scrollListener = new EndlessRecyclerViewScrollListener((GridLayoutManager) imagesRecyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                homePresenter.loadMoreImages(page);
            }
        };
        imagesRecyclerView.addOnScrollListener(scrollListener);
    }

    private void setupSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homePresenter.swipeRefresh();
            }
        });
    }

    @Override
    public void showImages(ArrayList<FlickrImage> newImages) {
        allImages.addAll(newImages);
        imagesRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void clearImages() {
        allImages.clear();
        scrollListener.resetState();
    }

    @Override
    public void showConnectionError(String errorMessage) {
        Snackbar snackbar = Snackbar
                .make(swipeRefreshLayout, "Check your internet connection!", Snackbar.LENGTH_INDEFINITE)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        homePresenter.initialImageLoading();
                    }
                });
        snackbar.show();
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar snackbar = Snackbar
                .make(swipeRefreshLayout, errorMessage, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void hideRefreshing() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void hideProgressBar() {
        homeProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        homeProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()>0)
        {
            getSupportFragmentManager().popBackStack();
            swipeRefreshLayout.setEnabled(true);
        }
        else
            super.onBackPressed();
    }

    //Recycler View Adapter Callbacks
    AdapterToHomeCallbacks homeCallbacks = new AdapterToHomeCallbacks() {
        @Override
        public void onThumbnailClick(FlickrImage image) {
            swipeRefreshLayout.setEnabled(false);

            FullScreenFragment fragment = FullScreenFragment.newInstance(image.getLargePicture());
            final FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.home_framelayout_parentLayout, fragment).addToBackStack("f");
            fragmentTransaction.commit();

        }
    };
}
