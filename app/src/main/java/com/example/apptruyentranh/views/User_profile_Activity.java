package com.example.apptruyentranh.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.apptruyentranh.Adapter.theodoiAdapter;
import com.example.apptruyentranh.IP;
import com.example.apptruyentranh.R;
import com.example.apptruyentranh.network.Api;
import com.example.apptruyentranh.objects.theodoi;
import com.example.apptruyentranh.objects.user;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class User_profile_Activity extends Fragment {
    private RecyclerView rcv_TheoDoi;
    private List<theodoi> TheoDoiList;
    private theodoiAdapter TheoDoiAdapter;

    private TextView TextViewHoVaTen;
    private TextView TextViewCapNhat;
    private ImageView ImageViewAvatar;

    private user User;

    TextView tvtenuser;
    TextView tvemail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_user_profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Ánh xạ các TextView từ layout

        TextViewCapNhat = view.findViewById(R.id.textViewCapnhatthongtin);
        TextView textViewYcdangnhap = view.findViewById(R.id.textViewYcdangnhap);
        TextView textViewDangxuat = view.findViewById(R.id.textViewDangxuat);
        ImageViewAvatar = view.findViewById(R.id.imageViewAvatar);
        tvtenuser = view.findViewById(R.id.tv_tenuser);
        tvemail = view.findViewById(R.id.tv_emailuser);



        // Kiểm tra xem object_users có tồn tại không
            Bundle args = getArguments();
            if (args != null) {
                String userJson = args.getString("user");
                User = new Gson().fromJson(userJson, user.class);

                textViewDangxuat.setVisibility(View.VISIBLE);
                textViewYcdangnhap.setVisibility(View.GONE);


                if (User != null) {
                    tvtenuser.setVisibility(View.VISIBLE);
                    tvemail.setVisibility(View.VISIBLE);


                    tvtenuser.setText(User.getTenuser());

                    tvemail.setText("Email: " +User.getEmail());

                    // Hiển thị textViewDangxuat và ẩn textViewYcdangnhap

                    // Thêm sự kiện nhấn nút edit
                    TextViewCapNhat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), User_profile_edit_Activity.class);
                            intent.putExtra("objects_user", User);
                            intent.putExtra("avatarUrl", User.getAvatar());
                            startActivity(intent);
                        }
                    });


                    getTheodoiFromApi(User.getMauser());
                    Glide.with(this)
                            .load("http://"+ IP.ip +":8080/webtruyentranh/images/avatar/" + User.getAvatar()) // Thêm tên ảnh vào đường dẫn
                            .placeholder(R.drawable.placeholder_image) // Placeholder image khi đang tải ảnh
                            .error(R.drawable.error_image)
                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))// Ảnh sẽ hiển thị khi xảy ra lỗi trong quá trình tải ảnh
                            .into(ImageViewAvatar); // Ánh xạ ImageViewAvatar từ layout

                } else {
                    tvtenuser.setVisibility(View.GONE);
                    tvemail.setVisibility(View.GONE);
                    // Hiển thị textViewYcdangnhap và ẩn textViewDangxuat
                    textViewYcdangnhap.setVisibility(View.VISIBLE);
                    textViewDangxuat.setVisibility(View.GONE);

                    // Thêm trình nghe sự kiện cho textViewYcDangnhap
                    textViewYcdangnhap.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Chuyển đến LoginActivity
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }

        // Thêm trình nghe sự kiện cho textViewDangxuat
        textViewDangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý đăng xuất (ví dụ: xóa thông tin người dùng khỏi SharedPreferences)
                // Sau đó chuyển đến LoginActivity
                // Ví dụ:
                SharedPreferences preferences = getActivity().getSharedPreferences("user_session", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear(); // Xóa thông tin người dùng
                editor.apply();


                // Show a toast message

                // Redirect to LoginActivity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish(); // Close the current activity


            }
        });

        TextView textViewXemthem = view.findViewById(R.id.textViewXemthem);
        textViewXemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), User_profile_more_Activity.class);
                startActivity(intent);
            }
        });

        rcv_TheoDoi = view.findViewById(R.id.rcv_Theodoi);
        TheoDoiList = new ArrayList<>();
        TheoDoiAdapter = new theodoiAdapter(getActivity(), TheoDoiList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcv_TheoDoi.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        rcv_TheoDoi.addItemDecoration(decoration);
        rcv_TheoDoi.setBackgroundResource(R.drawable.search_edit);
        rcv_TheoDoi.setAdapter(TheoDoiAdapter);
    }

    private void getTheodoiFromApi(String userId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<theodoi>> call = api.getTheodoiByUserId(userId);
        call.enqueue(new Callback<List<theodoi>>() {
            @Override
            public void onResponse(Call<List<theodoi>> call, Response<List<theodoi>> response) {
                if (response.isSuccessful()) {
                    List<theodoi> theodois = response.body();
                    if (theodois != null) {
                        TheoDoiList.clear();
                        int i=0;
                        for(theodoi td : theodois)
                        {
                            i++;
                            TheoDoiList.add(td);

                            if(i==3){
                                break;
                            }
                        }


                        TheoDoiAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getActivity(), "Danh sách theo dõi trống", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Đã xảy ra lỗi khi lấy dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<theodoi>> call, Throwable t) {
                Log.e("API Call", "Failed", t);
                Toast.makeText(getActivity(), "Lỗi kết nối đến máy chủ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static User_profile_Activity newInstance(user User) {
        User_profile_Activity fragment = new User_profile_Activity();
        Bundle args = new Bundle();
        args.putString("user", new Gson().toJson(User));
        fragment.setArguments(args);
        return fragment;
    }
}
