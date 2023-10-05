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

import congntph34559.fpoly.duanmauapplication.Adapter.AdapterTopSach;
import congntph34559.fpoly.duanmauapplication.DAO.ThongKeDAO;
import congntph34559.fpoly.duanmauapplication.DTO.Top10SachMuonDTO;
import congntph34559.fpoly.duanmauapplication.R;

public class FragTop10Sach extends Fragment {

    RecyclerView recyclerView;
    List<Top10SachMuonDTO> list;
    ThongKeDAO thongKeDAO;
    LinearLayoutManager manager;
    AdapterTopSach adapterTopSach;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_top_10_sach,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerView = view.findViewById(R.id.rcvTop10Sach);
        thongKeDAO = new ThongKeDAO(getContext());
        list = thongKeDAO.getTop10();
        adapterTopSach = new AdapterTopSach(getContext(),list);
        manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterTopSach);




        super.onViewCreated(view, savedInstanceState);
    }
}
