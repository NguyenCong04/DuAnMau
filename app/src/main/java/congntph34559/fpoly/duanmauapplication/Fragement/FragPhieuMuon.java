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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import congntph34559.fpoly.duanmauapplication.Adapter.AdapterPhieuMuon;
import congntph34559.fpoly.duanmauapplication.DAO.PhieuMuonDAO;
import congntph34559.fpoly.duanmauapplication.DAO.SachDAO;
import congntph34559.fpoly.duanmauapplication.DAO.ThanhVienDAO;
import congntph34559.fpoly.duanmauapplication.DTO.PhieuMuonDTO;
import congntph34559.fpoly.duanmauapplication.DTO.SachDTO;
import congntph34559.fpoly.duanmauapplication.DTO.ThanhVienDTO;
import congntph34559.fpoly.duanmauapplication.R;

public class FragPhieuMuon extends Fragment {

    TextView tvChuaCoDuLieu;


    FloatingActionButton floatAddPhieuMuon;
    RecyclerView rcvPhieuMuon;
    List<PhieuMuonDTO> list;
    PhieuMuonDAO phieuMuonDAO;

    AdapterPhieuMuon adapterPhieuMuon;
    LinearLayoutManager manager;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_phieu_muon,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

//        tvChuaCoDuLieu = view.findViewById(R.id.tvChuaCoDuLieu);
        floatAddPhieuMuon = view.findViewById(R.id.floadAddPhieuMuon);
        rcvPhieuMuon  = view.findViewById(R.id.rcvPhieuMuon);
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        list = phieuMuonDAO.getAll();
        adapterPhieuMuon = new AdapterPhieuMuon(getContext(),list);
        manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcvPhieuMuon.setLayoutManager(manager);
        rcvPhieuMuon.setAdapter(adapterPhieuMuon);



        floatAddPhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (kiemTra()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    LayoutInflater inflater = getLayoutInflater();
                    View view1 = inflater.inflate(R.layout.dialog_add_phieu_muon,null,false);

                    builder.setView(view1);
                    builder.setCancelable(false);

                    AlertDialog dialog = builder.create();
                    Window window = dialog.getWindow();
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);

                    AppCompatButton btnThem,btnHuy;
                    EditText edGiaTien,edNgayMuon;
                    Spinner spTenThanhVien,spTenSach,spTrangThai;

                    btnHuy = view1.findViewById(R.id.btnHuyDialogAddPhieuMuon);
                    btnThem = view1.findViewById(R.id.btnThemPhieuMuonDialogAdd);
                    edGiaTien = view1.findViewById(R.id.edGiaThuePhieuMuonDialogAdd);
                    edNgayMuon = view1.findViewById(R.id.edNgayMuonPhieuMuonDialogAdd);
                    spTenSach = view1.findViewById(R.id.spTenSachDialogAddPhieuMuon);
                    spTenThanhVien = view1.findViewById(R.id.spTenThanhVienDialogAdd);
                    spTrangThai = view1.findViewById(R.id.spTrangThaiDialogAdd);

                    SachDAO sachDAO = new SachDAO(getContext());
                    ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());

                    List<SachDTO> listSach = sachDAO.getAll();
                    List<ThanhVienDTO> listThanhVien = thanhVienDAO.getAll();
                    List<String> listTrangThai = new ArrayList<>();
                    listTrangThai.add("Đã trả");
                    listTrangThai.add("Chưa trả");
                    listTrangThai.add("Mất");

                    ArrayAdapter<SachDTO> arrayAdapterSach = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,listSach);
                    ArrayAdapter<ThanhVienDTO> arrayAdapterThanhVien = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,listThanhVien);
                    ArrayAdapter<String> arrayAdapterTrangThai = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,listTrangThai);


                    spTenSach.setAdapter(arrayAdapterSach);
                    spTenThanhVien.setAdapter(arrayAdapterThanhVien);
                    spTrangThai.setAdapter(arrayAdapterTrangThai);

                    spTenSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            edGiaTien.setText(listSach.get(position).getGiaTien()+"");

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    edNgayMuon.setOnClickListener(new View.OnClickListener() {
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
                                    edNgayMuon.setText(date);


                                }
                            },years,months,days);
                            pickerDialog.show();
                        }
                    });







                    btnHuy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog.dismiss();

                        }
                    });
                    btnThem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (kiemTra()){
                                ThanhVienDTO idTv = (ThanhVienDTO) spTenThanhVien.getSelectedItem();
                                String idThanhVien = idTv.getTenThanhVien();
                                SachDTO idS = (SachDTO) spTenSach.getSelectedItem();
                                String idSach = idS.getTenSach();
                                String ngayMuon = edNgayMuon.getText().toString();
                                String giaThue = edGiaTien.getText().toString();
                                String trangThai = (String) spTrangThai.getSelectedItem();

                                PhieuMuonDTO objPhieuMuon = new PhieuMuonDTO();
                                objPhieuMuon.setTenThanhVien(idThanhVien);
                                objPhieuMuon.setTenSach(idSach);
                                objPhieuMuon.setNgayMuon(ngayMuon);
                                objPhieuMuon.setGiaTien(Integer.parseInt(giaThue));
                                objPhieuMuon.setTrangThai(trangThai);

                                long kq = phieuMuonDAO.addRow(objPhieuMuon);

                                if (kq > 0) {

                                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                    list.clear();
                                    list.addAll(phieuMuonDAO.getAll());
                                    adapterPhieuMuon.notifyDataSetChanged();
                                    dialog.dismiss();

                                }else {

                                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();

                                }
                            }







                        }

                        private boolean kiemTra() {

                            if (edNgayMuon.getText().toString().equals("")){

                                Toast.makeText(getContext(), "Mời chọn ngày mượn sách", Toast.LENGTH_SHORT).show();
                                return false;

                            }

                            return true;
                        }
                    });



                    dialog.show();

                }

                }



            private boolean kiemTra() {

                SachDAO sachDAO = new SachDAO(getContext());
                List<SachDTO> list1 = sachDAO.getAll();

                ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());
                List<ThanhVienDTO> listThanhVien = thanhVienDAO.getAll();


                if (list1.size() == 0){

                    Toast.makeText(getContext(), "Chưa có sách không thể làm phiếu mượn", Toast.LENGTH_SHORT).show();
                    return false;

                }
                if (listThanhVien.size() == 0){

                    Toast.makeText(getContext(), "Chưa có thành viên không thể làm phiếu mượn", Toast.LENGTH_SHORT).show();
                    return false;

                }



                return true;
            }
        });





        super.onViewCreated(view, savedInstanceState);
    }
}
