package com.example.registersignin;

public class StoreData {
    private String category;
    private int amount;
    private String date;
    public StoreData() {

    }

    public StoreData(String category, int amount, String date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}
