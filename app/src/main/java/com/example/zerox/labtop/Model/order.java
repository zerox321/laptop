package com.example.zerox.labtop.Model;

/**
 * Created by Zerox on 11/21/2016.
 */

public class order {
    public String customerName;
    public String customerPhone;
    public String laptopTitle;
    public String laptopPrice;

    public order(String customerName, String customerPhone, String laptopTitle, String laptopPrice) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.laptopTitle = laptopTitle;
        this.laptopPrice = laptopPrice;
    }
}
