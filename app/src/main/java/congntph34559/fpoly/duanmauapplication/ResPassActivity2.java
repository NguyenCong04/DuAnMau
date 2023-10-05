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

public class ResPassActivity2 extends AppCompatActivity {

    ImageView ivBack;
    EditText edMatKhauMoi,edLaiMatKhauMoi;
    AppCompatButton btnDatLaiMk;

    ThuThuDAO thuThuDAO;
    List<ThuThuDTO> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_pass2);
        ivBack = findViewById(R.id.ivBackLayout);
        btnDatLaiMk = findViewById(R.id.btnDatLaiMk);
        edLaiMatKhauMoi = findViewById(R.id.edLaiMkMoi);
        edMatKhauMoi = findViewById(R.id.edMkMoi);
        thuThuDAO = new ThuThuDAO(this);
        list = thuThuDAO.getAll();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResPassActivity2.this,QuenMatKhauActivity.class));
            }
        });

        btnDatLaiMk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (kiemTra()){

                    ThuThuDTO idThuThu = new ThuThuDTO();

                    for (int i = 0; i < list.size(); i++) {

                        idThuThu = list.get(i);

                    }

                    String mkMoi = edMatKhauMoi.getText().toString();
                    String laiMkMoi = edLaiMatKhauMoi.getText().toString();

                    idThuThu.setMatKhau(mkMoi);
                    idThuThu.setNhapLaiMatKhau(laiMkMoi);

                    int kq = thuThuDAO.updateRow(idThuThu);

                    if (kq > 0){

                        Toast.makeText(ResPassActivity2.this, "Đặt lại mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(thuThuDAO.getAll());
                        edLaiMatKhauMoi.setText("");
                        edMatKhauMoi.setText("");
                        startActivity(new Intent(ResPassActivity2.this,LoginActivity.class));

                    }else {
                        Toast.makeText(ResPassActivity2.this, "Đặt lại mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });



    }

    private boolean kiemTra() {

        if (edMatKhauMoi.getText().toString().equals("")||edLaiMatKhauMoi.getText().toString().equals("")){

            Toast.makeText(this, "Mời nhập mật khẩu mới", Toast.LENGTH_SHORT).show();
            return false;

        }
        if (edMatKhauMoi.getText().toString().length()<=3){

            Toast.makeText(this, "Mật khẩu phải lớn hơn 3 ký tự", Toast.LENGTH_SHORT).show();
            return false;

        }

        if (!edMatKhauMoi.getText().toString().equals(edLaiMatKhauMoi.getText().toString())){

            Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            return false;

        }



        return true;
    }
}