package com.example.apptruyentranh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptruyentranh.R;
import com.example.apptruyentranh.objects.theloai;
import com.example.apptruyentranh.views.search_activity;

import java.util.List;


public class theloaiAdapter extends RecyclerView.Adapter<theloaiAdapter.theloaiViewHolder> {
    private List<theloai> theloaiList;
    Context ct;

    public theloaiAdapter(Context context,List<theloai> theloaiList) {
        this.ct = context;
        this.theloaiList = theloaiList;
    }

    @NonNull
    @Override
    public theloaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theloai2,parent,false);
        return new theloaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull theloaiViewHolder holder, int position) {
        theloai TheLoai = theloaiList.get(position);
        if (TheLoai == null){
            return;
        }
        holder.TextviewTheLoai.setText(TheLoai.getTentheloai());

        holder.TextviewTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ct, search_activity.class);
                intent.putExtra("Matheloai", String.valueOf(TheLoai.getMatheloai()));
                intent.putExtra("Tentheloai2", TheLoai.getTentheloai());
                ct.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (theloaiList != null){
            return theloaiList.size();
        }
        return 0;
    }

    class theloaiViewHolder extends RecyclerView.ViewHolder{
        private TextView TextviewTheLoai;
        public theloaiViewHolder(@NonNull View itemView) {
            super(itemView);
            TextviewTheLoai = itemView.findViewById(R.id.textViewTheloai);
        }
    }
}