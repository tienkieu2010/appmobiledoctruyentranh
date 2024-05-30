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
import com.example.apptruyentranh.objects.Comment;
import com.example.apptruyentranh.objects.Theloaitruyen;
import com.example.apptruyentranh.views.Chitiettruyen_Activity;

import java.util.ArrayList;

public class Binhluannoibat_Adapter extends RecyclerView.Adapter<Binhluannoibat_Adapter.ViewHolder> {
    private Context ct;
    private ArrayList<Comment> arrtt;

    public Binhluannoibat_Adapter( Context context,ArrayList<Comment> objects) {
        this.arrtt = objects;
        this.ct=context;
    }

    @NonNull
    @Override
    public Binhluannoibat_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itembinhluannoibat, parent, false);
        return new Binhluannoibat_Adapter.ViewHolder(view);
    }

    public int dpToPixels(int dp, Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Binhluannoibat_Adapter.ViewHolder holder, int position) {
        Comment cmt= arrtt.get(position);
        holder.Tenuser.setText(cmt.getTenuser());
        holder.Noidungcmt.setText(cmt.getNoidung());
        holder.Thoigiancmt.setText(cmt.getThoigiancmt());
        holder.Soluongcmtcon.setText(cmt.getSoLuongComment());



       if(cmt.getAvatar().isEmpty())
       {

       }

       else{
           int widthDp = 50;
           int heightDp = 50;

// Chuyển đổi từ dp sang pixel
           int widthPixels = dpToPixels(widthDp, ct);
           int heightPixels = dpToPixels(heightDp, ct);
           String imageUrl = "http://"+ IP.ip +":8080/webtruyentranh/images/avatar/" + cmt.getAvatar();


// Tạo RequestOptions với kích thước được đặt trước và bo tròn góc
           RequestOptions options = new RequestOptions()
                   .override(widthPixels, heightPixels)
                   .transform(new RoundedCorners(dpToPixels(100, ct))) ;// Điều chỉnh giá trị bo tròn tại đây

           Glide.with(ct)
                   .load(imageUrl)
                   .apply(options)
                   .into(holder.Avatar);


       }





        int macmt = cmt.getMacomment();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang trang chi tiết truyện và truyền thông tin về truyện
                Intent intent = new Intent(ct, Chitiettruyen_Activity.class);
                intent.putExtra("macmt",macmt);
                ct.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrtt.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Avatar;
        TextView Tenuser;
        TextView Noidungcmt;
        TextView Thoigiancmt;
        TextView Soluongcmtcon;

        public ViewHolder(View itemView) {
            super(itemView);
            Avatar = itemView.findViewById(R.id.imganhuser);
            Tenuser = itemView.findViewById(R.id.tvtenuser);
            Noidungcmt = itemView.findViewById(R.id.tvnoidungcomment);
            Thoigiancmt = itemView.findViewById(R.id.tvthoigiancmt);
            Soluongcmtcon = itemView.findViewById(R.id.soluottraloi);
        }
    }
}
