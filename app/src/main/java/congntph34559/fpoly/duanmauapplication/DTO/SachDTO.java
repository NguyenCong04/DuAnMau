package congntph34559.fpoly.duanmauapplication.DTO;

public class SachDTO {

    public  int id;
    public String tenSach;
    public int giaTien;
    public int idLoaiSach;
    public String tenLoaiSach;


    public SachDTO() {
    }

    public SachDTO(int id, String tenSach, int giaTien, int idLoaiSach) {
        this.id = id;
        this.tenSach = tenSach;
        this.giaTien = giaTien;
        this.idLoaiSach = idLoaiSach;
    }

    public String getTenLoaiSach() {
        return tenLoaiSach;
    }

    public void setTenLoaiSach(String tenLoaiSach) {
        this.tenLoaiSach = tenLoaiSach;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public int getIdLoaiSach() {
        return idLoaiSach;
    }

    public void setIdLoaiSach(int idLoaiSach) {
        this.idLoaiSach = idLoaiSach;
    }

    public String toString(){

        return this.id+" : "+this.tenSach;

    }
}
