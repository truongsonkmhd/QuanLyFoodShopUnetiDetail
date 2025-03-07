
package DTO;


/**
 *
 * @author Pham truong son
 */
public class PhienBanSanPhamDTO {
    private int maphienbansp;
    private int masp;
    private int gianhap;
    private int giaxuat;
    private int soluongton;

    public PhienBanSanPhamDTO() {
    }

    public PhienBanSanPhamDTO(int maphienbansp, int masp, int gianhap, int giaxuat, int soluongton) {
        this.maphienbansp = maphienbansp;
        this.masp = masp;
        this.gianhap = gianhap;
        this.giaxuat = giaxuat;
        this.soluongton = soluongton;
    }

    public PhienBanSanPhamDTO(int maphienbansp, int masp, int gianhap, int giaxuat) {
        this.maphienbansp = maphienbansp;
        this.masp = masp;
        this.gianhap = gianhap;
        this.giaxuat = giaxuat;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.maphienbansp;
        hash = 67 * hash + this.masp;
        hash = 67 * hash + this.gianhap;
        hash = 67 * hash + this.giaxuat;
        hash = 67 * hash + this.soluongton;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PhienBanSanPhamDTO other = (PhienBanSanPhamDTO) obj;
        if (this.maphienbansp != other.maphienbansp) {
            return false;
        }
        if (this.masp != other.masp) {
            return false;
        }
        if (this.gianhap != other.gianhap) {
            return false;
        }
        if (this.giaxuat != other.giaxuat) {
            return false;
        }
        return this.soluongton == other.soluongton;
    }

    public int getMaphienbansp() {
        return maphienbansp;
    }

    public void setMaphienbansp(int maphienbansp) {
        this.maphienbansp = maphienbansp;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getGianhap() {
        return gianhap;
    }

    public void setGianhap(int gianhap) {
        this.gianhap = gianhap;
    }

    public int getGiaxuat() {
        return giaxuat;
    }

    public void setGiaxuat(int giaxuat) {
        this.giaxuat = giaxuat;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

   
}
