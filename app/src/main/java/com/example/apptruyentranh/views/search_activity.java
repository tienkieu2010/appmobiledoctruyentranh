package com.example.apptruyentranh.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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

public class search_activity extends AppCompatActivity {
    ArrayList<Truyen> listtruyen;
    Truyen_Adapter truyenAdapter;

    RecyclerView listtruyensearch;

    ArrayList<Truyen> filteredUsers;

    String TenTheLoai;
    TextView theloai5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        theloai5 = findViewById(R.id.tvtheloaitruyen);

        filteredUsers  = new ArrayList<>();


        listtruyensearch = findViewById(R.id.rcvlisttruyensearch);
        listtruyen = new ArrayList<>();


        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("Matheloai")) {
                theloai5.setVisibility(View.VISIBLE);

                String maTheLoai = intent.getStringExtra("Matheloai");
                TenTheLoai = intent.getStringExtra("Tentheloai2");

                getAlltruyenbytheloai(maTheLoai);
            } else {
                theloai5.setVisibility(View.GONE);
                getAllTruyen();
            }
        }











        SearchView searchView = findViewById(R.id.searchview);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Xử lý khi người dùng nhấn Enter hoặc nút tìm kiếm
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Tạo danh sách để lưu trữ người dùng phù hợp
                // Duyệt qua danh sách người dùng hiện tại

                truyenAdapter = new Truyen_Adapter(search_activity.this,filteredUsers);
                listtruyensearch.setAdapter(truyenAdapter);

                filteredUsers.removeAll(listtruyen);


                if (!newText.isEmpty()) {
                    // Duyệt qua danh sách truyện hiện tại
                    for (Truyen t : listtruyen) {
                        // Kiểm tra xem newText có tồn tại trong tên của truyện
                        if (t.getTentruyen().toLowerCase().contains(newText.toLowerCase())) {
                            // Nếu có, thêm truyện vào danh sách lọc
                            filteredUsers.add(t);
                        }
                    }
                }



                truyenAdapter.notifyDataSetChanged();
                // Cập nhật dữ liệu của RecyclerView hoặc ListView với danh sách đã lọc







                return true;
            }
        });

    }

    public void getAllTruyen() {
        Call<List<Truyen>> call = RetrofitClient.getInstance().getMyApi().getAllTruyen();
        call.enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(Call<List<Truyen>> call, Response<List<Truyen>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Truyen> truyenList = response.body();
                    for (Truyen truyen : truyenList) {
                        listtruyen.add(truyen);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Truyen>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occurred: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("error", t.getMessage());
            }
        });
    }

    public void getAlltruyenbytheloai(String Matl){
        Call<List<Truyen>> call = RetrofitClient.getInstance().getMyApi().getAlltruyenbyTheloai(Matl);
        call.enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(Call<List<Truyen>> call, Response<List<Truyen>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Truyen> truyenList = response.body();
                    for (Truyen truyen : truyenList) {
                        listtruyen.add(truyen);
                    }

                    truyenAdapter = new Truyen_Adapter(search_activity.this,listtruyen);
                    listtruyensearch.setAdapter(truyenAdapter);
                    theloai5.setText("Truyện thuộc thể loại: "+TenTheLoai);
                } else {
                    Toast.makeText(getApplicationContext(), "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Truyen>> call, Throwable t) {
                Log.e("error", t.getMessage());
                Toast.makeText(getApplicationContext(), "An error has occurred: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}
