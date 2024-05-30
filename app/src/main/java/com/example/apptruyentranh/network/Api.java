package com.example.apptruyentranh.network;
import com.example.apptruyentranh.IP;
import com.example.apptruyentranh.objects.Addcomment;
import com.example.apptruyentranh.objects.Addtheodoi;
import com.example.apptruyentranh.objects.Chapter;
import com.example.apptruyentranh.objects.Comment;
import com.example.apptruyentranh.objects.RegistrationRequest;
import com.example.apptruyentranh.objects.Theloaitruyen;
import com.example.apptruyentranh.objects.Truyen;
import com.example.apptruyentranh.objects.UpdateUserRequest;
import com.example.apptruyentranh.objects.theloai;
import com.example.apptruyentranh.objects.theodoi;
import com.example.apptruyentranh.objects.user;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    String BASE_URL = "http://"+ IP.ip+":8080/webtruyentranh/api/";
    @GET("truyen.php/truyen")
    Call<List<Truyen>> getAllTruyen();

    @GET("truyen.php/truyenbxh")
    Call<List<Truyen>> getAllTruyenbxh();

    @GET("theloai.php/truyen/{matheloai}")
    Call<List<Truyen>> getAlltruyenbyTheloai(@Path("matheloai") String matheloai);

    @GET("truyen.php/truyenid/{matruyen}")
    Call<Truyen> getTruyenid(@Path("matruyen") String matruyen);

    @GET("truyen.php/truyenid/{matruyen}/theloai")
    Call<List<Theloaitruyen>> getallTheloaibytruyen(@Path("matruyen") String matruyen);

    @GET("truyen.php/truyenid/{matruyen}/chapter")
    Call<List<Chapter>> getallchapterbytruyen(@Path("matruyen") String matruyen);

    

    @GET("truyen.php/truyenid/{matruyen}/commentnoibat")
    Call<List<Comment>> getallcommentnoibat(@Path("matruyen") String matruyen);

    @GET("truyen.php/truyenid/{matruyen}/commentparent")
    Call<List<Comment>> getallcommentparent(@Path("matruyen") String matruyen);

    @GET("truyen.php/truyenid/{matruyen}/commentchild/{macmtparent}")
    Call<List<Comment>> getallcommentchild(@Path("matruyen") String matruyen, @Path("macmtparent") String macmtparent);


    @POST("truyen.php/sendComment")
    Call<ResponseBody> sendComment(@Body Addcomment comment);


    //Thắng


    @GET("data.php/theloaitruyen")
    Call<List<theloai>> getAlltheloaitruyen();

    @GET("data.php/user")
    Call<List<user>> getAlluser();



    // Thêm phương thức mới để lấy danh sách theo dõi của một người dùng
    @GET("data.php/theodoi/{userId}")
    Call<List<theodoi>> getTheodoiByUserId(@Path("userId") String userId);

    @GET("data.php/theodoi/{userId}/{matruyen}")
    Call<List<Truyen>> getTheodoi_userid_truyenid(@Path("userId") String userId, @Path("matruyen") String matruyen);



    @POST("register.php")
    Call<ResponseBody> registerUser(@Body RegistrationRequest request);


    @POST("update.php")
    Call<ResponseBody> updateUser(@Body UpdateUserRequest request);

    @POST("Addtheodoi.php")
    Call<ResponseBody> Addtheodoi(@Body Addtheodoi theodoitruyen);

    @POST("deletetheodoi.php")
    Call<ResponseBody> deletetheodoi(@Body Addtheodoi theodoitruyen);






}


