package com.example.apptruyentranh.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.apptruyentranh.IP;
import com.example.apptruyentranh.R;

import java.util.ArrayList;
import java.util.List;

public class ImageChapter_Adapter extends RecyclerView.Adapter<ImageChapter_Adapter.ViewHolder> {

    private ArrayList<String> imageNames;
    private Context context;

    public ImageChapter_Adapter(Context context, ArrayList<String> imageNames) {
        this.context = context;
        this.imageNames = imageNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.imagechapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String imageName = imageNames.get(position);

        String imageUrl = "http://"+ IP.ip +":8080/webtruyentranh/images/anhtruyen/" + imageName;




        Glide.with(context)
                .load(imageUrl)
                .apply(new RequestOptions().transform(new RoundedCorners(16)))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e("Glide", "Error loading image", e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.anh);


    }

    @Override
    public int getItemCount() {
        return imageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView anh;
        TextView tenchapter;

        public ViewHolder(View itemView) {
            super(itemView);
            anh = itemView.findViewById(R.id.img_chapter);

        }
    }
}
