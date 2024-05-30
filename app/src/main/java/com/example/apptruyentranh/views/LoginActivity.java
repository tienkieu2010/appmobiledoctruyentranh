package com.example.apptruyentranh.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.example.apptruyentranh.R;
import com.example.apptruyentranh.network.Api;
import com.example.apptruyentranh.objects.user;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;






public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewLogin;
    private List<user> mListUser;
    private user mUser;
    private TextView TextViewReGisTer;
    private static final int RC_SIGN_IN = 9001;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Ánh xạ các thành phần UI
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        textViewLogin = findViewById(R.id.textViewDangnhap);
        TextViewReGisTer = findViewById(R.id.textViewRegister);

        mListUser = new ArrayList<>();

    getDataFromApi();





    textViewLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            clickLogin();
        }

        private void clickLogin() {
            String strLogin = editTextEmail.getText().toString().trim();
            String strPassword = editTextPassword.getText().toString().trim();


            if(mListUser == null || mListUser.isEmpty()){
                Toast.makeText(LoginActivity.this, "Dữ liệu người dùng chưa sẵn sàng", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isHasUser = false;
            for(user User : mListUser){
                if(strLogin.equals(User.getTendangnhap()) && strPassword.equals(User.getMatkhau()) ){
                    isHasUser = true;
                    mUser = User;
                    break;
                }
            }

            if(isHasUser){

                // Trong activity đăng nhập hoặc nơi bạn nhận được thông tin người dùng
                SharedPreferences sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Mauser", mUser.getMauser());
                // Chuyển đổi đối tượng mUser thành chuỗi JSON
                Gson gson = new Gson();
                String json = gson.toJson(mUser);

                // Lưu chuỗi JSON vào SharedPreferences
                editor.putString("mUser", json);
// Thêm các thông tin chi tiết người dùng khác nếu cần
                editor.apply();


                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("objects_user",mUser);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(LoginActivity.this,"Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        }


    });

        TextViewReGisTer.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, Sign_up_Activity.class);
            startActivity(intent);
        });



    }





    private void getDataFromApi() {
        // Khởi tạo RetrofitClient để gọi API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Tạo đối tượng API từ RetrofitClient
        Api api = retrofit.create(Api.class);

        // Gọi phương thức API để lấy dữ liệu người dùng
        Call<List<user>> call = api.getAlluser();
        call.enqueue(new Callback<List<user>>() {
            @Override
            public void onResponse(Call<List<user>> call, retrofit2.Response<List<user>> response) {
                if (!response.isSuccessful()) {
                    // Xử lý khi có lỗi từ máy chủ
                    // Ví dụ: Hiển thị thông báo lỗi
                    return;
                }

                // Lấy danh sách người dùng từ phản hồi
                List<user> userList = response.body();

                // Kiểm tra xem danh sách người dùng có dữ liệu không
                if (userList != null && !userList.isEmpty()) {
                    // Lấy người dùng đầu tiên từ danh sách (có thể chỉ có một người dùng)
                    mListUser.addAll(userList);


                }
            }

            @Override
            public void onFailure(Call<List<user>> call, Throwable t) {
                // Xử lý khi gặp lỗi trong quá trình gọi API
                // Ví dụ: Hiển thị thông báo lỗi
            }
        });
    }
}
