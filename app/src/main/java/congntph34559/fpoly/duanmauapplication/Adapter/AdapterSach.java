package congntph34559.fpoly.duanmauapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.net.Inet4Address;
import java.util.List;

import congntph34559.fpoly.duanmauapplication.DAO.LoaiSachDAO;
import congntph34559.fpoly.duanmauapplication.DAO.SachDAO;
import congntph34559.fpoly.duanmauapplication.DTO.LoaiSachDTO;
import congntph34559.fpoly.duanmauapplication.DTO.SachDTO;
import congntph34559.fpoly.duanmauapplication.R;

public class AdapterSach extends RecyclerView.Adapter<AdapterSach.ViewHolder>{

    Context context;
    List<SachDTO> list;

    public AdapterSach(Context context, List<SachDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View  view1 = inflater.inflate(R.layout.item_sach,parent,false);


        return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SachDTO id = list.get(position);
        SachDAO  sachDAO = new SachDAO(context);

        holder.tvMaSach.setText(list.get(position).getId()+"");
        holder.tvTenSach.setText(list.get(position).getTenSach());
        holder.tvTenLoaiSach.setText(list.get(position).getTenLoaiSach());
        holder.tvGiaThue.setText(list.get(position).getGiaTien()+"");

        holder.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo !")
                        .setCancelable(true)
                        .setMessage("Bạn có chắc xóa sách "+id.getTenSach()+" không ?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                int kq = sachDAO.deleteRow(id);
                                if (kq > 0) {

                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    list.clear();
                                    list.addAll(sachDAO.getAll());
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
                View view1 = inflater.inflate(R.layout.dialog_update_sach,null,false);

                builder.setView(view1);
                builder.setCancelable(false);

                AlertDialog dialog = builder.create();
                Window window = dialog.getWindow();
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);

                AppCompatButton btnHuy,btnSua;
                EditText edTenSach,edGiaTien;
                Spinner spTenLoaiSach;
                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);

                btnHuy = view1.findViewById(R.id.btnHuyDialogUpdateSach);
                btnSua = view1.findViewById(R.id.btnSuaSachDialogUpdateSach);
                edGiaTien = view1.findViewById(R.id.edGiaThueSachDialogUpdate);
                edTenSach = view1.findViewById(R.id.edTenSachDialogUpdate);
                spTenLoaiSach =view1.findViewById(R.id.spTenLoaiSachDialogUpdate);




                List<LoaiSachDTO> listLoaiSach = loaiSachDAO.getAll();
                ArrayAdapter<LoaiSachDTO> adapterLoaiSach = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,listLoaiSach);
                spTenLoaiSach.setAdapter(adapterLoaiSach);

                edGiaTien.setText(id.getGiaTien()+"");
                edTenSach.setText(id.getTenSach());
                int viTri = 0;
                for (int i = 0; i < listLoaiSach.size() ; i++) {

                    if (id.getTenLoaiSach().equals(listLoaiSach.get(i).getTenLoaiSach())){

                        viTri = i;
                        Toast.makeText(context, ""+viTri, Toast.LENGTH_SHORT).show();

                    }

                }
                spTenLoaiSach.setSelection(viTri);


                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (kiemTra()){

                            String tenSach = edTenSach.getText().toString();
                            String giaTien = edGiaTien.getText().toString();
                            LoaiSachDTO idLoaiSach = (LoaiSachDTO) spTenLoaiSach.getSelectedItem();
                            int idLs = idLoaiSach.getId();

                            id.setTenSach(tenSach);
                            id.setGiaTien(Integer.parseInt(giaTien));
                            id.setIdLoaiSach(idLs);

                            int kq = sachDAO.updateRow(id);

                            if (kq > 0){

                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                list.clear();
                                list.addAll(sachDAO.getAll());
                                notifyDataSetChanged();
                                dialog.dismiss();


                            }else {

                                Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();

                            }

                        }

                    }

                    private boolean kiemTra() {

                        if (edGiaTien.getText().toString().equals("")||edTenSach.getText().toString().equals("")){

                            Toast.makeText(context, "Mời nhập thông tin sách", Toast.LENGTH_SHORT).show();
                            return false;

                        }
                        if (edTenSach.getText().toString().length()<=3){

                            Toast.makeText(context, "Tên sách phải có trên 3 ký tự", Toast.LENGTH_SHORT).show();
                            return false;

                        }
                        String checkKiTuDacBiet = "^.*[`~!@$%^&*_=+{}|;:'<>/]+.*$";

                        if (edTenSach.getText().toString().matches(checkKiTuDacBiet)){

                            Toast.makeText(context, "Tên sách không được chứa ký tự đặc biệt", Toast.LENGTH_SHORT).show();
                            return false;

                        }

                        try {

                            Integer.parseInt(edGiaTien.getText().toString());

                        }catch (NumberFormatException ex){

                            Toast.makeText(context, "Giá tiền phải là số", Toast.LENGTH_SHORT).show();
                            return false;

                        }
                        if (Integer.parseInt(edGiaTien.getText().toString())<=0){

                            Toast.makeText(context, "Giá tiền thuê phải lớn hơn 0", Toast.LENGTH_SHORT).show();
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


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMaSach,tvTenSach,tvTenLoaiSach,tvGiaThue;
        ImageView ivSua,ivXoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaSach = itemView.findViewById(R.id.tvMaSach);
            tvTenSach = itemView.findViewById(R.id.tvTenSach);
            tvTenLoaiSach = itemView.findViewById(R.id.tvTenLoaiSachItemSach);
            tvGiaThue = itemView.findViewById(R.id.tvGiaTienSach);
            ivSua = itemView.findViewById(R.id.ivSuaItemSach);
            ivXoa = itemView.findViewById(R.id.ivXoaItemSach);


        }
    }

}
