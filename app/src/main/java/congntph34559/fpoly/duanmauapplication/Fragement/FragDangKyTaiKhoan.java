package congntph34559.fpoly.duanmauapplication.Fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import java.util.List;

import congntph34559.fpoly.duanmauapplication.Adapter.AdapterDanhSachTaiKhoan;
import congntph34559.fpoly.duanmauapplication.DAO.ThuThuDAO;
import congntph34559.fpoly.duanmauapplication.DTO.ThuThuDTO;
import congntph34559.fpoly.duanmauapplication.R;

public class FragDangKyTaiKhoan extends Fragment {


    EditText edTaiKhoan,edMatKhau,edNhapLaiMatKhau,edHoTen;
    AppCompatButton btnDangKy;

    ThuThuDAO thuThuDAO;
    List<ThuThuDTO> list;
    AdapterDanhSachTaiKhoan adapterDanhSachTaiKhoan;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_dang_ky_tai_khoan,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        btnDangKy = view.findViewById(R.id.btnDangKyTaiKhoan);
        edMatKhau = view.findViewById(R.id.edMatKhauDangKy);
        edNhapLaiMatKhau = view.findViewById(R.id.edLaiMatKhauDangKy);
        edTaiKhoan = view.findViewById(R.id.edTenTaiKhoanDangKy);
        edHoTen = view.findViewById(R.id.edHoTenDangKyTaiKhoan);

        thuThuDAO = new ThuThuDAO(getContext());
        list = thuThuDAO.getAll();
        adapterDanhSachTaiKhoan = new AdapterDanhSachTaiKhoan(getContext(),list);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (kiemTra()){

                    String hoTen = edHoTen.getText().toString();
                    String taiKhoan = edTaiKhoan.getText().toString();
                    String matKhau = edMatKhau.getText().toString();
                    String laiMatKhau = edNhapLaiMatKhau.getText().toString();


                    ThuThuDTO objThuThu = new ThuThuDTO();
                    objThuThu.setHoTen(hoTen);
                    objThuThu.setTenTaiKhoan(taiKhoan);
                    objThuThu.setMatKhau(matKhau);
                    objThuThu.setNhapLaiMatKhau(laiMatKhau);

                    long kq = thuThuDAO.addRow(objThuThu);

                    if (kq > 0){

                        Toast.makeText(getContext(), "Thêm thành công ", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(thuThuDAO.getAll());
                        adapterDanhSachTaiKhoan.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Danh sách"+list.size(), Toast.LENGTH_SHORT).show();
                        edHoTen.setText("");
                        edTaiKhoan.setText("");
                        edNhapLaiMatKhau.setText("");
                        edMatKhau.setText("");

                    }else {

                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();

                    }

                }



            }

            private boolean kiemTra() {


                if (edMatKhau.getText().toString().equals("")
                        || edTaiKhoan.getText().toString().equals("")
                        ||edHoTen.getText().toString().equals("")
                        ||edNhapLaiMatKhau.getText().toString().equals("")){

                    Toast.makeText(getContext(), "Mời nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return false;

                }

                if (edHoTen.getText().toString().length()<=5){


                    Toast.makeText(getContext(), "Họ tên phải trên 5 ký tự", Toast.LENGTH_SHORT).show();
                    return false;

                }
                if (edTaiKhoan.getText().toString().length()<=4){

                    Toast.makeText(getContext(), "Tên tài khoản phải lớn hơn 4 ký tự", Toast.LENGTH_SHORT).show();

                }

                if (edMatKhau.getText().toString().length()<5){

                    Toast.makeText(getContext(), "Mật khẩu phải có trên 5 ký tự", Toast.LENGTH_SHORT).show();
                    return false;

                }
                if (!edMatKhau.getText().toString().equals(edNhapLaiMatKhau.getText().toString())){

                    Toast.makeText(getContext(), "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    return false;

                }

                String checkKiTuDacBiet = "^.*[`~!@#$%^&*()_+={};:'<>?/|]+.*$";

                if (edTaiKhoan.getText().toString().matches(checkKiTuDacBiet)){

                    Toast.makeText(getContext(), "Tên tài khoản không được chứa ký tự đặc biệt", Toast.LENGTH_SHORT).show();
                    return false;

                }





                return true;
            }
        });





        super.onViewCreated(view, savedInstanceState);
    }
}
