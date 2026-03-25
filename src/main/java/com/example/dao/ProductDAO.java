package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.model.Product;

@Repository
public class ProductDAO implements IProductDAO {

    private Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/project_2";
        String user = "root";
        String pass = "123456";

        return DriverManager.getConnection(url, user, pass);
    }

    @Override
    public List<Product> getAll() {

        List<Product> list = new ArrayList<>();

        try (Connection conn = getConnection()) {

            String sql = "SELECT * FROM product ORDER BY `order` ASC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("productID"));
                p.setName(rs.getString("Name"));
                p.setOrder(rs.getInt("order"));
                p.setIsActive(rs.getBoolean("isactive"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM product WHERE productID = ?";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowAffected = pstmt.executeUpdate();
            return rowAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean addProduct(Product p) {
        String sql = "INSERT INTO product (Name, `order`, isactive) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setInt(2, p.getOrder());
            ps.setBoolean(3, p.getIsActive());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product getProductById(int id) {
        String sql = "SELECT * FROM product WHERE productID = ?";
        try (Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("productID"));
                p.setName(rs.getString("Name"));
                p.setOrder(rs.getInt("order"));
                p.setIsActive(rs.getBoolean("isactive"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateProduct(Product p) {
        String sql = "UPDATE product SET Name = ?, `order` = ?, isactive = ? WHERE productID = ?";
        try (Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setInt(2, p.getOrder());
            ps.setBoolean(3, p.getIsActive());
            ps.setInt(4, p.getProductID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
