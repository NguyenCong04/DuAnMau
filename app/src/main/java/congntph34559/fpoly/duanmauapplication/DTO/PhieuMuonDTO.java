package congntph34559.fpoly.duanmauapplication.DTO;

public class PhieuMuonDTO {

    public int idPhieuMuon;
    public int idSach;
    public  int idThanhVien;
    public String ngayMuon;
    public String trangThai;
    public String tenSach;
    public String tenThanhVien;
    public int giaTien;

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public PhieuMuonDTO() {
    }

    public PhieuMuonDTO(int idPhieuMuon, int idSach, int idThanhVien, String ngayMuon, String trangThai) {
        this.idPhieuMuon = idPhieuMuon;
        this.idSach = idSach;
        this.idThanhVien = idThanhVien;
        this.ngayMuon = ngayMuon;
        this.trangThai = trangThai;
    }

    public int getIdPhieuMuon() {
        return idPhieuMuon;
    }

    public void setIdPhieuMuon(int idPhieuMuon) {
        this.idPhieuMuon = idPhieuMuon;
    }

    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    public int getIdThanhVien() {
        return idThanhVien;
    }

    public void setIdThanhVien(int idThanhVien) {
        this.idThanhVien = idThanhVien;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTenThanhVien() {
        return tenThanhVien;
    }

    public void setTenThanhVien(String tenThanhVien) {
        this.tenThanhVien = tenThanhVien;
    }
}
