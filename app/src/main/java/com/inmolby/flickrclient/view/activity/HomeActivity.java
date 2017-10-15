package com.inmolby.flickrclient.view.activity;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.inmolby.flickrclient.R;
import com.inmolby.flickrclient.data.model.FlickrImage;
import com.inmolby.flickrclient.presenter.HomePresenter;
import com.inmolby.flickrclient.view.adapter.ImagesAdapter;
import com.inmolby.flickrclient.view.contract.IHomeView;
import com.inmolby.flickrclient.view.customs.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements IHomeView {

    HomePresenter homePresenter;

    EndlessRecyclerViewScrollListener scrollListener;

    ArrayList<FlickrImage> allImages;

    ImagesAdapter imagesAdapter;

    @BindView(R.id.home_recyclerview_flickrImages) RecyclerView imagesRecyclerView;
    @BindView(R.id.home_swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.home_progressbar) ProgressBar homeProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        homePresenter = new HomePresenter(this,getApplicationContext());

        setupRecyclerView();

        setupScrollListener();

        setupSwipeRefreshLayout();

        homePresenter.getPopularImages(1,false);
    }

    private void setupRecyclerView() {
        allImages=new ArrayList<>();
        imagesRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        imagesAdapter = new ImagesAdapter(this,allImages);
        imagesRecyclerView.setAdapter(imagesAdapter);
    }

    private void setupScrollListener()
    {
        scrollListener = new EndlessRecyclerViewScrollListener((GridLayoutManager) imagesRecyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                homePresenter.getPopularImages(page,false);
            }
        };
        imagesRecyclerView.addOnScrollListener(scrollListener);
    }

    private void setupSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homePresenter.getPopularImages(1,true);
            }
        });
    }

    @Override
    public void showImages(ArrayList<FlickrImage> newImages) {
        int startIndex = allImages.size();
        allImages.addAll(newImages);
        notifyInsertedItems(startIndex, newImages.size());
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
                        homePresenter.getPopularImages(1,true);
                    }
                });
        snackbar.show();
    }

    @Override
    public void showError(String errorMessage) {

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

    private void notifyInsertedItems(int currentPosition, final int itemsCount) {
       /* final int firstItemPosition = currentPosition;
        imagesRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                imagesRecyclerView.getAdapter().notifyItemRangeInserted(firstItemPosition, itemsCount);
            }
        });*/
       imagesRecyclerView.getAdapter().notifyDataSetChanged();
    }
}
