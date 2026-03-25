package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.IProductDAO;
import com.example.model.Product;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductDAO productDAO;


    @GetMapping()
    public List<Product> getAllProducts() {
        return productDAO.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            boolean success = productDAO.deleteProduct(id);
            if (success) {
                return ResponseEntity.ok("Xóa thành công ");
            } else {
                return ResponseEntity.status(404).body("Không tìm thấy sản phẩm để xóa!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi Database: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody Product p) {
        if (productDAO.addProduct(p)) {
            return ResponseEntity.ok("Thêm thành công");
        }
        return ResponseEntity.status(500).body("Thêm thất bại");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getOne(@PathVariable int id) {
        return ResponseEntity.ok(productDAO.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Product p) {
        p.setProductID(id);
        if (productDAO.updateProduct(p)) {
            return ResponseEntity.ok("Cập nhật thành công!");
        }
        return ResponseEntity.status(500).body("Lỗi cập nhật!");
    }

}
