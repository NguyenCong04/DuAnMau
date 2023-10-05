package congntph34559.fpoly.duanmauapplication.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "QuanLyThuVien1";
    public static final int DB_VERSION = 2;

    public MyDBHelper(Context context){

        super(context,DB_NAME,null,DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql_thanhVien = "CREATE TABLE tb_thanh_vien (\n" +
                "    id             INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    ten_thanh_vien TEXT,\n" +
                "    nam_sinh       INTEGER\n" +
                ");\n";
        db.execSQL(sql_thanhVien);


        String sql_sach = "CREATE TABLE tb_sach (\n" +
                "    id           INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    ten_sach     TEXT,\n" +
                "    gia_tien     INTEGER,\n" +
                "    ma_loai_sach INTEGER REFERENCES tb_loai_sach (id) \n" +
                ");\n";
        db.execSQL(sql_sach);

        String sql_loaiSach = "CREATE TABLE tb_loai_sach (\n" +
                "    id            INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    ten_loai_sach TEXT\n" +
                ");\n";
        db.execSQL(sql_loaiSach);

        String sql_phieuMuon = "CREATE TABLE tb_phieu_muon (\n" +
                "    id             INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    ten_thanh_vien TEXT    REFERENCES tb_thanh_vien (ten_thanh_vien),\n" +
                "    ten_sach       TEXT    REFERENCES tb_sach (ten_sach),\n" +
                "    ngay_muon      TEXT,\n" +
                "    gia_tien       INTEGER REFERENCES tb_sach (gia_tien),\n" +
                "    trang_thai     TEXT\n" +
                ");\n";
        db.execSQL(sql_phieuMuon);
        
        String sql_thuThu = "CREATE TABLE tb_thu_thu (\n" +
                "    id            INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    ho_ten        TEXT,\n" +
                "    ten_tai_khoan TEXT,\n" +
                "    mat_khau      TEXT,\n" +
                "    nhap_lai_mk   TEXT\n" +
                ");\n";
        db.execSQL(sql_thuThu);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE tb_thanh_vien");
        db.execSQL("DROP TABLE tb_sach");
        db.execSQL("DROP TABLE tb_loai_sach");
        db.execSQL("DROP TABLE tb_phieu_muon");
        db.execSQL("DROP TABLE tb_thu_thu");

        onCreate(db);

    }
}
