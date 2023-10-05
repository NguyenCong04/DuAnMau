package congntph34559.fpoly.duanmauapplication.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import congntph34559.fpoly.duanmauapplication.DBHelper.MyDBHelper;
import congntph34559.fpoly.duanmauapplication.DTO.Top10SachMuonDTO;

public class ThongKeDAO {

    MyDBHelper myDBHelper;
    SQLiteDatabase db;

    public ThongKeDAO(Context context) {

        myDBHelper = new MyDBHelper(context);
        db = myDBHelper.getWritableDatabase();

    }

    public List<Top10SachMuonDTO> getTop10(){

        List<Top10SachMuonDTO> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT tb_phieu_muon.ten_sach,count(tb_phieu_muon.ten_sach)\n" +
                "FROM tb_phieu_muon\n" +
                "GROUP BY tb_phieu_muon.ten_sach\n" +
                "ORDER BY count(tb_phieu_muon.ten_sach) DESC LIMIT 10;",null);

        if (cursor!= null && cursor.getCount() > 0){

            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                String tenSach = cursor.getString(0);
                int soLuotMuon = cursor.getInt(1);

                Top10SachMuonDTO objTop10 = new Top10SachMuonDTO(tenSach,soLuotMuon);
                list.add(objTop10);

                cursor.moveToNext();

            }


        }


        return list;


    }
    public int getDoanhThu(String ngayBatDau,String ngayKetThuc){

        Cursor cursor = db.rawQuery("SELECT SUM(gia_tien) \n" +
                "FROM tb_phieu_muon\n" +
                "WHERE ngay_muon BETWEEN ? AND ?;",new String[]{ngayBatDau,ngayKetThuc});
        List<Integer> list = new ArrayList<>();

        if (cursor != null && cursor.getCount() > 0){

            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                try {

                    int doanhThu = cursor.getInt(0);
                    list.add(doanhThu);

                }catch (Exception e){

                    list.get(0);

                }

                cursor.moveToNext();

            }
        }

        return list.get(0);

    }

}
