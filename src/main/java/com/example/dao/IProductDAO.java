package com.example.dao;

import java.sql.SQLException;
import java.util.List;

import com.example.model.Product;

public interface IProductDAO {
    List<Product> getAll();

    Product getProductById(int id);

    boolean addProduct(Product p);

    boolean updateProduct(Product p);

    boolean deleteProduct(int id) throws SQLException;

}
