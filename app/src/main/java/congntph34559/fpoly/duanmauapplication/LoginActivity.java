package congntph34559.fpoly.duanmauapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import congntph34559.fpoly.duanmauapplication.DAO.ThuThuDAO;
import congntph34559.fpoly.duanmauapplication.DTO.ThuThuDTO;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText edMatKhau,edTaiKhoan;
    AppCompatButton btnDangNhap;

    ThuThuDAO thuThuDAO;

    List<ThuThuDTO> list;
    TextView tvQuenMatKhau;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edMatKhau = findViewById(R.id.edMatKhau);
        edTaiKhoan = findViewById(R.id.edTaiKhoan);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        tvQuenMatKhau = findViewById(R.id.tvQuenMatKhau);
        thuThuDAO = new ThuThuDAO(this);
        list = thuThuDAO.getAll();



        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (kiemTra()){

                    String tenTaiKhoan = edTaiKhoan.getText().toString();
                    String matKhau = edMatKhau.getText().toString();

                    boolean kq = thuThuDAO.checkUesrPass(tenTaiKhoan,matKhau);

                    if (kq == true || tenTaiKhoan.equals("admin") && matKhau.equals("admin123")){


                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                        rememberUser(tenTaiKhoan,matKhau);

                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("user",tenTaiKhoan);
                        startActivity(intent);
                        finish();

                    }else {

                        Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();

                    }

                }

            }
        });

        tvQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this,QuenMatKhauActivity.class));
                finish();

            }
        });







    }

    private boolean kiemTra() {


        if (edMatKhau.getText().toString().equals("")||edTaiKhoan.getText().toString().equals("")){

            Toast.makeText(this, "Mời nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;

        }



        return true;
    }
    public void rememberUser(String tk,String pass){

        SharedPreferences sharedPreferences = getSharedPreferences("FILE_USER",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("USERNAME",tk);
        editor.putString("PASS",pass);
        editor.commit();


    }




}