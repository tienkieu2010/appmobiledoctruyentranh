package com.example.apptruyentranh.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.example.apptruyentranh.objects.Theloaitruyen;
import com.example.apptruyentranh.objects.Truyen;
import com.example.apptruyentranh.views.Chitiettruyen_Activity;

import java.util.ArrayList;

public class Theloaibytruyen_Adapter extends RecyclerView.Adapter<Theloaibytruyen_Adapter.ViewHolder> {
    private Context ct;
    private ArrayList<Theloaitruyen> arrtt;

    public Theloaibytruyen_Adapter(ArrayList<Theloaitruyen> objects) {
        this.arrtt = objects;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemtheloai, parent, false);
        return new ViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Theloaitruyen theloai= arrtt.get(position);
        holder.Tentheloai.setText(theloai.getTentheloai());




        String matruyen = theloai.getTentheloai();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang trang chi tiết truyện và truyền thông tin về truyện
                Intent intent = new Intent(ct, Chitiettruyen_Activity.class);
                intent.putExtra("matruyen",matruyen);
                ct.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrtt.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Tentheloai;

        public ViewHolder(View itemView) {
            super(itemView);
            Tentheloai = itemView.findViewById(R.id.tvtentl);

        }
    }
}
