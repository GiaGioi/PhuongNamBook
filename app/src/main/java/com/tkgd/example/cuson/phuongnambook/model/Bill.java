package com.tkgd.example.cuson.phuongnambook.model;


import java.util.Date;

public class Bill {
    private String idBill;
    private Date DOP;

    public Bill(){

    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public Date getDOP() {
        return DOP;
    }

    public void setDOP(Date DOP) {
        this.DOP = DOP;
    }

    public Bill(String idBill, Date DOP) {

        this.idBill = idBill;
        this.DOP = DOP;
    }
}
