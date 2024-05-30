package com.example.apptruyentranh.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptruyentranh.Adapter.CommentChild_Adapter;
import com.example.apptruyentranh.Adapter.CommentParent_Adapter;
import com.example.apptruyentranh.R;
import com.example.apptruyentranh.network.RetrofitClient;
import com.example.apptruyentranh.objects.Addcomment;
import com.example.apptruyentranh.objects.Comment;
import com.example.apptruyentranh.objects.Truyen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Comment_Activity extends AppCompatActivity {

    ArrayList<Comment> listcmtparent;


    CommentParent_Adapter commentParentAdapter;



    RecyclerView rcvlistcmtparent;

    TextView tentruyen;

    EditText nhapnoidungcmt;

    ImageView sendcmt;
    String matruyen;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        nhapnoidungcmt = findViewById(R.id.editTextComment);
        sendcmt = findViewById(R.id.imgvsendcomment);
        matruyen = getIntent().getStringExtra("matruyen");
        String tentruyen2= getIntent().getStringExtra("tentruyen");
        String Mauser= getIntent().getStringExtra("mauser");

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String currentTime = formatter.format(date);


        // Nhận Intent từ Activity trước
        Intent intent = getIntent();

        /*
        // Kiểm tra xem Intent có dữ liệu không
        if (intent != null) {
            String Macmtcha = intent.getStringExtra("macmt");
            String Matruyen = intent.getStringExtra("matruyen");
            String Tenuser = intent.getStringExtra("tenuser");

            nhapnoidungcmt.setHint("Trả lời "+Tenuser);

            sendcmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Noidungcmt = nhapnoidungcmt.getText().toString().trim();
                    if (!Noidungcmt.isEmpty()) {
                        sendComment(Noidungcmt.trim(),currentTime, Matruyen,"1",Macmtcha);
                    } else {
                        Toast.makeText(Comment_Activity.this, "Vui lòng nhập bình luận", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

         */

            sendcmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Noidungcmt = nhapnoidungcmt.getText().toString().trim();
                    if (!Noidungcmt.isEmpty()) {
                        sendComment(Noidungcmt.trim(),currentTime, matruyen,Mauser,"0");
                    } else {
                        Toast.makeText(Comment_Activity.this, "Vui lòng nhập bình luận", Toast.LENGTH_SHORT).show();
                    }
                }
            });





    listcmtparent = new ArrayList<>();

        rcvlistcmtparent = findViewById(R.id.rcvlistbinhluantruyen);
        commentParentAdapter = new CommentParent_Adapter(this,listcmtparent,matruyen);
        rcvlistcmtparent.setAdapter(commentParentAdapter);
        Getallcmt(matruyen);

        tentruyen = findViewById(R.id.tvtentruyencmt);
        tentruyen.setText(tentruyen2);
    }


    public void Getallcmt(String matr){
        Call<List<Comment>> call = RetrofitClient.getInstance().getMyApi().getallcommentparent(matr);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Comment> cmtparent = response.body();
                    int i=0;
                    for (Comment cmt : cmtparent) {
                        listcmtparent.add(cmt);
                    }
                    commentParentAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occurred: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("error", t.getMessage());
            }
        });
    }


    private void sendComment(String noidung, String thoigiancmt, String matruyen, String mauser, String macmtcha) {

        Addcomment comment = new Addcomment(noidung, thoigiancmt,matruyen,mauser,macmtcha);
        Call<ResponseBody> call = RetrofitClient.getInstance().getMyApi().sendComment(comment);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    listcmtparent.removeAll(listcmtparent);
                    Getallcmt(matruyen);

                    Toast.makeText(Comment_Activity.this, "Gửi bình luận thành công" +response.message(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Comment_Activity.this, "Gửi bình luận thất bại: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Comment_Activity.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }


}
