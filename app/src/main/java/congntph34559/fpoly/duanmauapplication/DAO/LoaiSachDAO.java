package congntph34559.fpoly.duanmauapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import congntph34559.fpoly.duanmauapplication.DBHelper.MyDBHelper;
import congntph34559.fpoly.duanmauapplication.DTO.LoaiSachDTO;

public class LoaiSachDAO {

    MyDBHelper myDBHelper;
    SQLiteDatabase db;

    public LoaiSachDAO(Context context){

        myDBHelper = new MyDBHelper(context);
        db = myDBHelper.getWritableDatabase();

    }

    public long addRow(LoaiSachDTO objLoaiSach){

        ContentValues values = new ContentValues();
        values.put("ten_loai_sach",objLoaiSach.getTenLoaiSach());

        return db.insert("tb_loai_sach",null,values);

    }

    public int updateRow(LoaiSachDTO objLoaiSach){

        ContentValues values = new ContentValues();
        values.put("ten_loai_sach",objLoaiSach.getTenLoaiSach());

        return db.update("tb_loai_sach",values,"id=?",new String[]{objLoaiSach.getId()+""});


    }

    public int deleteRow(LoaiSachDTO objLoaiSach){


        return db.delete("tb_loai_sach","id=?",new String[]{objLoaiSach.getId()+""});


    }

    public List<LoaiSachDTO> getAll(){

        List<LoaiSachDTO> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM tb_loai_sach",null);

        if (cursor != null && cursor.getCount() > 0){

            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                int id = cursor.getInt(0);
                String tenLoai = cursor.getString(1);

                LoaiSachDTO objLoaiSach = new LoaiSachDTO(id,tenLoai);
                list.add(objLoaiSach);

                cursor.moveToNext();

            }
        }


        return list;

    }






}
