package congntph34559.fpoly.duanmauapplication.Fragement;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import congntph34559.fpoly.duanmauapplication.Adapter.AdapterThanhVien;
import congntph34559.fpoly.duanmauapplication.DAO.ThanhVienDAO;
import congntph34559.fpoly.duanmauapplication.DTO.ThanhVienDTO;
import congntph34559.fpoly.duanmauapplication.R;

public class FragThanhVien extends Fragment {


    FloatingActionButton floatAddThanhVien;
    RecyclerView recyclerView;
    AdapterThanhVien adapterThanhVien;
    List<ThanhVienDTO> list;
    LinearLayoutManager manager;
    ThanhVienDAO thanhVienDAO;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_thanh_vien,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        floatAddThanhVien = view.findViewById(R.id.floatAddThanhVien);
        recyclerView = view.findViewById(R.id.rcvThanhVien);
        thanhVienDAO = new ThanhVienDAO(getContext());
        list = thanhVienDAO.getAll();
        adapterThanhVien = new AdapterThanhVien(getContext(),list);
        manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterThanhVien);

        floatAddThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                LayoutInflater  inflater = getLayoutInflater();
                View view1 = inflater.inflate(R.layout.dialog_add_thanh_vien,null,false);

                builder.setView(view1);
                builder.setCancelable(false);

                AlertDialog dialog = builder.create();
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                AppCompatButton btnHuy,btnThem;
                EditText edTenThanhVien,edNgaySinh;

                btnHuy = view1.findViewById(R.id.btnHuyDialogAddThanhVien);
                btnThem = view1.findViewById(R.id.btnThemSachDialogAddThanhVien);
                edTenThanhVien = view1.findViewById(R.id.edTenThanhVienDialogAdd);
                edNgaySinh = view1.findViewById(R.id.edNgaySinhDialogAdd);

                edNgaySinh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Calendar calendar = Calendar.getInstance();
                        int years  = calendar.get(Calendar.YEAR);
                        int months = calendar.get(Calendar.MONTH);
                        int days = calendar.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog pickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                Calendar calendar1 = Calendar.getInstance();
                                calendar1.set(year,month,dayOfMonth);

                                String date = simpleDateFormat.format(calendar1.getTime());

                                edNgaySinh.setText(date);


                            }
                        },years,months,days);
                        pickerDialog.show();




                    }
                });


                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();;
                    }
                });
                btnThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (kiemTra()){

                            String tenThanhVien = edTenThanhVien.getText().toString();
                            String ngaySinh = edNgaySinh.getText().toString();

                            ThanhVienDTO objThanhVien = new ThanhVienDTO();
                            objThanhVien.setTenThanhVien(tenThanhVien);
                            objThanhVien.setNamSinh(ngaySinh);

                            long kq = thanhVienDAO.addRow(objThanhVien);

                            if (kq > 0){

                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                list.clear();
                                list.addAll(thanhVienDAO.getAll());
                                adapterThanhVien.notifyDataSetChanged();
                                dialog.dismiss();

                            }else {

                                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();

                            }

                        }


                    }

                    private boolean kiemTra() {

                        if (edTenThanhVien.getText().toString().equals("")||edNgaySinh.getText().toString().equals("")){

                            Toast.makeText(getContext(), "Mời nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                            return false;

                        }
                        if (edTenThanhVien.getText().toString().length()<=3){

                            Toast.makeText(getContext(), "Tên thành viên phải lớn hơn 3 ký tự", Toast.LENGTH_SHORT).show();
                            return false;

                        }

                        String checkKiTuDacBiet = "^.*[`~!@#$%^&*_=+{}:<>/?|]+.*$";

                        if (edTenThanhVien.getText().toString().matches(checkKiTuDacBiet)){

                            Toast.makeText(getContext(), "Tên thành viên không chứa ký tự đặc biệt", Toast.LENGTH_SHORT).show();
                            return false;

                        }



                        return true;
                    }
                });




                dialog.show();


            }
        });




        super.onViewCreated(view, savedInstanceState);
    }
}
