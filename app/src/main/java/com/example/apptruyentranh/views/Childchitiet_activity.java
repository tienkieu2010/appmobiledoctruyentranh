package com.example.apptruyentranh.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apptruyentranh.Adapter.Binhluannoibat_Adapter;
import com.example.apptruyentranh.Adapter.Theloaibytruyen_Adapter;
import com.example.apptruyentranh.Adapter.Truyen_Adapter;
import com.example.apptruyentranh.IP;
import com.example.apptruyentranh.R;
import com.example.apptruyentranh.network.RetrofitClient;
import com.example.apptruyentranh.objects.Comment;
import com.example.apptruyentranh.objects.Theloaitruyen;
import com.example.apptruyentranh.objects.Truyen;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class Childchitiet_activity extends Fragment {
    Truyen truyen;

    ArrayList<Comment> listcommentnoibat;

    ArrayList<Theloaitruyen> listtheloai;

    TextView noidungtruyen;

    Theloaibytruyen_Adapter tladapter;

    RecyclerView listtlrcv;


    RecyclerView binhluannoibatrcv;
    Binhluannoibat_Adapter binhluannoibat_Adapter;

    Context ct;

    TextView tvtongbl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_childchitiet, container, false);

        // Tìm kiếm TextView trong layout của Fragment
        noidungtruyen = view.findViewById(R.id.tvnoidungtruyen);
        listtlrcv = view.findViewById(R.id.rcvlisttheloai);
        tvtongbl = view.findViewById(R.id.tvtongbl);
        // Đặt LinearLayoutManager với HORIZONTAL
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        listtlrcv.setLayoutManager(layoutManager);




        binhluannoibatrcv = view.findViewById(R.id.rcvlistcommentnoibat);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binhluannoibatrcv.setLayoutManager(layoutManager2);





        Bundle bundle = getArguments();
        if (bundle != null) {
            String mt = bundle.getString("matruyen");
            String tentruyen =bundle.getString("tentruyen");
            String Mauser = bundle.getString("Mauser");

            listtheloai = new ArrayList<>();
            tladapter = new Theloaibytruyen_Adapter(listtheloai);
            listtlrcv.setAdapter(tladapter);


            listcommentnoibat = new ArrayList<>();
            binhluannoibat_Adapter = new Binhluannoibat_Adapter(getContext(),listcommentnoibat);
            binhluannoibatrcv.setAdapter(binhluannoibat_Adapter);

            getTheloaiByIdtruyen(mt);

            getTruyenById(mt);

            getBinhluannoibat(mt);

            tvtongbl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Mauser!=null)
                    {
                        ((Chitiettruyen_Activity) getActivity()).openBinhluanActivity(mt,tentruyen,Mauser);
                    }

                }
            });

            // Sử dụng dữ liệu nhận được tại đây
        }



        // Trả về giao diện của Fragment đã được inflate
        return view;
    }


    public void getTruyenById(String matr) {
        Call<Truyen> call = RetrofitClient.getInstance().getMyApi().getTruyenid(matr);
        call.enqueue(new Callback<Truyen>() {
            @Override
            public void onResponse(Call<Truyen> call, Response<Truyen> response) {
                if (response.isSuccessful() && response.body() != null) {
                    truyen = response.body();
                    // Xử lý truyện được lấy ra

                    if (truyen != null && truyen.getAnhbia() != null) {
                        noidungtruyen.setText(truyen.getNoidung());
                    }

                }
            }

            @Override
            public void onFailure(Call<Truyen> call, Throwable t){
                }

            });

    }

    public void getTheloaiByIdtruyen(String matr) {
        Call<List<Theloaitruyen>> call = RetrofitClient.getInstance().getMyApi().getallTheloaibytruyen(matr);
        call.enqueue(new Callback<List<Theloaitruyen>>() {
            @Override
            public void onResponse(Call<List<Theloaitruyen>> call, Response<List<Theloaitruyen>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Theloaitruyen> tlList = response.body();
                    for (Theloaitruyen tt : tlList) {
                        listtheloai.add(tt);
                    }
                    tladapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(Call<List<Theloaitruyen>> call, Throwable t){

            }

        });

    }

    public void getBinhluannoibat(String matr){
            Call<List<Comment>> call = RetrofitClient.getInstance().getMyApi().getallcommentnoibat(matr);
            call.enqueue(new Callback<List<Comment>>() {
                @Override
                public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                    if (response.isSuccessful() && response.body() != null) {

                        List<Comment> cmtList = response.body();
                        int tongbinhluan = cmtList.size();
                        tvtongbl.setText( "Tổng "+String.valueOf(tongbinhluan) + " bình luận >>");


                        int i=0;
                        for (Comment tt : cmtList) {
                            i++;
                            listcommentnoibat.add(tt);
                            if(i>=3){
                                break;
                            }
                        }
                        binhluannoibat_Adapter.notifyDataSetChanged();



                    }
                }

                @Override
                public void onFailure(Call<List<Comment>> call, Throwable t){
                }

            });


    }
}
