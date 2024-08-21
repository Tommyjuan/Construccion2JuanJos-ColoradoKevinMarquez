/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

import java.util.Date;

/**
 *
 * @author yean papi
 */
public class Invoice {

    private long Id;
    private Person Person_id;
    private Partner partner_id;
    private Date Creacion_date;
    private double amount;
    private boolean status;

 

    public Invoice() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public Person getPerson_id() {
        return Person_id;
    }

    public void setPerson_id(Person Person_id) {
        this.Person_id = Person_id;
    }

    public Partner getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(Partner partner_id) {
        this.partner_id = partner_id;
    }

    public Date getCreacion_date() {
        return Creacion_date;
    }

    public void setCreacion_date(Date Creacion_date) {
        this.Creacion_date = Creacion_date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
