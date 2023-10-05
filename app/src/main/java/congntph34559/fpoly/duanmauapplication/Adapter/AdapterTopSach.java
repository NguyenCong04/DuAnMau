package congntph34559.fpoly.duanmauapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import congntph34559.fpoly.duanmauapplication.DTO.Top10SachMuonDTO;
import congntph34559.fpoly.duanmauapplication.R;

public class AdapterTopSach extends RecyclerView.Adapter<AdapterTopSach.ViewHolder>{

    Context context;
    List<Top10SachMuonDTO> list;

    public AdapterTopSach(Context context, List<Top10SachMuonDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view1 = inflater.inflate(R.layout.item_top_10_sach,parent,false);

        return new ViewHolder(view1);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvSoLuotMuon.setText(list.get(position).getSoLuongMuon()+"");
        holder.tvTenSach.setText(list.get(position).getTenSach());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTenSach,tvSoLuotMuon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSach = itemView.findViewById(R.id.tvTenSachItemTop10);
            tvSoLuotMuon = itemView.findViewById(R.id.tvSoLuotMuon);


        }
    }

}
