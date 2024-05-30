package com.example.apptruyentranh.Adapter;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.apptruyentranh.IP;
import com.example.apptruyentranh.R;
import com.example.apptruyentranh.objects.theodoi;
import com.example.apptruyentranh.views.Chitiettruyen_Activity;


import java.util.List;

public class theodoiAdapter extends RecyclerView.Adapter<theodoiAdapter.ViewHolder> {

    private List<theodoi> theoDoiList;
    private Context context;
    private static final String IMAGE_BASE_URL = "http://192.168.1.7:8010/truyen/images/anhbiatruyen/"; // Thay thế bằng URL gốc của bạn

    public theodoiAdapter(Context context, List<theodoi> theoDoiList) {
        this.context = context;
        this.theoDoiList = theoDoiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theodoi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        theodoi theoDoi = theoDoiList.get(position);
        holder.textViewTitle.setText(theoDoi.getTentruyen());
        holder.textViewTacgia.setText(theoDoi.getTacgia());


        String imageUrl = "http://"+ IP.ip +":8080/webtruyentranh/images/anhbiatruyen/" + theoDoi.getAnhbia();


        Glide.with(context)
                .load(imageUrl)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Intent to start new Activity
                Intent intent = new Intent(context, Chitiettruyen_Activity.class);
                // You may need to putExtra some data to the next Activity
                intent.putExtra("matruyen", theoDoi.getMatruyen()); // Example
                context.startActivity(intent);
            }
        });






    }

    @Override
    public int getItemCount() {
        return theoDoiList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewTitle;
        public TextView textViewTacgia;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTentruyen);
            textViewTacgia = itemView.findViewById(R.id.textViewTacgia);
        }
    }
}