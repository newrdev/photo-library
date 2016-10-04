package com.newrdev.photolibrary.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.newrdev.photolibrary.R;
import com.newrdev.photolibrary.data.model.Album;

import java.util.List;

import timber.log.Timber;

/**
 * Created by newrdev on 10/4/16.
 */

public class CloudRecyclerAdapter extends RecyclerView.Adapter<CloudRecyclerAdapter.MyViewHolder> {
    private List<Album> albums;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtView;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            txtView = (TextView) view.findViewById(R.id.titleTextView);
            imageView = (ImageView) view.findViewById(R.id.previewImageView);

        }
    }

    public CloudRecyclerAdapter(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cloud_album_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Album album = albums.get(position);
        String text = "Album " + album.getId().toString();
        holder.txtView.setText(text);

        Timber.d(album.getPhotos().get(0).getThumbnailUrl());
        Glide.with(holder.itemView.getContext())
                .load(album.getPhotos().get(0).getThumbnailUrl())
                .into(holder.imageView);

        holder.txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), holder.txtView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.albums.size();
    }
}
