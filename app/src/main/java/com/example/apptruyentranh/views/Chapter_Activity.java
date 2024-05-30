package com.example.apptruyentranh.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptruyentranh.Adapter.ImageChapter_Adapter;
import com.example.apptruyentranh.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chapter_Activity extends AppCompatActivity {
    RecyclerView rcvanhchapter;
    TextView tenchapter;
    ImageChapter_Adapter imgchapter_adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        rcvanhchapter = findViewById(R.id.rcv_listanhchapter);
        tenchapter = findViewById(R.id.tv_tenchapter);

        rcvanhchapter.setLayoutManager(new LinearLayoutManager(this));

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String tenChapter = intent.getStringExtra("Tenchapter");
        String noidung = intent.getStringExtra("Noidung");

        tenchapter.setText(tenChapter);

        // Tách chuỗi tên ảnh thành danh sách
        ArrayList<String> imageNames = new ArrayList<>(Arrays.asList(noidung.split(",")));
        imgchapter_adapter = new ImageChapter_Adapter(this, imageNames);
        rcvanhchapter.setAdapter(imgchapter_adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Lỗi", "onResume called");
        // Đảm bảo rằng không có hành động nào không hợp lệ tại đây
    }
}
