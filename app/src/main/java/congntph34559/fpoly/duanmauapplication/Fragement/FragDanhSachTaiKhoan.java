package congntph34559.fpoly.duanmauapplication.Fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import congntph34559.fpoly.duanmauapplication.Adapter.AdapterDanhSachTaiKhoan;
import congntph34559.fpoly.duanmauapplication.DAO.ThuThuDAO;
import congntph34559.fpoly.duanmauapplication.DTO.ThuThuDTO;
import congntph34559.fpoly.duanmauapplication.R;

public class FragDanhSachTaiKhoan extends Fragment {


    RecyclerView recyclerView;
    ThuThuDAO thuThuDAO;
    List<ThuThuDTO> list;
    AdapterDanhSachTaiKhoan adapterDanhSachTaiKhoan;
    LinearLayoutManager manager;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_danh_sach_tai_khoan,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerView = view.findViewById(R.id.rcvDanhSachTaiKhoan);
        thuThuDAO = new ThuThuDAO(getContext());
        list = thuThuDAO.getAll();
        adapterDanhSachTaiKhoan = new AdapterDanhSachTaiKhoan(getContext(),list);
        adapterDanhSachTaiKhoan.notifyDataSetChanged();
        Toast.makeText(getContext(), ""+list.size(), Toast.LENGTH_SHORT).show();
        manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterDanhSachTaiKhoan);


        super.onViewCreated(view, savedInstanceState);
    }
}
