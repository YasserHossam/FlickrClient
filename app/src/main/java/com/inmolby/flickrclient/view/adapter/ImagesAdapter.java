package com.inmolby.flickrclient.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inmolby.flickrclient.R;
import com.inmolby.flickrclient.data.model.network.FlickrImage;
import com.inmolby.flickrclient.view.adapter.callback.AdapterToHomeCallbacks;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yasser on 15/10/17.
 */

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private ArrayList<FlickrImage> images;

    private LayoutInflater mInflater;

    private Context context;

    private AdapterToHomeCallbacks homeCallbacks;

    public ImagesAdapter(Context context, ArrayList<FlickrImage> data,AdapterToHomeCallbacks homeCallbacks) {
        this.images = data;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.homeCallbacks=homeCallbacks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cell_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_imageview_flickrImage) ImageView flickrImageView;
        @BindView(R.id.image_textview_title) TextView imageTitleTextView;

        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view=itemView;
        }

        public void bind(final int position) {
            Picasso.with(context).load(images.get(position).getSmallPicture()).into(flickrImageView);
            imageTitleTextView.setText(images.get(position).getTitle());
            flickrImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    homeCallbacks.onThumbnailClick(images.get(position));
                }
            });
        }

    }

}
