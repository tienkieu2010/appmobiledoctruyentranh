package com.example.apptruyentranh.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptruyentranh.R;
import com.example.apptruyentranh.objects.theodoi;

import java.util.List;

public class theodoimoreAdapter extends RecyclerView.Adapter<theodoimoreAdapter.theodoimoreViewHolder> {
    private List<theodoi> theodoimoreList;

    public theodoimoreAdapter(List<theodoi> theodoiList) {
        this.theodoimoreList = theodoiList;
    }

    @NonNull
    @Override
    public theodoimoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theodoi,parent,false);
        return new theodoimoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull theodoimoreViewHolder holder, int position) {
        theodoi TheoDoi = theodoimoreList.get(position);
        if (TheoDoi == null){
            return;
        }
        holder.TextviewTenTruyen.setText(TheoDoi.getTentruyen());
        holder.TextviewChapTer.setText(TheoDoi.getMaxchapter());
        holder.TextviewTacgia.setText(TheoDoi.getTacgia());
    }

    @Override
    public int getItemCount() {
        if (theodoimoreList != null){
            return theodoimoreList.size();
        }
        return 0;
    }

    class theodoimoreViewHolder extends RecyclerView.ViewHolder{
        private TextView TextviewTenTruyen;
        private TextView TextviewChapTer;
        private TextView TextviewTacgia;

        public theodoimoreViewHolder(@NonNull View itemView) {
            super(itemView);
            TextviewTenTruyen = itemView.findViewById(R.id.textViewTentruyen);
            TextviewTacgia = itemView.findViewById(R.id.textViewTacgia);
            TextviewTacgia = itemView.findViewById(R.id.textViewTacgia);

        }
    }
}