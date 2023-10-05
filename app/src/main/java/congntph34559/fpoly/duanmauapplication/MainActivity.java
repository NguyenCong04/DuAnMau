package congntph34559.fpoly.duanmauapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import congntph34559.fpoly.duanmauapplication.DAO.ThuThuDAO;
import congntph34559.fpoly.duanmauapplication.DTO.ThuThuDTO;
import congntph34559.fpoly.duanmauapplication.Fragement.FragDoanhThu;
import congntph34559.fpoly.duanmauapplication.Fragement.FragDoiMatKhau;
import congntph34559.fpoly.duanmauapplication.Fragement.FragLoaiSach;
import congntph34559.fpoly.duanmauapplication.Fragement.FragPhieuMuon;
import congntph34559.fpoly.duanmauapplication.Fragement.FragSach;
import congntph34559.fpoly.duanmauapplication.Fragement.FragThanhVien;
import congntph34559.fpoly.duanmauapplication.Fragement.FragThemNguoiDung;
import congntph34559.fpoly.duanmauapplication.Fragement.FragTop10Sach;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    FragmentManager manager;

    View headerView;

    TextView tvNameUser;
    List<ThuThuDTO> list;
    ThuThuDAO thuThuDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);
        toolbar = findViewById(R.id.myToolbar);
        thuThuDAO = new ThuThuDAO(this);
        list = thuThuDAO.getAll();

        headerView = navigationView.getHeaderView(0);
        tvNameUser = headerView.findViewById(R.id.tvNameWelcome);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");

        String fullName = "";

        for (int i = 0; i < list.size(); i++) {

            if (user.equals(list.get(i).getTenTaiKhoan())){

                fullName = list.get(i).getHoTen();

            }else {

                fullName = user;

            }

        }

        tvNameUser.setText(fullName);

        //Phân quyền
        SharedPreferences sharedPreferences = getSharedPreferences("FILE_USER",MODE_PRIVATE);
        String tenTaiKhoan = sharedPreferences.getString("USERNAME","");

        if (tenTaiKhoan.equals("admin")){

            navigationView.getMenu().findItem(R.id.itemThemNguoiDung).setVisible(true);

        }else {

            navigationView.getMenu().findItem(R.id.itemThemNguoiDung).setVisible(false);

        }


        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.getDrawerArrowDrawable();
        toggle.syncState();
//        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white,getTheme()));
        manager = getSupportFragmentManager();

        manager.beginTransaction().replace(R.id.fragmentContainer,new FragPhieuMuon()).commit();
        getSupportActionBar().setTitle("Quản lý phiếu mượn");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.itemPhieuMuon){

                    manager.beginTransaction().replace(R.id.fragmentContainer,new FragPhieuMuon()).commit();
                    getSupportActionBar().setTitle("Quản lý phiếu mượn");


                } else if (item.getItemId() == R.id.itemLoaiSach) {

                    manager.beginTransaction().replace(R.id.fragmentContainer,new FragLoaiSach()).commit();


                }else if (item.getItemId() == R.id.itemSach){

                    manager.beginTransaction().replace(R.id.fragmentContainer,new FragSach()).commit();

                } else if (item.getItemId() == R.id.itemThanhVien) {

                    manager.beginTransaction().replace(R.id.fragmentContainer,new FragThanhVien()).commit();

                } else if (item.getItemId() == R.id.itemTop10SachMuonNhieuNhat) {

                    manager.beginTransaction().replace(R.id.fragmentContainer,new FragTop10Sach()).commit();


                } else if (item.getItemId() == R.id.itemDoanhThu) {

                    manager.beginTransaction().replace(R.id.fragmentContainer,new FragDoanhThu()).commit();


                } else if (item.getItemId() == R.id.itemDoiMatKhau) {

                    manager.beginTransaction().replace(R.id.fragmentContainer,new FragDoiMatKhau()).commit();

                }else if (item.getItemId() == R.id.itemDangXuat){

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Thông báo !")
                            .setMessage("Bạn có chắc muốn đăng xuất không ?")
                            .setCancelable(true)
                            .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                   startActivity(new Intent(MainActivity.this,LoginActivity.class));
                                   finish();

                                }
                            })
                            .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();

                } else if (item.getItemId() == R.id.itemThemNguoiDung) {

                    manager.beginTransaction().replace(R.id.fragmentContainer,new FragThemNguoiDung()).commit();

                }

                getSupportActionBar().setTitle(item.getTitle());
                drawerLayout.close();

                return true;
            }
        });



    }

    @Override
    public void onBackPressed() {

        int index = 0;
        index++;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        int finalIndex = index;
        builder.setTitle("Thông báo !")
                .setMessage("Bạn có chắc muốn thoát không ?")
                .setCancelable(true)
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (finalIndex == 1){

                            MainActivity.super.onBackPressed();

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
}