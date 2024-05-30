package com.example.apptruyentranh.objects;

import com.google.gson.annotations.SerializedName;

public class Chapter {
    @SerializedName("Machapter")
    private Integer Machapter;

    @SerializedName("Tenchapter")
    private String Tenchapter;

    @SerializedName("Thoigiancapnhat")
    private String Thoigiancapnhat;

    @SerializedName("Noidung")
    private String Noidung;

    public String getNoidung() {
        return Noidung;
    }

    public void setNoidung(String noidung) {
        Noidung = noidung;
    }

    public Integer getMachapter() {
        return Machapter;
    }

    public void setMachapter(Integer machapter) {
        Machapter = machapter;
    }

    public String getTenchapter() {
        return Tenchapter;
    }

    public void setTenchapter(String tenchapter) {
        Tenchapter = tenchapter;
    }

    public String getThoigiancapnhat() {
        return Thoigiancapnhat;
    }

    public void setThoigiancapnhat(String thoigiancapnhat) {
        Thoigiancapnhat = thoigiancapnhat;
    }
}
