package com.inmolby.flickrclient.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.inmolby.flickrclient.R;
import com.inmolby.flickrclient.view.customs.TouchImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FullScreenFragment extends Fragment {

    static final String IMAGE_URL="Image_Url@FullScreenFragment";

    @BindView(R.id.fullscreen_imageview_flickrImage) TouchImageView touchImageView;
    @BindView(R.id.fullscreen_progressbar)ProgressBar fullScreenProgressBar;

    public FullScreenFragment() {
        // Required empty public constructor
    }

    public static FullScreenFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString(IMAGE_URL,url);
        FullScreenFragment fragment = new FullScreenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_screen, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Picasso.with(getContext()).load(getArguments().getString(IMAGE_URL)).placeholder(R.drawable.placeholder).into(touchImageView);
    }
}
