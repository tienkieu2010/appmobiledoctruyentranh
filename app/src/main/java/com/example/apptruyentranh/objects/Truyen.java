package com.example.apptruyentranh.objects;

import android.provider.ContactsContract;

import com.google.gson.annotations.SerializedName;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Truyen {
    @SerializedName("Matruyen")
    private String Matruyen;
    @SerializedName("Tentruyen")
    private String Tentruyen;


    @SerializedName("Tacgia")
    private String Tacgia;

    @SerializedName("Thoigiancapnhat")
    private String Thoigiancapnhat;

    @SerializedName("Tinhtrang")
    private String Tinhtrang;

    @SerializedName("Luotxem")
    private String Luotxem;

    @SerializedName("Luottheodoi")
    private String Luottheodoi;

    @SerializedName("Anhbia")
    private String Anhbia;

    @SerializedName("Noidungtruyen")
    private String Noidung;

    @SerializedName("Trangthai")
    private String Trangthai;

    @SerializedName("Tenchapter")
    private String Tenchapter;

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

    public String getTacgia() {
        return Tacgia;
    }

    public void setTacgia(String tacgia) {
        Tacgia = tacgia;
    }

    public String getThoigiancapnhat() {
        return Thoigiancapnhat;
    }

    public void setThoigiancapnhat(String thoigiancapnhat) {
        Thoigiancapnhat = thoigiancapnhat;
    }

    public String getTinhtrang() {
        return Tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        Tinhtrang = tinhtrang;
    }

    public String getLuotxem() {
        return Luotxem;
    }

    public void setLuotxem(String luotxem) {
        Luotxem = luotxem;
    }

    public String getLuottheodoi() {
        return Luottheodoi;
    }

    public void setLuottheodoi(String luottheodoi) {
        Luottheodoi = luottheodoi;
    }

    public String getAnhbia() {
        return Anhbia;
    }

    public void setAnhbia(String anhbia) {
        Anhbia = anhbia;
    }

    public String getNoidung() {
        return Noidung;
    }

    public void setNoidung(String noidung) {
        Noidung = noidung;
    }

    public String getTrangthai() {
        return Trangthai;
    }

    public void setTrangthai(String trangthai) {
        Trangthai = trangthai;
    }

    public String getTenchapter() {
        return Tenchapter;
    }

    public void setTenchapter(String tenchapter) {
        Tenchapter = tenchapter;
    }
}