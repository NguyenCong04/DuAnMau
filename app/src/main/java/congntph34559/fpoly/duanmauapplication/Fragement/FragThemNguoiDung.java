package congntph34559.fpoly.duanmauapplication.Fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import congntph34559.fpoly.duanmauapplication.Adapter.AdapterThemNguoiDung;
import congntph34559.fpoly.duanmauapplication.R;

public class FragThemNguoiDung extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    AdapterThemNguoiDung adapterThemNguoiDung;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_them_nguoi_dung,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        tabLayout = view.findViewById(R.id.myTabLayout);
        viewPager2 = view.findViewById(R.id.viewPager2);
        adapterThemNguoiDung = new AdapterThemNguoiDung(this);
        viewPager2.setAdapter(adapterThemNguoiDung);

        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position){

                    case 0:
                        tab.setText("Đăng ký");
                        break;
                    case 1:
                        tab.setText("Danh sách");
                        break;


                }


            }
        });
        mediator.attach();



        super.onViewCreated(view, savedInstanceState);
    }
}
