package congntph34559.fpoly.duanmauapplication.Fragement;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import congntph34559.fpoly.duanmauapplication.Adapter.AdapterSach;
import congntph34559.fpoly.duanmauapplication.DAO.LoaiSachDAO;
import congntph34559.fpoly.duanmauapplication.DAO.SachDAO;
import congntph34559.fpoly.duanmauapplication.DTO.LoaiSachDTO;
import congntph34559.fpoly.duanmauapplication.DTO.SachDTO;
import congntph34559.fpoly.duanmauapplication.R;

public class FragSach extends Fragment {


    FloatingActionButton floatAdd;
    RecyclerView recyclerView;
    List<SachDTO> list;
    LinearLayoutManager manager;
    AdapterSach adapterSach;
    SachDAO sachDAO;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_sach,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        floatAdd = view.findViewById(R.id.floatAddSach);
        recyclerView = view.findViewById(R.id.rcvSach);
        sachDAO = new SachDAO(getContext());
        list = sachDAO.getAll();
        adapterSach = new AdapterSach(getContext(),list);
        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterSach);


        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (kiemTra()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    LayoutInflater inflater = getLayoutInflater();
                    View view1 = inflater.inflate(R.layout.dialog_add_sach,null,false);

                    builder.setView(view1);
                    builder.setCancelable(false);

                    AlertDialog dialog = builder.create();
                    Window window = dialog.getWindow();
                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    AppCompatButton btnHuy,btnThem;
                    EditText edTenSach,edGiaThue;
                    Spinner spTenLoaiSach;

                    btnHuy = view1.findViewById(R.id.btnHuyDialogAddSach);
                    btnThem = view1.findViewById(R.id.btnThemSachDialogAddSach);
                    edTenSach = view1.findViewById(R.id.edTenSachDialogAdd);
                    edGiaThue = view1.findViewById(R.id.edGiaThueSachDialogAdd);
                    spTenLoaiSach = view1.findViewById(R.id.spTenLoaiSach);



                    //Khởi tạo spinner tenLoaiSach

                    LoaiSachDAO loaiSachDAO = new LoaiSachDAO(getContext());
                    List<LoaiSachDTO> listLoaiSach = loaiSachDAO.getAll();
                    ArrayAdapter<LoaiSachDTO> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,listLoaiSach);
                    spTenLoaiSach.setAdapter(arrayAdapter);




                    btnThem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String tenSach = edTenSach.getText().toString();
                            String giaThue = edGiaThue.getText().toString();
                            LoaiSachDTO id = (LoaiSachDTO) spTenLoaiSach.getSelectedItem();
                            int idLoaiSach = id.getId();

                            SachDTO objSach = new SachDTO();
                            objSach.setTenSach(tenSach);
                            objSach.setGiaTien(Integer.parseInt(giaThue));
                            objSach.setIdLoaiSach(idLoaiSach);

                            long kq = sachDAO.addRow(objSach);

                            if (kq > 0){

                                Toast.makeText(getContext(), "Thêm thành công thông tin sách", Toast.LENGTH_SHORT).show();
                                list.clear();
                                list.addAll(sachDAO.getAll());
                                adapterSach.notifyDataSetChanged();
                                dialog.dismiss();

                            }else {

                                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();


                            }
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



            }

            private boolean kiemTra() {

                LoaiSachDAO  loaiSachDAO = new LoaiSachDAO(getContext());
                List<LoaiSachDTO> list1 = loaiSachDAO.getAll();

                if (list1.size() == 0){

                    Toast.makeText(getContext(), "Chưa có loại sách không thể thêm sách", Toast.LENGTH_SHORT).show();
                    return false;


                }


                return true;
            }
        });






        super.onViewCreated(view, savedInstanceState);
    }
}
