package congntph34559.fpoly.duanmauapplication.Fragement;

import android.app.Application;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import congntph34559.fpoly.duanmauapplication.DAO.ThongKeDAO;
import congntph34559.fpoly.duanmauapplication.R;

public class FragDoanhThu extends Fragment {

    EditText edNgayBatDau;
    EditText edNgayKetThuc;

    AppCompatButton btnThongKeDoanhThu;

    ThongKeDAO thongKeDAO;
    TextView tvDoanhThu;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frag_doanh_thu,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        edNgayBatDau = view.findViewById(R.id.edNhapNgayBatDau);
        edNgayKetThuc = view.findViewById(R.id.edNgayKetThuc);
        btnThongKeDoanhThu = view.findViewById(R.id.btnThongKeDoanhThu);
        tvDoanhThu = view.findViewById(R.id.tvDoanhThu);
        thongKeDAO = new ThongKeDAO(getContext());

        edNgayKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int years = calendar.get(Calendar.YEAR);
                int months = calendar.get(Calendar.MONTH);
                int days = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog pickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.set(year,month,dayOfMonth);
                        String date = simpleDateFormat.format(calendar1.getTime());
                        edNgayKetThuc.setText(date);


                    }
                },years,months,days);
                pickerDialog.show();


            }
        });
        edNgayBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int months = calendar.get(Calendar.MONTH);
                int days = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog pickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.set(year,month,dayOfMonth);
                        String date = simpleDateFormat.format(calendar1.getTime());
                        edNgayBatDau.setText(date);


                    }
                },year,months,days);
                pickerDialog.show();


            }
        });

        btnThongKeDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (kiemTra()){

                    String ngayBatDau = edNgayBatDau.getText().toString();
                    String ngayKetThuc = edNgayKetThuc.getText().toString();

                    int doanhThu = thongKeDAO.getDoanhThu(ngayBatDau,ngayKetThuc);

                    if (doanhThu > 0){

                        tvDoanhThu.setText(doanhThu+"");

                    }else {

                        Toast.makeText(getContext(), "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();

                    }


                }


            }

            private boolean kiemTra() {


                if (edNgayBatDau.getText().toString().equals("")||edNgayKetThuc.getText().toString().equals("")){

                    Toast.makeText(getContext(), "Mời chọn ngày", Toast.LENGTH_SHORT).show();
                    return false;

                }



                return true;

            }
        });



        super.onViewCreated(view, savedInstanceState);
    }
}
