package com.example.apptruyentranh.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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

import java.util.ArrayList;

public class CommentChild_Adapter extends RecyclerView.Adapter<CommentChild_Adapter.ViewHolder> {
    private Context ct;
    private ArrayList<Comment> arrtt;

    public CommentChild_Adapter( Context context,ArrayList<Comment> objects) {
        this.arrtt = objects;
        this.ct=context;
    }

    @NonNull
    @Override
    public CommentChild_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcomment_child, parent, false);
        return new CommentChild_Adapter.ViewHolder(view);
    }

    public int dpToPixels(int dp, Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CommentChild_Adapter.ViewHolder holder, int position) {
        Comment cmt= arrtt.get(position);
        holder.Tenuser.setText(cmt.getTenuser());
        holder.Noidungcmt.setText(cmt.getNoidung());
        holder.Thoigiancmt.setText(cmt.getThoigiancmt());



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




        public ViewHolder(View itemView) {
            super(itemView);
            Avatar = itemView.findViewById(R.id.imgv_anhuserchild);
            Tenuser = itemView.findViewById(R.id.tv_tenuserchild);
            Noidungcmt = itemView.findViewById(R.id.tv_noidungcmtchild);
            Thoigiancmt = itemView.findViewById(R.id.tv_thoigiancmtchild);

        }
    }
}
