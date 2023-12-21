package com.henry.pijava.dto;


import java.time.LocalDate;

public class ExpenseDTO  {
    private int id;
    private int categoryId;
    private double amount;
    private String description;
    private LocalDate date;



    // Constructor, getters y setters

    public ExpenseDTO(double amount, String description, int categoryId) {

        this.amount = amount;
        this.description = description;
        this.categoryId = categoryId;
//        this.date = LocalDate.now();
    }

    public ExpenseDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getAmount() {

        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}