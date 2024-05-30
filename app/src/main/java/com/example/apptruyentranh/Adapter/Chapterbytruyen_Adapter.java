package com.example.apptruyentranh.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptruyentranh.R;
import com.example.apptruyentranh.objects.Chapter;
import com.example.apptruyentranh.objects.Theloaitruyen;
import com.example.apptruyentranh.views.Chapter_Activity;
import com.example.apptruyentranh.views.Chitiettruyen_Activity;

import java.util.ArrayList;

public class Chapterbytruyen_Adapter extends RecyclerView.Adapter<Chapterbytruyen_Adapter.ViewHolder> {
    private Context ct;
    private ArrayList<Chapter> arrtt;

    public OnChapterItemClickListener chapterItemClickListener;

    public Chapterbytruyen_Adapter(ArrayList<Chapter> objects) {
        this.arrtt = objects;

    }

    public void setChapterItemClickListener(OnChapterItemClickListener listener){
        this.chapterItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemchapter, parent, false);
        return new ViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chapter chapter2 = arrtt.get(position);
        holder.Tenchapter.setText(chapter2.getTenchapter());
        holder.Ngaydang.setText(chapter2.getThoigiancapnhat());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chapterItemClickListener != null) {
                    chapterItemClickListener.onChapterItemClick(chapter2.getTenchapter(),chapter2.getNoidung());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrtt.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Tenchapter;

        TextView Ngaydang;

        public ViewHolder(View itemView) {
            super(itemView);
            Tenchapter = itemView.findViewById(R.id.tvtenchapter);
            Ngaydang = itemView.findViewById(R.id.tvthoigiandang);

        }
    }

    public interface OnChapterItemClickListener {
        void onChapterItemClick(String Tenchapter, String Noidung);
    }
}
