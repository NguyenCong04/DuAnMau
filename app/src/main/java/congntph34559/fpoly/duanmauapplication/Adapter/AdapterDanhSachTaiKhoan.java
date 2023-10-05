package congntph34559.fpoly.duanmauapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import congntph34559.fpoly.duanmauapplication.DAO.ThuThuDAO;
import congntph34559.fpoly.duanmauapplication.DTO.ThuThuDTO;
import congntph34559.fpoly.duanmauapplication.R;

public class AdapterDanhSachTaiKhoan extends RecyclerView.Adapter<AdapterDanhSachTaiKhoan.ViewHolder>{

    Context context;
    List<ThuThuDTO> list;

    public AdapterDanhSachTaiKhoan(Context context, List<ThuThuDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View  view1 = inflater.inflate(R.layout.item_tai_khoan,parent,false);


        return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ThuThuDTO id = list.get(position);
        ThuThuDAO thuThuDAO = new ThuThuDAO(context);

        holder.tvMaTk.setText(list.get(position).getIdMaThuThu()+"");
        holder.tvTenTaiKhoan.setText(list.get(position).getTenTaiKhoan());
        holder.tvHoTen.setText(list.get(position).getHoTen());
        holder.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder  builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo !")
                        .setCancelable(true)
                        .setMessage("Bạn có chắc xóa tài khoản không ?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                int kq = thuThuDAO.deleteRow(id);

                                if (kq > 0){

                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    list.clear();
                                    list.addAll(thuThuDAO.getAll());
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



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvHoTen,tvTenTaiKhoan,tvMaTk;
        ImageView ivXoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaTk = itemView.findViewById(R.id.tvMaTaiKhoan);
            tvHoTen = itemView.findViewById(R.id.tvHoTenThuThu);
            tvTenTaiKhoan = itemView.findViewById(R.id.tvTenTaiKhoanItemTaiKhoan);
            ivXoa = itemView.findViewById(R.id.ivXoaItemTaiKhoan);


        }
    }

}
