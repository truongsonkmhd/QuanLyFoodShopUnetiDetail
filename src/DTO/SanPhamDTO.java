
package DTO;

import java.util.Objects;

/**
 *
 * @author truongsonkmhd
 */
public class SanPhamDTO {

    private int masp;
    private String tensp;
    private String hinhanh;
    private int xuatxu;
    private int thuonghieu;
    private int khuvuckho;
    private int soluongton;

    public SanPhamDTO() {
    }

    public SanPhamDTO(int masp, String tensp, String hinhanh, int xuatxu, int thuonghieu, int khuvuckho, int soluongton) {
        this.masp = masp;
        this.tensp = tensp;
        this.hinhanh = hinhanh;
        this.xuatxu = xuatxu;
        this.thuonghieu = thuonghieu;
        this.khuvuckho = khuvuckho;
        this.soluongton = soluongton;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getXuatxu() {
        return xuatxu;
    }

    public void setXuatxu(int xuatxu) {
        this.xuatxu = xuatxu;
    }

    public int getThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(int thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public int getKhuvuckho() {
        return khuvuckho;
    }

    public void setKhuvuckho(int khuvuckho) {
        this.khuvuckho = khuvuckho;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.masp;
        hash = 29 * hash + Objects.hashCode(this.tensp);
        hash = 29 * hash + Objects.hashCode(this.hinhanh);
        hash = 29 * hash + this.xuatxu;
        hash = 29 * hash + this.thuonghieu;
        hash = 29 * hash + this.khuvuckho;
        hash = 29 * hash + this.soluongton;
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
        final SanPhamDTO other = (SanPhamDTO) obj;
        if (this.masp != other.masp) {
            return false;
        }
        if (this.xuatxu != other.xuatxu) {
            return false;
        }
        if (this.thuonghieu != other.thuonghieu) {
            return false;
        }
        if (this.khuvuckho != other.khuvuckho) {
            return false;
        }
        if (this.soluongton != other.soluongton) {
            return false;
        }
        if (!Objects.equals(this.tensp, other.tensp)) {
            return false;
        }
        return Objects.equals(this.hinhanh, other.hinhanh);
    }

   
}
