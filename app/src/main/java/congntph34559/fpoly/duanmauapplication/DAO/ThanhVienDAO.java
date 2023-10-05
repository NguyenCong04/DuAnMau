package congntph34559.fpoly.duanmauapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import congntph34559.fpoly.duanmauapplication.DBHelper.MyDBHelper;
import congntph34559.fpoly.duanmauapplication.DTO.ThanhVienDTO;

public class ThanhVienDAO {

    MyDBHelper myDBHelper;
    SQLiteDatabase db;

    public ThanhVienDAO(Context context){

        myDBHelper = new MyDBHelper(context);
        db = myDBHelper.getWritableDatabase();


    }

    public long addRow(ThanhVienDTO objThanhVien){

        ContentValues values = new ContentValues();
        values.put("ten_thanh_vien",objThanhVien.getTenThanhVien());
        values.put("nam_sinh",objThanhVien.getNamSinh());

        return db.insert("tb_thanh_vien",null,values);

    }

    public  int updateRow(ThanhVienDTO objThanhVien){

        ContentValues values = new ContentValues();
        values.put("ten_thanh_vien",objThanhVien.getTenThanhVien());
        values.put("nam_sinh",objThanhVien.getNamSinh());

        return db.update("tb_thanh_vien",values,"id=?",new String[]{objThanhVien.getId()+""});

    }

    public  int deleteRow(ThanhVienDTO objThanhVien){

        return db.delete("tb_thanh_vien","id=?",new String[]{objThanhVien.getId()+""});

    }

    public List<ThanhVienDTO> getAll(){

        List<ThanhVienDTO> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM tb_thanh_vien",null);

        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();

            while (!cursor.isAfterLast()){

                int id = cursor.getInt(0);
                String tenThanhVien = cursor.getString(1);
                String namSinh = cursor.getString(2);

                ThanhVienDTO objThanhVien = new ThanhVienDTO(id,tenThanhVien,namSinh);
                list.add(objThanhVien);

                cursor.moveToNext();

            }

        }

        return list;

    }





}
