package com.example.apptruyentranh.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptruyentranh.Adapter.theloaiAdapter;
import com.example.apptruyentranh.R;
import com.example.apptruyentranh.network.Api;
import com.example.apptruyentranh.objects.theloai;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KhamphaActivity extends Fragment {
        private RecyclerView rcv_TheLoai;
        private List<theloai> TheLoaiList;
        private theloaiAdapter TheLoaiAdapter;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.activity_khampha, container, false);
                rcv_TheLoai = view.findViewById(R.id.rcv_theloai);
                return view;
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
                super.onViewCreated(view, savedInstanceState);
                TheLoaiList = new ArrayList<>();
                TheLoaiAdapter = new theloaiAdapter(getContext(),TheLoaiList);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                rcv_TheLoai.setLayoutManager(linearLayoutManager);
                RecyclerView.ItemDecoration decoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
                rcv_TheLoai.addItemDecoration(decoration);

                rcv_TheLoai.setAdapter(TheLoaiAdapter);

                // Gọi API và nhận dữ liệu
                getDataFromApi();
        }

        private void getDataFromApi() {
                // Tạo Retrofit Instance
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                // Tạo Service
                Api api = retrofit.create(Api.class);

                // Gọi API
                Call<List<theloai>> call = api.getAlltheloaitruyen();
                call.enqueue(new Callback<List<theloai>>() {
                        @Override
                        public void onResponse(Call<List<theloai>> call, Response<List<theloai>> response) {
                                if (response.isSuccessful()) {
                                        // Nhận danh sách thể loại từ kết quả API
                                        List<theloai> theloais = response.body();

                                        // Cập nhật danh sách thể loại của Adapter
                                        TheLoaiList.clear();
                                        TheLoaiList.addAll(theloais);
                                        TheLoaiAdapter.notifyDataSetChanged();
                                } else {
                                        Toast.makeText(getContext(), "Đã xảy ra lỗi khi lấy dữ liệu", Toast.LENGTH_SHORT).show();
                                }
                        }

                        @Override
                        public void onFailure(Call<List<theloai>> call, Throwable t) {
                                Log.e("API Call", "Failed", t);
                                Toast.makeText(getContext(), "Lỗi kết nối đến máy chủ", Toast.LENGTH_SHORT).show();
                        }
                });
        }
}
