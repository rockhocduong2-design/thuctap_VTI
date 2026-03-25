package com.example.view;

import java.util.List;

import com.example.model.Product;

public class ProductView {
    public void showList(List<Product> products) {
        System.out.println("========= DANH SÁCH SẢN PHẨM (LOCAL) =========");
        if (products.isEmpty()) {
            System.out.println("Không có dữ liệu nào trong Database");
        } else {
            for (Product p : products) {
                System.out.println(p);
            }
        }
        System.out.println("===============================================");
    }

}
