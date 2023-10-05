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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import congntph34559.fpoly.duanmauapplication.DAO.LoaiSachDAO;
import congntph34559.fpoly.duanmauapplication.DTO.LoaiSachDTO;
import congntph34559.fpoly.duanmauapplication.R;

public class AdapterLoaiSach extends RecyclerView.Adapter<AdapterLoaiSach.ViewHolder> {


    Context context;
    List<LoaiSachDTO> list;

    public AdapterLoaiSach(Context context, List<LoaiSachDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view1 = inflater.inflate(R.layout.item_loai_sach,parent,false);


        return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        LoaiSachDTO id = list.get(position);
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);

        holder.tvTenLoaiSach.setText(list.get(position).getTenLoaiSach());

        holder.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo !")
                        .setMessage("Bạn có chắc xóa loại sách "+id.getTenLoaiSach()+" không ?")
                        .setCancelable(true)
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                int kq = loaiSachDAO.deleteRow(id);

                                if (kq > 0){

                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    list.clear();
                                    list.addAll(loaiSachDAO.getAll());
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
                View view1 = inflater.inflate(R.layout.dialog_update_loai_sach,null,false);

                builder.setView(view1);
                builder.setCancelable(false);

                AlertDialog dialog = builder.create();
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                AppCompatButton btnSua,btnHuy;
                EditText edTenLoaiSach;

                btnSua = view1.findViewById(R.id.btnSuaLoaiSachDialogSua);
                btnHuy = view1.findViewById(R.id.btnHuyDialogAddLoaiSachSua);
                edTenLoaiSach = view1.findViewById(R.id.edTenLoaiSachDialogSua);

                edTenLoaiSach.setText(id.getTenLoaiSach());

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
                            id.setTenLoaiSach(edTenLoaiSach.getText().toString());

                            int kq = loaiSachDAO.updateRow(id);

                            if (kq > 0) {

                                Toast.makeText(context, "Sửa thông tin sách thành công", Toast.LENGTH_SHORT).show();
                                list.clear();
                                list.addAll(loaiSachDAO.getAll());
                                notifyDataSetChanged();
                                dialog.dismiss();

                            }else {

                                Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();

                            }

                        }

                    }

                    private boolean kiemTra() {

                        if (edTenLoaiSach.getText().toString().equals("")){

                            Toast.makeText(context, "Mời nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                            return false;

                        }
                        if (edTenLoaiSach.getText().toString().length()<=3){

                            Toast.makeText(context, "Tên loại sách phải có trên 3 ký tự", Toast.LENGTH_SHORT).show();
                            return false;

                        }
                        String checkKyTuDacBiet = "^.*[~`!@#$%^&*_=+{};:'?]+.*$";

                        if (edTenLoaiSach.getText().toString().matches(checkKyTuDacBiet)){

                            Toast.makeText(context, "Tên loại sách không chứa ký tự đặc biệt", Toast.LENGTH_SHORT).show();
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

    public  static  class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTenLoaiSach;
        ImageView ivSua,ivXoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenLoaiSach = itemView.findViewById(R.id.tvTenLoaiSach);
            ivSua = itemView.findViewById(R.id.ivSua);
            ivXoa = itemView.findViewById(R.id.ivXoa);


        }
    }

}
