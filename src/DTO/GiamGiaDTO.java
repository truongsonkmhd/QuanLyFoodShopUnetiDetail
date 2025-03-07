/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author Truong Sơn kmhd
 */
public class GiamGiaDTO {
    int idgiamgia;
    String giamgia;

    public GiamGiaDTO() {
    }

    public GiamGiaDTO(int idgiamgia, String giamgia) {
        this.idgiamgia = idgiamgia;
        this.giamgia = giamgia;
    }

    public int getIdgiamgia() {
        return idgiamgia;
    }

    public void setIdgiamgia(int idgiamgia) {
        this.idgiamgia = idgiamgia;
    }

    public String getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(String giamgia) {
        this.giamgia = giamgia;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.idgiamgia;
        hash = 41 * hash + Objects.hashCode(this.giamgia);
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
        final GiamGiaDTO other = (GiamGiaDTO) obj;
        if (this.idgiamgia != other.idgiamgia) {
            return false;
        }
        return Objects.equals(this.giamgia, other.giamgia);
    }

   
}
