package congntph34559.fpoly.duanmauapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import congntph34559.fpoly.duanmauapplication.DBHelper.MyDBHelper;
import congntph34559.fpoly.duanmauapplication.DTO.ThuThuDTO;

public class ThuThuDAO {

    MyDBHelper myDBHelper;
    SQLiteDatabase db;

    public ThuThuDAO(Context context){

        myDBHelper = new MyDBHelper(context);
        db = myDBHelper.getWritableDatabase();


    }

    public  long addRow(ThuThuDTO objThuThu){

        ContentValues values = new ContentValues();
        values.put("ho_ten",objThuThu.getHoTen());
        values.put("ten_tai_khoan",objThuThu.getTenTaiKhoan());
        values.put("mat_khau",objThuThu.getMatKhau());
        values.put("nhap_lai_mk",objThuThu.getNhapLaiMatKhau());


        return db.insert("tb_thu_thu",null,values);


    }

    public  int  updateRow(ThuThuDTO objThuThu){

        ContentValues values = new ContentValues();
        values.put("mat_khau",objThuThu.getMatKhau());
        values.put("nhap_lai_mk",objThuThu.getNhapLaiMatKhau());

        return db.update("tb_thu_thu",values,"id=?",new String[]{objThuThu.getIdMaThuThu()+""});


    }

    public  int deleteRow(ThuThuDTO objThuThu){

        return db.delete("tb_thu_thu","id=?",new String[]{objThuThu.getIdMaThuThu()+""});


    }


    public List<ThuThuDTO> getAll(){

        List<ThuThuDTO> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM tb_thu_thu",null);

        if (cursor != null && cursor.getCount() > 0){

            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                int id = cursor.getInt(0);
                String hoTen = cursor.getString(1);
                String tenTaiKhoa = cursor.getString(2);
                String matKhau = cursor.getString(3);
                String nhapLaiMatKhau = cursor.getString(4);

                ThuThuDTO objThuThu = new ThuThuDTO(id,hoTen,tenTaiKhoa,matKhau,nhapLaiMatKhau);
                list.add(objThuThu);

                cursor.moveToNext();

            }


        }


        return list;


    }

    public Boolean checkUesrPass(String taiKhoan,String matKhau){

        Cursor cursor = db.rawQuery("SELECT * FROM tb_thu_thu " +
                                        "WHERE ten_tai_khoan = ? AND mat_khau = ?",
                                        new String[]{taiKhoan,matKhau});

        if (cursor.getCount() > 0){

            return true;

        }else {

            return false;

        }


    }

    public Boolean checkTaiKhoan(String tenTaiKhoan){

        Cursor cursor = db.rawQuery("SELECT * FROM tb_thu_thu WHERE ten_tai_khoan = ?",
                                            new String[]{tenTaiKhoan});

        if (cursor.getCount() > 0){

            return true;

        }else {

            return false;

        }


    }





}
