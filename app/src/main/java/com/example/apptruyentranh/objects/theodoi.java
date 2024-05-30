package com.example.apptruyentranh.objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class theodoi  {
    @SerializedName("Tentruyen")
    private String Tentruyen;
    @SerializedName("Anhbia")
    private String Anhbia;

    public String getTacgia() {
        return Tacgia;
    }

    public void setTacgia(String tacgia) {
        Tacgia = tacgia;
    }

    @SerializedName("Tacgia")
    private String Tacgia;

    @SerializedName("Maxchapter")
    private String Maxchapter;

    @SerializedName("Matruyen")
    private String Matruyen;

    public String getMatruyen() {
        return Matruyen;
    }

    public void setMatruyen(String matruyen) {
        Matruyen = matruyen;
    }

    public String getTentruyen() {
        return Tentruyen;
    }

    public void setTentruyen(String tentruyen) {
        Tentruyen = tentruyen;
    }

    public String getAnhbia() {
        return Anhbia;
    }

    public void setAnhbia(String anhbia) {
        Anhbia = anhbia;
    }

    public String getMaxchapter() {
        return Maxchapter;
    }

    public void setMaxchapter(String maxchapter) {
        Maxchapter = maxchapter;
    }

}