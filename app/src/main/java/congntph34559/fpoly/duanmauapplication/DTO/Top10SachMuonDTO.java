package congntph34559.fpoly.duanmauapplication.DTO;

public class Top10SachMuonDTO {

    public String tenSach;
    public  int soLuongMuon;

    public Top10SachMuonDTO() {
    }

    public Top10SachMuonDTO(String tenSach, int soLuongMuon) {
        this.tenSach = tenSach;
        this.soLuongMuon = soLuongMuon;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoLuongMuon() {
        return soLuongMuon;
    }

    public void setSoLuongMuon(int soLuongMuon) {
        this.soLuongMuon = soLuongMuon;
    }
}
