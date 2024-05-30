package com.example.apptruyentranh.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.apptruyentranh.IP;
import com.example.apptruyentranh.R;
import com.example.apptruyentranh.network.RetrofitClient;
import com.example.apptruyentranh.objects.Comment;
import com.example.apptruyentranh.views.Chitiettruyen_Activity;
import com.example.apptruyentranh.views.CommentRepliesActivity;
import com.example.apptruyentranh.views.Comment_Activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentParent_Adapter extends RecyclerView.Adapter<CommentParent_Adapter.ViewHolder> {
    private Context ct;
    private ArrayList<Comment> arrtt;

    ArrayList<Comment> listcmtchild;

    String matr;


    CommentChild_Adapter commentChildAdapter;

    public CommentParent_Adapter( Context context,ArrayList<Comment> objects,String matr) {
        this.arrtt = objects;
        this.ct=context;
        this.matr = matr;
    }

    @NonNull
    @Override
    public CommentParent_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcomment, parent, false);
        return new CommentParent_Adapter.ViewHolder(view);
    }

    public int dpToPixels(int dp, Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CommentParent_Adapter.ViewHolder holder, int position) {
        Comment cmt= arrtt.get(position);
        holder.Tenuser.setText(cmt.getTenuser());
        holder.Noidungcmt.setText(cmt.getNoidung());
        // Chuỗi ngày tháng từ đối tượng cmt
        String ngayThangCmt = cmt.getThoigiancmt();

// Định dạng của ngày tháng trong chuỗi
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
// Định dạng của ngày tháng sau khi chuyển đổi
        SimpleDateFormat sdfOutput = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        try {
            // Chuyển đổi chuỗi ngày tháng thành đối tượng Date
            Date ngayThangDate = sdfInput.parse(ngayThangCmt);
            // Định dạng lại thành ngày-tháng-năm
            String ngayThangNam = sdfOutput.format(ngayThangDate);
            // Hiển thị ngày-tháng-năm trong holder
            holder.Thoigiancmt.setText(ngayThangNam);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.Soluotcmtrep.setText(cmt.getSoLuongComment());

        holder.Soluotcmtrep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Truyền dữ liệu Tenuser và Macmt qua Intent
                Intent intent = new Intent(ct, Comment_Activity.class);
                intent.putExtra("tenuser", cmt.getTenuser());
                intent.putExtra("macmt", cmt.getMacomment());
                intent.putExtra("matruyen", matr); // Truyền thêm matr nếu cần thiết
                ct.startActivity(intent);
            }
        });





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

        Getallcmtchild(matr,String.valueOf(cmt.getMacomment()));
        commentChildAdapter = new CommentChild_Adapter(ct,listcmtchild);
        holder.rcvlistcmtchild.setAdapter(commentChildAdapter);




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

        TextView Soluotcmtrep;
        RecyclerView rcvlistcmtchild;




        public ViewHolder(View itemView) {
            super(itemView);
            Avatar = itemView.findViewById(R.id.imgvanhuser);
            Tenuser = itemView.findViewById(R.id.tv_tenuser);
            Noidungcmt = itemView.findViewById(R.id.tv_noidungcmt);
            Thoigiancmt = itemView.findViewById(R.id.tv_thoigiancmt);
            Soluotcmtrep = itemView.findViewById(R.id.tv_soluotcmtrep);
            rcvlistcmtchild = itemView.findViewById(R.id.listcommentchild);






        }

    }
    public void Getallcmtchild(String matr,String macmtparent){
        Call<List<Comment>> call = RetrofitClient.getInstance().getMyApi().getallcommentchild(matr,macmtparent);
        listcmtchild = new ArrayList<>();
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Comment> cmtchild = response.body();
                    for (Comment cmt : cmtchild) {
                        listcmtchild.add(cmt);
                    }
                    commentChildAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ct.getApplicationContext(), "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(ct.getApplicationContext(), "An error has occurred: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("error", t.getMessage());
            }
        });
    }
}
