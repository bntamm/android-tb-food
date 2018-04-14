package com.example.tam.tb_food.model;

/**
 * Created by Tam on 4/10/2018.
 */

public class Giohang {
    public int Idsp;
    public String Tensp;
    public long Giasp;
    public String Hinhsp;
    public int Soluongsp;


    public Giohang(int idsp, String tensp, long giasp, String hinhsp, int soluongsp) {
        Idsp = idsp;
        Tensp = tensp;
        Giasp = giasp;
        Hinhsp = hinhsp;
        Soluongsp = soluongsp;
    }

    public int getIdsp() {
        return Idsp;
    }

    public void setIdsp(int idsp) {
        Idsp = idsp;
    }

    public String getTensp() {
        return Tensp;
    }

    public void setTensp(String tensp) {
        Tensp = tensp;
    }

    public long getGiasp() {
        return Giasp;
    }

    public void setGiasp(long giasp) {
        Giasp = giasp;
    }

    public String getHinhsp() {
        return Hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        Hinhsp = hinhsp;
    }

    public int getSoluongsp() {
        return Soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        Soluongsp = soluongsp;
    }
}
