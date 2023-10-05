package congntph34559.fpoly.duanmauapplication.DTO;

public class LoaiSachDTO {

    public  int id;
    public String tenLoaiSach;

    public LoaiSachDTO() {
    }

    public LoaiSachDTO(int id, String tenLoaiSach) {
        this.id = id;
        this.tenLoaiSach = tenLoaiSach;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoaiSach() {
        return tenLoaiSach;
    }

    public void setTenLoaiSach(String tenLoaiSach) {
        this.tenLoaiSach = tenLoaiSach;
    }

    public String toString(){

        return this.id + " : "+this.tenLoaiSach;

    }

}
