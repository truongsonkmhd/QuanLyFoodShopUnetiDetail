/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.ThuocTinhSanPham;

import java.util.Objects;

/**
 *
 * @author Truong Sơn kmhd
 */
public class SizeSanPhamDTO {
    int idsize;
    String namesize;

    public SizeSanPhamDTO(int idsize, String namesize) {
        this.idsize = idsize;
        this.namesize = namesize;
    }

    public SizeSanPhamDTO() {
    }

    public int getIdsize() {
        return idsize;
    }

    public void setIdsize(int idsize) {
        this.idsize = idsize;
    }

    public String getNamesize() {
        return namesize;
    }

    public void setNamesize(String namesize) {
        this.namesize = namesize;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idsize;
        hash = 97 * hash + Objects.hashCode(this.namesize);
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
        final SizeSanPhamDTO other = (SizeSanPhamDTO) obj;
        if (this.idsize != other.idsize) {
            return false;
        }
        return Objects.equals(this.namesize, other.namesize);
    }

    
 
}
