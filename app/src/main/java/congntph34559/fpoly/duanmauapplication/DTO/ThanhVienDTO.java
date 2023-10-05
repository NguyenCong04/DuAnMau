package congntph34559.fpoly.duanmauapplication.DTO;

public class ThanhVienDTO {

    public  int id;
    public String tenThanhVien;
    public String namSinh;

    public ThanhVienDTO() {
    }

    public ThanhVienDTO(int id, String tenThanhVien, String namSinh) {
        this.id = id;
        this.tenThanhVien = tenThanhVien;
        this.namSinh = namSinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenThanhVien() {
        return tenThanhVien;
    }

    public void setTenThanhVien(String tenThanhVien) {
        this.tenThanhVien = tenThanhVien;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String toString(){

        return this.id+" : "+this.tenThanhVien;

    }


}
