package com.example.apptruyentranh.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.apptruyentranh.Adapter.Chapterbytruyen_Adapter;
import com.example.apptruyentranh.IP;
import com.example.apptruyentranh.R;
import com.example.apptruyentranh.network.RetrofitClient;
import com.example.apptruyentranh.objects.Addcomment;
import com.example.apptruyentranh.objects.Addtheodoi;
import com.example.apptruyentranh.objects.Truyen;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chitiettruyen_Activity extends AppCompatActivity implements Chapterbytruyen_Adapter.OnChapterItemClickListener {
    // Các phương thức khác của Activity
    TextView scrollingTextView;
    private Handler mHandler = new Handler();
    private static final int SCROLL_SPEED = 1; // Tốc độ cuộn (pixels/frame)
    private boolean isMarqueeRunning = false;


    ImageView Anhtruyen;
    TextView Tacgia;

    TextView Tinhtrang;
    TextView Luotxem;
    TextView Xephang;
    TextView Luotdanhgia;

    TextView Luottheodoi;

    TextView btnTheodoitruyen;
    TextView btnHuyTheodoitruyen;
    Truyen truyen;

    Context ct;
    String Mauser;
    TextView selectedTextView = null;

    String maTruyen;

    public void openBinhluanActivity(String matruyen, String tentruyen, String Mauser) {
        Intent intent = new Intent(this, Comment_Activity.class);
        intent.putExtra("matruyen", matruyen);
        intent.putExtra("tentruyen", tentruyen);
        intent.putExtra("mauser",Mauser);

        startActivity(intent);
    }

    @Override
    public void onChapterItemClick(String tenChapter, String noiDung) {
        Intent intent = new Intent(this, Chapter_Activity.class);
        intent.putExtra("Tenchapter", tenChapter);
        intent.putExtra("Noidung", noiDung);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        maTruyen = getIntent().getStringExtra("matruyen");

        ct = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiettruyen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        scrollingTextView = findViewById(R.id.tvTentruyen);

        // Bắt sự kiện khi nút quay lại được nhấn
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        SharedPreferences sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE);
        Mauser = sharedPreferences.getString("Mauser", null);




        TextView textView = findViewById(R.id.tvchitiet);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedTextView != null) {
                    selectedTextView.setTypeface(null, Typeface.NORMAL);
                }

                // Đổi màu của TextView hiện tại đang được chọn
                ((TextView) v).setTypeface(null,Typeface.BOLD_ITALIC);

                // Cập nhật TextView hiện tại đang được chọn
                selectedTextView = (TextView) v;

                //set màn hình con
                /*
                FrameLayout parentLayout = findViewById(R.id.Layoutcttruyen);
                LayoutInflater inflater = getLayoutInflater();
                View childView = inflater.inflate(R.layout.activity_childchitiet, parentLayout, false);
                parentLayout.removeAllViews();
                parentLayout.addView(childView);

                 */

                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);


                // Tạo Bundle để chứa dữ liệu
                Bundle bundle = new Bundle();

                // Đặt giá trị matruyen vào Bundle

                if (Mauser!= null) {
                    // Người dùng đã đăng nhập, tiếp tục với các chức năng dành cho người dùng
                    bundle.putString("Mauser", Mauser);
                }
                bundle.putString("matruyen", truyen.getMatruyen());
                bundle.putString("tentruyen", truyen.getTentruyen());

                // Tạo một instance mới của Childchitiet_activity
                Childchitiet_activity child = new Childchitiet_activity();

                // Đặt Bundle làm tham số của phương thức setArguments
                child.setArguments(bundle);

                // Thay thế Fragment hiện tại bằng Childchitiet_activity
                getSupportFragmentManager().beginTransaction().replace(R.id.Layoutcttruyen, child).addToBackStack(null).commit();
            }
        });

        TextView textView2 = findViewById(R.id.tvchapter);

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (selectedTextView != null) {
                    selectedTextView.setTypeface(null, Typeface.NORMAL);
                }

                // Đổi màu của TextView hiện tại đang được chọn
                ((TextView) v).setTypeface(null,Typeface.BOLD_ITALIC);

                // Cập nhật TextView hiện tại đang được chọn
                selectedTextView = (TextView) v;


                //set màn hình con
                /*
                FrameLayout parentLayout = findViewById(R.id.Layoutcttruyen);
                LayoutInflater inflater = getLayoutInflater();
                View childView = inflater.inflate(R.layout.activity_childchapter, parentLayout, false);
                parentLayout.removeAllViews();
                parentLayout.addView(childView);

                 */

                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);


                // Tạo Bundle để chứa dữ liệu
                Bundle bundle = new Bundle();

                // Đặt giá trị matruyen vào Bundle
                bundle.putString("matruyen", truyen.getMatruyen());

                // Tạo một instance mới của Childchitiet_activity
                Childchapter_Acivity child2 = new Childchapter_Acivity();

                // Đặt Bundle làm tham số của phương thức setArguments
                child2.setArguments(bundle);

                // Thay thế Fragment hiện tại bằng Childchitiet_activity
                getSupportFragmentManager().beginTransaction().replace(R.id.Layoutcttruyen, child2).addToBackStack(null).commit();
            }
        });



        btnTheodoitruyen = findViewById(R.id.tvTheodoitruyen);
        btnHuyTheodoitruyen = findViewById(R.id.tvHuytheodoitruyen);
        if(Mauser!=null)
        {
            setbtnTHeodoi_Huytheodoi_Mauser_Matruyen(Mauser,maTruyen);
        }
        else{
            btnTheodoitruyen.setVisibility(View.VISIBLE);
            btnHuyTheodoitruyen.setVisibility(View.GONE);

            btnTheodoitruyen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ct, LoginActivity.class);
                    ct.startActivity(intent);
                }
            });
        }



        Tinhtrang = findViewById(R.id.tvTinhtrang);
        Luotxem = findViewById(R.id.tvLuotxem);
        Xephang = findViewById(R.id.tvXephang);
        Luottheodoi = findViewById(R.id.tvLuottheodoi);
        Anhtruyen  = findViewById(R.id.imgAnhbia);


        getTruyenById(maTruyen);



    }



    public int dpToPixels(int dp, Context ct) {
        if (ct == null) {
            return 0; // hoặc giá trị mặc định khác tùy vào yêu cầu của ứng dụng
        }
        float scale = ct.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (!isMarqueeRunning) {
            startMarquee();
            isMarqueeRunning = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopMarquee();
    }

    private void stopMarquee() {
        mHandler.removeCallbacksAndMessages(null);
        isMarqueeRunning = false;
    }

    private void startMarquee() {
        mHandler.postDelayed(new Runnable() {
            public void run() {
                // Tính toán vị trí mới
                int scrollX = scrollingTextView.getScrollX() + SCROLL_SPEED;

                // Kiểm tra xem đã cuộn hết chữ hay chưa
                if (scrollX >= scrollingTextView.getWidth()) {
                    scrollX = 0; // Trở lại vị trí ban đầu nếu đã cuộn hết
                }

                // Scroll đến vị trí mới
                scrollingTextView.scrollTo(scrollX, 0);

                // Tiếp tục chữ trượt
                mHandler.postDelayed(this, 10); // Lặp lại sau mỗi 10ms
            }
        }, 1000); // Bắt đầu sau 1 giây
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // hoặc thực hiện hành động quay lại mong muốn
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void getTruyenById(String matr) {
        Call<Truyen> call = RetrofitClient.getInstance().getMyApi().getTruyenid(matr);
        call.enqueue(new Callback<Truyen>() {
            @Override
            public void onResponse(Call<Truyen> call, Response<Truyen> response) {
                if (response.isSuccessful() && response.body() != null) {
                    truyen = response.body();
                    // Xử lý truyện được lấy ra

                    if (truyen != null && truyen.getAnhbia() != null) {

                        Tinhtrang.setText("Tình trạng: "+truyen.getTinhtrang());
                        Luotxem.setText("Lượt xem: " +truyen.getLuotxem());
                        Luottheodoi.setText("Lượt theo dõi: "+ truyen.getLuottheodoi());

                        scrollingTextView.setText(truyen.getTentruyen());

                        String imageUrl = "http://" + IP.ip + ":8080/webtruyentranh/images/anhbiatruyen/" + truyen.getAnhbia();

                        Glide.with(ct)
                                .load(imageUrl)
                                .into(Anhtruyen);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Truyen> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occurred: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("error", t.getMessage());
            }
        });
    }

    public void setbtnTHeodoi_Huytheodoi_Mauser_Matruyen(String Mauser,String Matruyen)
    {
        Call<List<Truyen>> call = RetrofitClient.getInstance().getMyApi().getTheodoi_userid_truyenid(Mauser,Matruyen);
        call.enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(Call<List<Truyen>> call, Response<List<Truyen>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Truyen> truyentheodoi = response.body();
                    // Xử lý truyện được lấy ra
                    if (truyentheodoi.size()>0) {
                        btnTheodoitruyen.setVisibility(View.GONE);
                        btnHuyTheodoitruyen.setVisibility(View.VISIBLE);

                        btnHuyTheodoitruyen.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                deletetheodoi(maTruyen,Mauser);

                            }
                        });



                    } else {
                        btnTheodoitruyen.setVisibility(View.VISIBLE);
                        btnTheodoitruyen.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sendtheodoi(maTruyen, Mauser);

                            }
                        });

                        btnHuyTheodoitruyen.setVisibility(View.GONE);

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Truyen>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occurred: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("error", t.getMessage());
            }
        });
    }



    private void sendtheodoi(String Matruyen, String Mauser) {

        Addtheodoi theodoit = new Addtheodoi(Matruyen,Mauser);
        Call<ResponseBody> call = RetrofitClient.getInstance().getMyApi().Addtheodoi(theodoit);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    btnTheodoitruyen.setVisibility(View.GONE);
                    btnHuyTheodoitruyen.setVisibility(View.VISIBLE);


                    Toast.makeText(Chitiettruyen_Activity.this, "Đã theo dõi cuốn tryện này!" +response.message(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Chitiettruyen_Activity.this, "Lỗi hệ thống! theo dõi truyện không thành công " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Chitiettruyen_Activity.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void deletetheodoi(String Matruyen, String Mauser) {

        try {

            Addtheodoi theodoit = new Addtheodoi(Matruyen, Mauser);
            Call<ResponseBody> call = RetrofitClient.getInstance().getMyApi().deletetheodoi(theodoit);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {

                        btnTheodoitruyen.setVisibility(View.VISIBLE);
                        btnHuyTheodoitruyen.setVisibility(View.GONE);


                        Toast.makeText(Chitiettruyen_Activity.this, "Đã hủy theo dõi cuốn tryện này!" + response.message(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(Chitiettruyen_Activity.this, "Lỗi hệ thống! hủy theo dõi truyện không thành công " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(Chitiettruyen_Activity.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }

            });
        }
        catch (Exception ex)
        {
            Toast.makeText(Chitiettruyen_Activity.this, "Lỗi hệ thống! hủy theo dõi truyện không thành công " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
