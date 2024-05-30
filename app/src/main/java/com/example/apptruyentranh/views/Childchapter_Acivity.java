package com.example.apptruyentranh.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptruyentranh.Adapter.Chapterbytruyen_Adapter;
import com.example.apptruyentranh.Adapter.Theloaibytruyen_Adapter;
import com.example.apptruyentranh.R;
import com.example.apptruyentranh.network.RetrofitClient;
import com.example.apptruyentranh.objects.Chapter;
import com.example.apptruyentranh.objects.Theloaitruyen;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Childchapter_Acivity extends Fragment implements Chapterbytruyen_Adapter.OnChapterItemClickListener{
    // Các phương thức và biến khác



    ArrayList<Chapter> listchapter;
    RecyclerView  listchapterrcv;
    Chapterbytruyen_Adapter chapterbytruyen_adapter;

    TextView Capnhatchapter;

    int sochap=0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_childchapter, container, false);

        // Tìm kiếm TextView trong layout của Fragment

        listchapterrcv = view.findViewById(R.id.listchapterrcv);

        Capnhatchapter = view.findViewById(R.id.tvcapnhatchapter);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String mt = bundle.getString("matruyen");

            listchapter = new ArrayList<>();


            chapterbytruyen_adapter = new Chapterbytruyen_Adapter(listchapter);
            chapterbytruyen_adapter.setChapterItemClickListener(this);
            listchapterrcv.setAdapter(chapterbytruyen_adapter);
            getChapterByIdtruyen(mt);





            // Sử dụng dữ liệu nhận được tại đây
        }


        // Trả về giao diện của Fragment đã được inflate
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void getChapterByIdtruyen(String matr) {
        Call<List<Chapter>> call = RetrofitClient.getInstance().getMyApi().getallchapterbytruyen(matr);
        call.enqueue(new Callback<List<Chapter>>() {
            @Override
            public void onResponse(Call<List<Chapter>> call, Response<List<Chapter>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Chapter> tlList = response.body();
                    for (Chapter tt : tlList) {
                        listchapter.add(tt);
                        sochap=sochap+1;
                    }
                    Capnhatchapter.setText("Cập nhật đến chap "+ sochap);
                    chapterbytruyen_adapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(Call<List<Chapter>> call, Throwable t){

            }

        });

    }


    @Override
    public void onChapterItemClick(String Tenchapter, String Noidung) {
        // Lấy context từ Activity chứa Fragment
        Context context = getActivity();
        if (context instanceof Chapterbytruyen_Adapter.OnChapterItemClickListener) {
            ((Chapterbytruyen_Adapter.OnChapterItemClickListener) context).onChapterItemClick(Tenchapter,Noidung);
        }
    }
}
