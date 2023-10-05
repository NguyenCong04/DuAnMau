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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import congntph34559.fpoly.duanmauapplication.DAO.ThanhVienDAO;
import congntph34559.fpoly.duanmauapplication.DTO.ThanhVienDTO;
import congntph34559.fpoly.duanmauapplication.R;

public class AdapterThanhVien extends RecyclerView.Adapter<AdapterThanhVien.ViewHolder> {

    Context context;
    List<ThanhVienDTO> list;

    public AdapterThanhVien(Context context, List<ThanhVienDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view1 = inflater.inflate(R.layout.item_thanh_vien,parent,false);

        return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ThanhVienDTO idThanhVien = list.get(position);
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(context);

        holder.tvMaThanhVien.setText(list.get(position).getId()+"");
        holder.tvTenThanhVien.setText(list.get(position).getTenThanhVien());
        holder.tvNgaySinh.setText(list.get(position).getNamSinh());

        holder.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo !")
                        .setMessage("Bạn có chắc xóa thành viên "+idThanhVien.getTenThanhVien()+" không ?")
                        .setCancelable(true)
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                int kq = thanhVienDAO.deleteRow(idThanhVien);

                                if (kq > 0 ){

                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    list.clear();
                                    list.addAll(thanhVienDAO.getAll());
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
                View view1 = inflater.inflate(R.layout.dialog_update_thanh_vien,null,false);

                builder.setView(view1);
                builder.setCancelable(false);

                AlertDialog dialog = builder.create();
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                AppCompatButton btnSua,btnHuy;
                EditText edTenThanhVien,edNgaySinh;

                btnSua = view1.findViewById(R.id.btnSuaSachDialogSuaThanhVien);
                btnHuy = view1.findViewById(R.id.btnHuyDialogSuaThanhVien);
                edTenThanhVien = view1.findViewById(R.id.edTenThanhVienDialogSua);
                edNgaySinh = view1.findViewById(R.id.edNgaySinhDialogSua);

                edTenThanhVien.setText(idThanhVien.getTenThanhVien());
                edNgaySinh.setText(idThanhVien.getNamSinh());



                edNgaySinh.setOnClickListener(new View.OnClickListener() {
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

                                edNgaySinh.setText(date);



                            }
                        },years,months,days);
                        pickerDialog.show();

                    }
                });


                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (kiemTra()){

                            String tenThanhVien = edTenThanhVien.getText().toString();
                            String namSinh = edNgaySinh.getText().toString();

                            idThanhVien.setNamSinh(namSinh);
                            idThanhVien.setTenThanhVien(tenThanhVien);

                            int kq = thanhVienDAO.updateRow(idThanhVien);

                            if (kq > 0){

                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                list.clear();
                                list.addAll(thanhVienDAO.getAll());
                                notifyDataSetChanged();
                                dialog.dismiss();

                            }else {

                                Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }

                    private boolean kiemTra() {

                        if (edTenThanhVien.getText().toString().equals("")||edNgaySinh.getText().toString().equals("")){

                            Toast.makeText(context, "Mời nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                            return false;

                        }
                        if (edTenThanhVien.getText().toString().length()<=3){

                            Toast.makeText(context, "Tên thành viên phải có trên 3 ký tự", Toast.LENGTH_SHORT).show();
                            return false;

                        }

                        String checkKiTuDacBiet = "^.*[~!`@#$%^&*()_=+{};'<>,/?|]+.*$";

                        if (edTenThanhVien.getText().toString().matches(checkKiTuDacBiet)){

                            Toast.makeText(context, "Tên thành viên không chứa ký tự đặc biệt", Toast.LENGTH_SHORT).show();
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


    public final static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMaThanhVien,tvTenThanhVien,tvNgaySinh;
        ImageView ivSua,ivXoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMaThanhVien = itemView.findViewById(R.id.tvMaThanhVien);
            tvNgaySinh = itemView.findViewById(R.id.tvNgaySinh);
            tvTenThanhVien = itemView.findViewById(R.id.tvTenThanhVien);
            ivSua = itemView.findViewById(R.id.ivSuaItemThanhVien);
            ivXoa = itemView.findViewById(R.id.ivXoaItemThanhVien);


        }
    }

}
