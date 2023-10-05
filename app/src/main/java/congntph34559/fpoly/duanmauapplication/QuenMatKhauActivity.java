package congntph34559.fpoly.duanmauapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import congntph34559.fpoly.duanmauapplication.DAO.ThuThuDAO;
import congntph34559.fpoly.duanmauapplication.DTO.ThuThuDTO;

public class QuenMatKhauActivity extends AppCompatActivity {

    EditText edCheckTenTaiKhoan;
    AppCompatButton btnKhoiPhuc;
    ImageView ivBack;
    TextView tvDangNhap;

    ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);
        edCheckTenTaiKhoan = findViewById(R.id.edTenTaiKhoanKhoiPhuc);
        btnKhoiPhuc = findViewById(R.id.btnCheckTaiKhoan);
        ivBack = findViewById(R.id.ivBack);
        tvDangNhap = findViewById(R.id.tvDangNhap);
        thuThuDAO = new ThuThuDAO(this);


        btnKhoiPhuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (kiemTra()){

                    String tenTaiKhoan = edCheckTenTaiKhoan.getText().toString();

                    boolean kq = thuThuDAO.checkTaiKhoan(tenTaiKhoan);

                    if (kq){

                        startActivity(new Intent(QuenMatKhauActivity.this,ResPassActivity2.class));
                        finish();


                    }else {

                        Toast.makeText(QuenMatKhauActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();

                    }



                }

            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(QuenMatKhauActivity.this,LoginActivity.class));
                finish();

            }
        });
        tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuenMatKhauActivity.this,LoginActivity.class));
                finish();
            }
        });


    }

    private boolean kiemTra() {

        if (edCheckTenTaiKhoan.getText().toString().equals("")){

            Toast.makeText(this, "Mời nhập tên tài khoản", Toast.LENGTH_SHORT).show();
            return false;

        }

        return true;
    }
}