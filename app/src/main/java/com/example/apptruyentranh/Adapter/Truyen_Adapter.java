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
import com.example.apptruyentranh.objects.Truyen;
import com.example.apptruyentranh.views.Chitiettruyen_Activity;

import java.util.ArrayList;

public class Truyen_Adapter extends RecyclerView.Adapter<Truyen_Adapter.ViewHolder> {
    private Context ct;
    private ArrayList<Truyen> arrtt;

    public Truyen_Adapter(Context context, ArrayList<Truyen> objects) {
        this.ct = context;
        this.arrtt = objects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemtruyen, parent, false);
        return new ViewHolder(view);
    }
    public int dpToPixels(int dp, Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Truyen truyen = arrtt.get(position);
        holder.Tentruyen.setText(truyen.getTentruyen());
        holder.Tenchapter.setText(truyen.getTenchapter());

        int widthDp = 105;
        int heightDp = 155;

// Chuyển đổi từ dp sang pixel
        int widthPixels = dpToPixels(widthDp, ct);
        int heightPixels = dpToPixels(heightDp, ct);
        String imageUrl = "http://"+ IP.ip +":8080/webtruyentranh/images/anhbiatruyen/" + truyen.getAnhbia();


// Tạo RequestOptions với kích thước được đặt trước và bo tròn góc
        RequestOptions options = new RequestOptions()
                .override(widthPixels, heightPixels)
                .transform(new RoundedCorners(dpToPixels(10, ct))) ;// Điều chỉnh giá trị bo tròn tại đây

        Glide.with(ct)
                .load(imageUrl)
                .apply(options)
                .into(holder.imgAnhbia);


        String matruyen = truyen.getMatruyen();

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
        TextView Tentruyen;
        TextView Tenchapter;
        ImageView imgAnhbia;

        public ViewHolder(View itemView) {
            super(itemView);
            Tentruyen = itemView.findViewById(R.id.tvxTenTruyen);
            Tenchapter = itemView.findViewById(R.id.txvTenChap);
            imgAnhbia = itemView.findViewById(R.id.imgAnhbiatruyen);
        }
    }
}
