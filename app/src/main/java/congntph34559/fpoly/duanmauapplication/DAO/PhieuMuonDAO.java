package congntph34559.fpoly.duanmauapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import congntph34559.fpoly.duanmauapplication.DBHelper.MyDBHelper;
import congntph34559.fpoly.duanmauapplication.DTO.PhieuMuonDTO;

public class PhieuMuonDAO {

    MyDBHelper myDBHelper;
    SQLiteDatabase db;

    public PhieuMuonDAO(Context context){

        myDBHelper = new MyDBHelper(context);
        db = myDBHelper.getWritableDatabase();


    }

    public long addRow(PhieuMuonDTO objPhieuMuon){

        ContentValues values = new ContentValues();
        values.put("ten_thanh_vien",objPhieuMuon.getTenThanhVien());
        values.put("ten_sach",objPhieuMuon.getTenSach());
        values.put("ngay_muon",objPhieuMuon.getNgayMuon());
        values.put("gia_tien",objPhieuMuon.getGiaTien());
        values.put("trang_thai",objPhieuMuon.getTrangThai());

        return db.insert("tb_phieu_muon",null,values);


    }

    public  int updateRow(PhieuMuonDTO objPhieuMuon){

        ContentValues values = new ContentValues();
        values.put("ten_thanh_vien",objPhieuMuon.getTenThanhVien());
        values.put("ten_sach",objPhieuMuon.getTenSach());
        values.put("ngay_muon",objPhieuMuon.getNgayMuon());
        values.put("gia_tien",objPhieuMuon.getGiaTien());
        values.put("trang_thai",objPhieuMuon.getTrangThai());

        return db.update("tb_phieu_muon",values,"id=?",new String[]{objPhieuMuon.getIdPhieuMuon()+""});


    }

    public int deleteRow(PhieuMuonDTO objPhieuMuon){

        return db.delete("tb_phieu_muon","id=?",new String[]{objPhieuMuon.getIdPhieuMuon()+""});

    }

    public List<PhieuMuonDTO> getAll(){

        List<PhieuMuonDTO> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM tb_phieu_muon",null);

        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();

            while (!cursor.isAfterLast()){

                int idPhieuMuon = cursor.getInt(0);
                String tenThanhVien = cursor.getString(1);
                String tenSach = cursor.getString(2);
                String ngayMuon = cursor.getString(3);
                int giaTien = cursor.getInt(4);
                String trangThai = cursor.getString(5);

                PhieuMuonDTO objPhieuMuon = new PhieuMuonDTO();
                objPhieuMuon.setIdPhieuMuon(idPhieuMuon);
                objPhieuMuon.setTenThanhVien(tenThanhVien);
                objPhieuMuon.setTenSach(tenSach);
                objPhieuMuon.setNgayMuon(ngayMuon);
                objPhieuMuon.setGiaTien(giaTien);
                objPhieuMuon.setTrangThai(trangThai);

                list.add(objPhieuMuon);

                cursor.moveToNext();

            }

        }


        return list;

    }



}
