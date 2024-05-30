package com.example.apptruyentranh.views;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptruyentranh.R;
import com.example.apptruyentranh.network.RetrofitClient;
import com.example.apptruyentranh.objects.Comment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Callback;
import retrofit2.Response;

public class CommentRepliesActivity extends AppCompatActivity {
    private String tenuser;
    private String macmtcha;
    private String matr;

    String mauser;

    EditText noidungcmt;
    EditText editTextComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);



        /*
        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        tenuser = intent.getStringExtra("tenuser");
        macmtcha = intent.getStringExtra("macmtcha");
        matr = intent.getStringExtra("matruyen");
        mauser = intent.getStringExtra("mauser");

        // Tìm và set OnClickListener cho nút gửi bình luận
        editTextComment = findViewById(R.id.editTextComment);
        ImageView imgvsendcomment = findViewById(R.id.imgvsendcomment);

        imgvsendcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentContent = editTextComment.getText().toString();
                if (!commentContent.isEmpty()) {
                    sendComment(commentContent);
                } else {
                    Toast.makeText(CommentRepliesActivity.this, "Vui lòng nhập bình luận", Toast.LENGTH_SHORT).show();
                }
            }
        });

         */
    }

    /*
    private void sendComment(String content) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String currentTime = formatter.format(date);
        Comment comment = new Comment(String.valueOf(editTextComment.getText()), currentTime,matr,mauser,macmtcha);
        retrofit2.Call<Void> call = RetrofitClient.getInstance().getMyApi().sendComment(comment);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(retrofit2.Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CommentRepliesActivity.this, "Gửi bình luận thành công", Toast.LENGTH_SHORT).show();
                    finish(); // Đóng activity sau khi gửi bình luận
                } else {
                    Toast.makeText(CommentRepliesActivity.this, "Gửi bình luận thất bại: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Void> call, Throwable t) {
                Toast.makeText(CommentRepliesActivity.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

     */
}

