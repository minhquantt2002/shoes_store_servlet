package com.dev4fun.model;

import java.util.ArrayList;

public class Bill {
    private int id;
    private String status;
    private int userId;
    private String fullName;
    private String email;
    private String address;
    private String phoneNumber;
    private float totalAmount;
    private String payMethod;
    private String note;
    private String createdAt;
    private String invoice_creator;


    private ArrayList<BillDetail> billDetails;

    public Bill() {
        this.billDetails = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public int getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public String getNote() {
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setBillDetails(ArrayList<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }

    public ArrayList<BillDetail> getBillDetails() {
        return billDetails;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInvoice_creator() {
        return invoice_creator;
    }

    public void setInvoice_creator(String invoice_creator) {
        this.invoice_creator = invoice_creator;
    }

}
