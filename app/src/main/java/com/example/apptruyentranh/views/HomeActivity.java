package com.example.apptruyentranh.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptruyentranh.Adapter.Truyen_Adapter;
import com.example.apptruyentranh.R;
import com.example.apptruyentranh.network.RetrofitClient;
import com.example.apptruyentranh.objects.Truyen;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends Fragment {
    RecyclerView gdvlisttruyen;
    RecyclerView rcvlisttruyenbxh;
    Truyen_Adapter truyenadt;
    Truyen_Adapter truyenadt2;
    ArrayList<Truyen> listtruyen;
    ArrayList<Truyen> listtruyenbxh;
    ImageView imgsearch;
    private ScrollView contentScrollView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);

        init();
        Anhxa(view);
        setup();
        setClick(view);

        return view;
    }

    private void init() {
        listtruyen = new ArrayList<>();
        listtruyenbxh = new ArrayList<>();
    }

    private void Anhxa(View view) {
        gdvlisttruyen = view.findViewById(R.id.grvlisttruyen);
        imgsearch = view.findViewById(R.id.img_Search);
        rcvlisttruyenbxh = view.findViewById(R.id.rcvlisttruyenbxh);
    }

    private void setup() {
        truyenadt = new Truyen_Adapter(getContext(), listtruyen);
        gdvlisttruyen.setAdapter(truyenadt); // Đặt Adapter cho RecyclerView
        getAllTruyen();

        truyenadt2 = new Truyen_Adapter(getContext(), listtruyenbxh);
        rcvlisttruyenbxh.setAdapter(truyenadt2);
        getAllTruyenBxh();
    }

    private void setClick(View view) {
        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo một Intent để mở SearchActivity
                Intent intent = new Intent(getActivity(), search_activity.class);

                // Bắt đầu SearchActivity
                startActivity(intent);
            }
        });
    }

    //lay de danh sach employee su dung thu vien Retrofit 2
    public void getAllTruyen() {
        Call<List<Truyen>> call = RetrofitClient.getInstance().getMyApi().getAllTruyen();
        call.enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(Call<List<Truyen>> call, Response<List<Truyen>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Truyen> truyenList = response.body();
                    int i = 0;
                    for (Truyen truyen : truyenList) {
                        listtruyen.add(truyen);
                        i++;
                        if (i >= 16) {
                            break;
                        }
                    }
                    truyenadt.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Truyen>> call, Throwable t) {
                Toast.makeText(getContext(), "An error has occurred: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("error", t.getMessage());
            }
        });
    }

    public void getAllTruyenBxh() {
        Call<List<Truyen>> call = RetrofitClient.getInstance().getMyApi().getAllTruyenbxh();
        call.enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(Call<List<Truyen>> call, Response<List<Truyen>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Truyen> truyenList = response.body();
                    for (Truyen truyen : truyenList) {
                        listtruyenbxh.add(truyen);
                    }
                    truyenadt2.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Truyen>> call, Throwable t) {
                Toast.makeText(getContext(), "An error has occurred: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("error", t.getMessage());
            }
        });
    }
}
