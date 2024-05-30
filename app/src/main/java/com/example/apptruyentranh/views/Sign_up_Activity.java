package com.example.apptruyentranh.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.apptruyentranh.R;
import com.example.apptruyentranh.network.RetrofitClient;
import com.example.apptruyentranh.objects.RegistrationRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sign_up_Activity extends AppCompatActivity {

    private EditText usernameEmailEditText, passwordEditText, nameEditText, emailEditText;
    private TextView TextViewDangKi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigup);

        // Ánh xạ các thành phần giao diện
        usernameEmailEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        nameEditText = findViewById(R.id.editTextHoten);
        emailEditText = findViewById(R.id.editTextemail);



        TextViewDangKi = findViewById(R.id.textViewDangki);

        // Xử lý sự kiện khi người dùng nhấn nút đăng ký
        TextViewDangKi.setOnClickListener(view -> registerUser());
    }

    private void registerUser() {
        String username = usernameEmailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();


        // Kiểm tra dữ liệu đầu vào
        if (!TextUtils.isEmpty(username) &&  !TextUtils.isEmpty(password)) {
            RegistrationRequest request = new RegistrationRequest(username, password, name, email);

            // Gửi yêu cầu đăng ký bằng Retrofit

            Call<ResponseBody> call = RetrofitClient.getInstance().getMyApi().registerUser( request);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        // Xử lý phản hồi thành công từ máy chủ
                        Toast.makeText(getApplicationContext(), "User registered successfully", Toast.LENGTH_SHORT).show();
                        finish();
                        // Chuyển đến màn hình đăng nhập hoặc màn hình chính
                        //Intent intent = new Intent(Sign_up_Activity.this, LoginActivity.class);
                        //startActivity(intent);
                        //finish();
                    } else {
                        // Xử lý phản hồi lỗi từ máy chủ
                        Toast.makeText(getApplicationContext(), "Unable to register user", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    // Xử lý khi gặp lỗi kết nối hoặc lỗi không xác định
                    Toast.makeText(getApplicationContext(), "Failed to connect to server", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Hiển thị thông báo lỗi nếu dữ liệu không hợp lệ
            Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }
}
