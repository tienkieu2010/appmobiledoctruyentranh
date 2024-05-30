package com.example.apptruyentranh.objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class user implements Serializable {
    @SerializedName("Mauser")
    private String Mauser;
    @SerializedName("Tenuser")
    private String Tenuser;
    @SerializedName("Loaitaikhoan")
    private String Loaitaikhoan;
    @SerializedName("Email")
    private String Email;
    @SerializedName("Tendangnhap")
    private String Tendangnhap;
    @SerializedName("Matkhau")
    private String Matkhau;

    @SerializedName("Avatar")
    private String Avatar;

    public String getMauser() {
        return Mauser;
    }

    public void setMauser(String mauser) {
        Mauser = mauser;
    }

    public String getTenuser() {
        return Tenuser;
    }

    public void setTenuser(String tenuser) {
        Tenuser = tenuser;
    }

    public String getLoaitaikhoan() {
        return Loaitaikhoan;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public void setLoaitaikhoan(String loaitaikhoan) {
        Loaitaikhoan = loaitaikhoan;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTendangnhap() {
        return Tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        Tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return Matkhau;
    }

    public void setMatkhau(String matkhau) {
        Matkhau = matkhau;
    }

    @Override
    public String toString() {
        return Tendangnhap + "\n" + "\n" +
                Email + "\n" + "\n" +

                Loaitaikhoan;
    }


}
