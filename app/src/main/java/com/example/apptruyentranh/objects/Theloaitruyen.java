package com.example.apptruyentranh.objects;

import com.google.gson.annotations.SerializedName;

public class Theloaitruyen {
    @SerializedName("Matheloai")
    private Integer matheloai;

    @SerializedName("Tentheloai")
    private String tentheloai;

    public Integer getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(Integer matheloai) {
        this.matheloai = matheloai;
    }

    public String getTentheloai() {
        return tentheloai;
    }

    public void setTentheloai(String tentheloai) {
        this.tentheloai = tentheloai;
    }

    public String getHinhanh() {
        return Hinhanhtruyen;
    }

    public void setHinhanh(String hinhanh) {
        Hinhanhtruyen = hinhanh;
    }

    @SerializedName("Hinhanhtruyen")
    private String Hinhanhtruyen;



}