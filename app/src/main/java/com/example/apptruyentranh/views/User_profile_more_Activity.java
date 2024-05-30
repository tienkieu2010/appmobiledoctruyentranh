package com.example.apptruyentranh.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptruyentranh.Adapter.theodoiAdapter;
import com.example.apptruyentranh.R;
import com.example.apptruyentranh.network.Api;
import com.example.apptruyentranh.objects.theodoi;
import com.example.apptruyentranh.objects.user;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class User_profile_more_Activity extends AppCompatActivity {

    private List<theodoi> TheoDoiList;
    private theodoiAdapter TheoDoiAdapter;
    private RecyclerView rcv_TheoDoi;
    private user User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile_more);

        // Nhận đối tượng user từ intent
        SharedPreferences sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("mUser", null);
        User= gson.fromJson(json, user.class);

        if (User!=null) {
            TheoDoiList = new ArrayList<>();
            TheoDoiAdapter = new theodoiAdapter(this, TheoDoiList);

            rcv_TheoDoi = findViewById(R.id.rcv_Theodoimore);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rcv_TheoDoi.setLayoutManager(linearLayoutManager);
            RecyclerView.ItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            rcv_TheoDoi.addItemDecoration(decoration);

            rcv_TheoDoi.setAdapter(TheoDoiAdapter);

            // Lấy dữ liệu từ API sử dụng mã user
            getTheodoiFromApi(User.getMauser());
        }

        else {
            Toast.makeText(this, "User data not available", Toast.LENGTH_SHORT).show();
            finish();

        }
    }

    private void getTheodoiFromApi(String userId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        retrofit2.Call<List<theodoi>> call = api.getTheodoiByUserId(userId);
        call.enqueue(new retrofit2.Callback<List<theodoi>>() {
            @Override
            public void onResponse(retrofit2.Call<List<theodoi>> call, Response<List<theodoi>> response) {
                if (response.isSuccessful()) {
                    List<theodoi> theodois = response.body();
                    if (theodois != null) {
                        TheoDoiList.clear();
                        TheoDoiList.addAll(theodois);
                        TheoDoiAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(User_profile_more_Activity.this, "Danh sách theo dõi trống", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(User_profile_more_Activity.this, "Đã xảy ra lỗi khi lấy dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<theodoi>> call, Throwable t) {
                Log.e("API Call", "Failed", t);
                Toast.makeText(User_profile_more_Activity.this, "Lỗi kết nối đến máy chủ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}