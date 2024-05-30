package com.example.apptruyentranh.views;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.apptruyentranh.IP;
import com.example.apptruyentranh.R;
import com.example.apptruyentranh.network.RetrofitClient;
import com.example.apptruyentranh.objects.UpdateUserRequest;
import com.example.apptruyentranh.objects.user;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_profile_edit_Activity extends AppCompatActivity {
    private EditText EditTextHoVaTen;
    private EditText EditTextEmail;
    private EditText EditTextLoaiTaiKhoan;
    private TextView TextViewCapNhat;
    private ImageView imageViewAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_edit);

        // Ánh xạ các EditText và TextView từ layout
        EditTextHoVaTen = findViewById(R.id.editTextTextHovaten);
        EditTextEmail = findViewById(R.id.editTextTextEmail);
        EditTextLoaiTaiKhoan = findViewById(R.id.editTextTextLoaitaikhoan);
        TextViewCapNhat = findViewById(R.id.textViewCapnhat);
        imageViewAvatar = findViewById(R.id.imageViewAvatar);

        // Nhận thông tin từ Intent
        Bundle bundleReceive = getIntent().getExtras();
        if (bundleReceive != null) {
            user User = (user) bundleReceive.get("objects_user");
            String avatarUrl = bundleReceive.getString("avatarUrl"); // Nhận đường dẫn ảnh từ Intent
            if (User != null) {
                EditTextHoVaTen.setText(User.getTendangnhap());
                EditTextEmail.setText(User.getEmail());
                EditTextLoaiTaiKhoan.setText(User.getLoaitaikhoan());


                Glide.with(this)
                        .load("http://"+ IP.ip +":8080/webtruyentranh/images/avatar/" + avatarUrl) // Nối đường dẫn của ảnh avatar với đường dẫn bạn cung cấp
                        .placeholder(R.drawable.placeholder_image) // Placeholder image khi đang tải ảnh
                        .error(R.drawable.error_image)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))// Ảnh sẽ hiển thị khi xảy ra lỗi trong quá trình tải ảnh
                        .into(imageViewAvatar); // Ánh xạ ImageViewAvatar từ layout
            }
        }

        // Bắt sự kiện khi người dùng nhấn nút "Cập nhật"
        TextViewCapNhat.setOnClickListener(view -> updateUser());
    }


    private void updateUser() {
        String username = EditTextHoVaTen.getText().toString().trim();
        String email = EditTextEmail.getText().toString().trim();
        String accountType = EditTextLoaiTaiKhoan.getText().toString().trim();

        // Kiểm tra dữ liệu đầu vào
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(accountType)){
            UpdateUserRequest request = new UpdateUserRequest(username, email,accountType);

            // Gửi yêu cầu cập nhật thông tin người dùng bằng Retrofit
            String updateUrl = RetrofitClient.BASE_URL + "update.php"; // Sử dụng BASE_URL và đường dẫn cụ thể
            Call<ResponseBody> call = RetrofitClient.getInstance().getMyApi().updateUser( request);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (!response.isSuccessful()) {
                        // Xử lý phản hồi lỗi từ máy chủ
                        try {
                            String errorBody = response.errorBody().string();
                            Toast.makeText(getApplicationContext(), "Error: " + errorBody, Toast.LENGTH_SHORT).show();
                            Log.e("ServerError", "Error response from server: " + errorBody);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // Xử lý phản hồi thành công từ máy chủ
                        Toast.makeText(getApplicationContext(), "User information updated successfully", Toast.LENGTH_SHORT).show();
                        // Cập nhật giao diện hoặc thực hiện hành động khác sau khi cập nhật thành công
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