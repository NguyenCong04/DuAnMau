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

import congntph34559.fpoly.duanmauapplication.DAO.ThuThuDAO;
import congntph34559.fpoly.duanmauapplication.DTO.ThuThuDTO;
import congntph34559.fpoly.duanmauapplication.R;

public class FragDoiMatKhau extends Fragment {

    EditText edMatKhauCu,edMatKhauMoi,edNhapLaiMatKhauMoi;
    AppCompatButton btnDoiMatKhau;
    ThuThuDAO thuThuDAO;
    List<ThuThuDTO> list;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_doi_mat_khau,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        edMatKhauCu = view.findViewById(R.id.edMatKhauCu);
        edMatKhauMoi = view.findViewById(R.id.edMatKhauMoi);
        edNhapLaiMatKhauMoi = view.findViewById(R.id.edNhapLaiMatKhauMoi);
        btnDoiMatKhau = view.findViewById(R.id.btnDoiMatKhau);

        thuThuDAO = new ThuThuDAO(getContext());
        list = thuThuDAO.getAll();

        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (kiemTra()){

                    ThuThuDTO idThuThu = null;

                    for (int i = 0; i < list.size(); i++) {

                        idThuThu = list.get(i);

                        String mkCu = edMatKhauCu.getText().toString();

                        if (!idThuThu.getMatKhau().equals(mkCu)){

                            Toast.makeText(getContext(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                            return;

                        }else {

                            String mkMoi = edMatKhauMoi.getText().toString();
                            String laiMkMoi = edNhapLaiMatKhauMoi.getText().toString();

                            idThuThu.setMatKhau(mkMoi);
                            idThuThu.setNhapLaiMatKhau(laiMkMoi);

                        }
                    }
                    int kq = thuThuDAO.updateRow(idThuThu);

                    if (kq > 0){

                        Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(thuThuDAO.getAll());
                        edMatKhauCu.setText("");
                        edMatKhauMoi.setText("");
                        edNhapLaiMatKhauMoi.setText("");
                    }else {
                        Toast.makeText(getContext(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }

                }

            }

            private boolean kiemTra() {

                if (edMatKhauCu.getText().toString().equals("")||edMatKhauMoi.getText().toString().equals("")||edNhapLaiMatKhauMoi.getText().toString().equals("")){

                    Toast.makeText(getContext(), "Mời nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return false;

                }
                if (!edMatKhauMoi.getText().toString().equals(edNhapLaiMatKhauMoi.getText().toString())){

                    Toast.makeText(getContext(), "Mật khẩu mới không khớp", Toast.LENGTH_SHORT).show();
                    return false;

                }



                return true;
            }
        });




        super.onViewCreated(view, savedInstanceState);
    }
}
