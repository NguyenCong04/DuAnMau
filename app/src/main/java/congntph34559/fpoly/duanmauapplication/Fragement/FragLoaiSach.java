package congntph34559.fpoly.duanmauapplication.Fragement;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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

import java.util.List;

import congntph34559.fpoly.duanmauapplication.Adapter.AdapterLoaiSach;
import congntph34559.fpoly.duanmauapplication.DAO.LoaiSachDAO;
import congntph34559.fpoly.duanmauapplication.DTO.LoaiSachDTO;
import congntph34559.fpoly.duanmauapplication.R;

public class FragLoaiSach extends Fragment {

    FloatingActionButton floatAdd;
    LoaiSachDAO loaiSachDAO;
    List<LoaiSachDTO> list;
    RecyclerView  recyclerView;
    LinearLayoutManager manager;
    AdapterLoaiSach adapterLoaiSach;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_loai_sach,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        floatAdd = view.findViewById(R.id.floatAddLoaiSach);
        recyclerView = view.findViewById(R.id.rcyLoaiSach);
        loaiSachDAO = new LoaiSachDAO(getContext());
        list = loaiSachDAO.getAll();
        adapterLoaiSach = new AdapterLoaiSach(getContext(),list);
        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterLoaiSach);


        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view1 = inflater.inflate(R.layout.dialog_add_loai_sach,null,false);

                builder.setView(view1);
                builder.setCancelable(false);

                AlertDialog dialog = builder.create();
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                AppCompatButton btnThem,btnHuy;
                EditText edTenLoaiSach;


                btnThem = view1.findViewById(R.id.btnThemLoaiSachDialogAdd);
                btnHuy = view1.findViewById(R.id.btnHuyDialogAddLoaiSach);
                edTenLoaiSach = view1.findViewById(R.id.edTenLoaiSach);

                btnThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (kiemTra()){

                            String tenLoaiSach = edTenLoaiSach.getText().toString();

                            LoaiSachDTO objLoaiSach = new LoaiSachDTO();
                            objLoaiSach.setTenLoaiSach(tenLoaiSach);

                            long kq = loaiSachDAO.addRow(objLoaiSach);

                            if (kq > 0){

                                Toast.makeText(getContext(), "Thêm thành công loại sách", Toast.LENGTH_SHORT).show();
                                list.clear();
                                list.addAll(loaiSachDAO.getAll());
                                adapterLoaiSach.notifyDataSetChanged();
                                dialog.dismiss();

                            }else {

                                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();

                            }

                        }


                    }

                    private boolean kiemTra() {

                        if (edTenLoaiSach.getText().toString().equals("")){

                            Toast.makeText(getContext(), "Mời nhập tên loại sách", Toast.LENGTH_SHORT).show();
                            return false;

                        }
                        if (edTenLoaiSach.getText().toString().length()<=3){

                            Toast.makeText(getContext(), "Tên loại sách phải có trên 3 ký tự", Toast.LENGTH_SHORT).show();
                            return false;

                        }

                        String checkKiTuDacBiet = "^.*[~`!@#$%^&*_=+;'<>?/]+.*$";

                        if (edTenLoaiSach.getText().toString().matches(checkKiTuDacBiet)){

                            Toast.makeText(getContext(), "Tên loại sách không chứa kí tự đặc biệt", Toast.LENGTH_SHORT).show();
                            return false;

                        }


                        return true;
                    }
                });

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                dialog.show();

            }
        });



        super.onViewCreated(view, savedInstanceState);
    }
}
