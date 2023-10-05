package congntph34559.fpoly.duanmauapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import congntph34559.fpoly.duanmauapplication.DBHelper.MyDBHelper;
import congntph34559.fpoly.duanmauapplication.DTO.SachDTO;

public class SachDAO {

    MyDBHelper myDBHelper;
    SQLiteDatabase db;

    public SachDAO (Context context){

        myDBHelper = new MyDBHelper(context);
        db = myDBHelper.getWritableDatabase();


    }

    public long addRow(SachDTO objSach){

        ContentValues values = new ContentValues();
        values.put("ten_sach",objSach.getTenSach());
        values.put("gia_tien",objSach.getGiaTien());
        values.put("ma_loai_sach",objSach.getIdLoaiSach());

        return db.insert("tb_sach",null,values);


    }

    public int updateRow(SachDTO objSach){

        ContentValues values = new ContentValues();
        values.put("ten_sach",objSach.getTenSach());
        values.put("gia_tien",objSach.getGiaTien());
        values.put("ma_loai_sach",objSach.getIdLoaiSach());


        return db.update("tb_sach",values,"id=?",new String[]{objSach.getId()+""});


    }

    public int deleteRow(SachDTO objSach){

        return db.delete("tb_sach","id=?",new String[]{objSach.getId()+""});

    }

    public List<SachDTO> getAll(){

        List<SachDTO> list = new ArrayList<>();
        Cursor  cursor = db.rawQuery("SELECT tb_sach.id,ten_sach,gia_tien,ten_loai_sach\n" +
                "FROM tb_sach INNER JOIN tb_loai_sach ON tb_sach.ma_loai_sach = tb_loai_sach.id;",null);

        if (cursor != null && cursor.getCount()>0){

            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                int id = cursor.getInt(0);
                String tenSach = cursor.getString(1);
                int giaTien = cursor.getInt(2);
                String tenLoaiSach  = cursor.getString(3);

                SachDTO objSach = new SachDTO();
                objSach.setId(id);
                objSach.setTenSach(tenSach);
                objSach.setGiaTien(giaTien);
                objSach.setTenLoaiSach(tenLoaiSach);

                list.add(objSach);

                cursor.moveToNext();

            }


        }





        return list;

    }




}
