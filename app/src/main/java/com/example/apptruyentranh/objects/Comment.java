package com.example.apptruyentranh.objects;

import com.google.gson.annotations.SerializedName;

public class Comment{
    @SerializedName("Macomment")
    private Integer Macomment;

    @SerializedName("Noidung")
    private String Noidung;

    @SerializedName("Thoigiancmt")
    private String Thoigiancmt;

    @SerializedName("SoLuongComment")
    private String SoLuongComment;

    @SerializedName("Tenuser")
    private String Tenuser;

    @SerializedName("Mauser")
    private String Mauser;

    @SerializedName("Macommentcha")
    private String Macommentcha;

    @SerializedName("Matruyen")
    private String Matruyen;

    public String getMatruyen() {
        return Matruyen;
    }

    public void setMatruyen(String matruyen) {
        Matruyen = matruyen;
    }

    @SerializedName("Avatar")
    private String Avatar;

    public Integer getMacomment() {
        return Macomment;
    }

    public String getMauser() {
        return Mauser;
    }

    public Comment(String noidung, String thoigiancmt, String matruyen,String mauser, String macommentcha) {
        Noidung = noidung;
        Thoigiancmt = thoigiancmt;

        Mauser = mauser;
        Macommentcha = macommentcha;
        Matruyen = matruyen;
    }

    public void setMauser(String mauser) {
        Mauser = mauser;
    }

    public String getMacommentcha() {
        return Macommentcha;
    }

    public void setMacommentcha(String macommentcha) {
        Macommentcha = macommentcha;
    }

    public void setMacomment(Integer macomment) {
        Macomment = macomment;
    }

    public String getNoidung() {
        return Noidung;
    }

    public void setNoidung(String noidung) {
        Noidung = noidung;
    }

    public String getThoigiancmt() {
        return Thoigiancmt;
    }

    public void setThoigiancmt(String thoigiancmt) {
        Thoigiancmt = thoigiancmt;
    }

    public String getSoLuongComment() {
        return SoLuongComment;
    }

    public void setSoLuongComment(String soLuongComment) {
        SoLuongComment = soLuongComment;
    }

    public String getTenuser() {
        return Tenuser;
    }

    public void setTenuser(String tenuser) {
        Tenuser = tenuser;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }
}
