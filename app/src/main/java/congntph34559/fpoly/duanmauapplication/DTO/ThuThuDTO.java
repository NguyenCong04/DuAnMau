package congntph34559.fpoly.duanmauapplication.DTO;

public class ThuThuDTO {

    public int  idMaThuThu;

    public String hoTen;
    public String tenTaiKhoan;
    public String matKhau;
    public String nhapLaiMatKhau;

    public ThuThuDTO() {
    }

    public ThuThuDTO(int idMaThuThu, String hoTen, String tenTaiKhoan, String matKhau, String nhapLaiMatKhau) {
        this.idMaThuThu = idMaThuThu;
        this.hoTen = hoTen;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.nhapLaiMatKhau = nhapLaiMatKhau;
    }

    public int getIdMaThuThu() {
        return idMaThuThu;
    }

    public void setIdMaThuThu(int idMaThuThu) {
        this.idMaThuThu = idMaThuThu;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getNhapLaiMatKhau() {
        return nhapLaiMatKhau;
    }

    public void setNhapLaiMatKhau(String nhapLaiMatKhau) {
        this.nhapLaiMatKhau = nhapLaiMatKhau;
    }
}
