package congntph34559.fpoly.duanmauapplication.Adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import congntph34559.fpoly.duanmauapplication.DAO.PhieuMuonDAO;
import congntph34559.fpoly.duanmauapplication.DAO.SachDAO;
import congntph34559.fpoly.duanmauapplication.DAO.ThanhVienDAO;
import congntph34559.fpoly.duanmauapplication.DTO.PhieuMuonDTO;
import congntph34559.fpoly.duanmauapplication.DTO.SachDTO;
import congntph34559.fpoly.duanmauapplication.DTO.ThanhVienDTO;
import congntph34559.fpoly.duanmauapplication.R;

public class AdapterPhieuMuon extends RecyclerView.Adapter<AdapterPhieuMuon.ViewHolder> {

    Context context;
    List<PhieuMuonDTO> list;

    public AdapterPhieuMuon(Context context, List<PhieuMuonDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view1 = inflater.inflate(R.layout.item_phieu_muon,parent,false);

        return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PhieuMuonDTO id = list.get(position);
        PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(context);

        holder.tvIdPhieu.setText(list.get(position).getIdPhieuMuon()+"");
        holder.tvHoTen.setText(list.get(position).getTenThanhVien());
        holder.tvTenSach.setText(list.get(position).getTenSach());
        holder.tvNgayMuon.setText(list.get(position).getNgayMuon());
        holder.tvGiaThue.setText(list.get(position).getGiaTien()+"");
        holder.tvTrangThai.setText(list.get(position).getTrangThai());

        if (list.get(position).getTrangThai().equals("Đã trả")){

            holder.tvTrangThai.setTextColor(Color.GREEN);

        } else if (list.get(position).getTrangThai().equals("Chưa trả")) {

            holder.tvTrangThai.setTextColor(Color.parseColor("#FF9900"));

        } else if (list.get(position).getTrangThai().equals("Mất")) {

            holder.tvTrangThai.setTextColor(Color.RED);

        }


        holder.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo !")
                        .setMessage("Bạn có chắc chắn xóa không ?")
                        .setCancelable(true)
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                int kq = phieuMuonDAO.deleteRow(id);

                                if (kq > 0){

                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    list.clear();
                                    list.addAll(phieuMuonDAO.getAll());
                                    notifyDataSetChanged();


                                }else {

                                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();

                                }


                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();

                            }
                        })
                        .show();



            }
        });

        holder.ivSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view1 = inflater.inflate(R.layout.dialog_update_phieu_muon,null,false);

                builder.setView(view1);
                builder.setCancelable(false);

                AlertDialog dialog = builder.create();
                Window window = dialog.getWindow();
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);

                AppCompatButton btnSua,btnHuy;
                EditText edGiaTien,edNgayMuon;
                Spinner spTenThanhVien,spTenSach,spTrangThai;

                btnHuy = view1.findViewById(R.id.btnHuyDialogUPdatePhieuMuon);
                btnSua = view1.findViewById(R.id.btnSuaPhieuMuonDialogAdd);
                edGiaTien = view1.findViewById(R.id.edGiaThuePhieuMuonDialogSua);
                edNgayMuon = view1.findViewById(R.id.edNgayMuonPhieuMuonDialogSua);
                spTenSach = view1.findViewById(R.id.spTenSachDialogSuaPhieuMuon);
                spTenThanhVien = view1.findViewById(R.id.spTenThanhVienDialogSua);
                spTrangThai = view1.findViewById(R.id.spTrangThaiDialogSua);

                SachDAO sachDAO = new SachDAO(context);
                ThanhVienDAO thanhVienDAO = new ThanhVienDAO(context);

                List<SachDTO> listSach = sachDAO.getAll();
                List<ThanhVienDTO> listThanhVien = thanhVienDAO.getAll();
                List<String> listTrangThai = new ArrayList<>();
                listTrangThai.add("Đã trả");
                listTrangThai.add("Chưa trả");
                listTrangThai.add("Mất");

                ArrayAdapter<SachDTO> arrayAdapterSach = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,listSach);
                ArrayAdapter<ThanhVienDTO> arrayAdapterThanhVien = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,listThanhVien);
                ArrayAdapter<String> arrayAdapterTrangThai = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,listTrangThai);


                spTenSach.setAdapter(arrayAdapterSach);
                spTenThanhVien.setAdapter(arrayAdapterThanhVien);
                spTrangThai.setAdapter(arrayAdapterTrangThai);

                int viTriHoTen=0;
                for (int i = 0; i < listThanhVien.size(); i++) {

                    if (id.getTenThanhVien().equals(listThanhVien.get(i).getTenThanhVien())){
                        viTriHoTen = i;

                    }
                }
                spTenThanhVien.setSelection(viTriHoTen);

                int viTriSach = 0;
                for (int i = 0; i < listSach.size(); i++) {

                    if (id.getTenSach().equals(listSach.get(i).getTenSach())){

                        viTriSach = i;

                    }

                }
                spTenSach.setSelection(viTriSach);



                edNgayMuon.setText(id.getNgayMuon());

                int viTriTrangThai = 0;
                for (int i = 0; i < listTrangThai.size(); i++) {

                    if (id.getTrangThai().equals(listTrangThai.get(i).toString())){

                        viTriTrangThai = i;

                    }

                }
                spTrangThai.setSelection(viTriTrangThai);



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

                        DatePickerDialog pickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
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
                btnSua.setOnClickListener(new View.OnClickListener() {
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
                            id.setTenThanhVien(idThanhVien);
                            id.setTenSach(idSach);
                            id.setNgayMuon(ngayMuon);
                            id.setGiaTien(Integer.parseInt(giaThue));
                            id.setTrangThai(trangThai);

                            int kq = phieuMuonDAO.updateRow(id);

                            if (kq > 0) {

                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                list.clear();
                                list.addAll(phieuMuonDAO.getAll());
                                notifyDataSetChanged();
                                dialog.dismiss();

                            }else {

                                Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();

                            }
                        }







                    }

                    private boolean kiemTra() {

                        if (edNgayMuon.getText().toString().equals("")){

                            Toast.makeText(context, "Mời chọn ngày mượn sách", Toast.LENGTH_SHORT).show();
                            return false;

                        }

                        return true;
                    }
                });



                dialog.show();




            }
        });



    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder {

        TextView tvHoTen,tvTenSach,tvNgayMuon, tvGiaThue,tvTrangThai,tvIdPhieu;
        ImageView ivSua,ivXoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHoTen = itemView.findViewById(R.id.tvTenThanhVienPhieuMuon);
            tvTenSach = itemView.findViewById(R.id.tvTenSachItemPhieuMuon);
            tvIdPhieu = itemView.findViewById(R.id.tvMaPhieu);
            tvNgayMuon = itemView.findViewById(R.id.tvNgayMuonItemPhieuMuon);
            tvGiaThue = itemView.findViewById(R.id.tvGiaTienSachItemPhieuMuon);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThaiPhieuMuon);
            ivSua = itemView.findViewById(R.id.ivSuaItemPhieuMuon);
            ivXoa = itemView.findViewById(R.id.ivXoaItemPhieuMuon);


        }
    }

}

