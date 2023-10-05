package congntph34559.fpoly.duanmauapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import congntph34559.fpoly.duanmauapplication.Fragement.FragDangKyTaiKhoan;
import congntph34559.fpoly.duanmauapplication.Fragement.FragDanhSachTaiKhoan;

public class AdapterThemNguoiDung extends FragmentStateAdapter {


    public AdapterThemNguoiDung(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new FragDangKyTaiKhoan();
            case 1:
                return new FragDanhSachTaiKhoan();
            default:
                return new FragDangKyTaiKhoan();


        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
