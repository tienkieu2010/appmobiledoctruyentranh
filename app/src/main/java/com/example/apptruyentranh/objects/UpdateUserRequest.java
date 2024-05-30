package com.example.apptruyentranh.objects;

public class UpdateUserRequest {
    private String username;
    private String email;
    private String accountType;

    public UpdateUserRequest(String Tendangnhap, String  Email, String Loaitaikhoan) {
        this.username = Tendangnhap;
        this.email = Email;
        this.accountType = Loaitaikhoan;
    }
}
