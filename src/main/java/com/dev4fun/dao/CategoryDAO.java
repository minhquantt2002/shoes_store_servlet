package com.dev4fun.dao;

import com.dev4fun.model.Category;
import com.dev4fun.model.Product;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends DAO {
    public Category getCategoryById(int id) {
        try (Connection conn = getConnection()) {
            String statement = "select * from category where id = ?";
            Category category = null;
            PreparedStatement ppStmt = conn.prepareStatement(statement);
            ppStmt.setInt(1, id);
            ResultSet rs = ppStmt.executeQuery();
            while (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
            }
            return category;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Category> getListCategories() {
        try (Connection conn = getConnection()) {
            String statement = "select * from category";
            PreparedStatement ppStmt = conn.prepareStatement(statement);
            ResultSet rs = ppStmt.executeQuery();
            ArrayList<Category> categories = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));

                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Category> getCategoriesWithProducts() {
        try (Connection conn = getConnection()) {
            String stmtCategory = "select * from category";
            ArrayList<Category> categories = new ArrayList<>();
            ResultSet rs = conn.createStatement().executeQuery(stmtCategory);
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setIcon(rs.getString("icon"));

                String stmtProduct = "select * from product where category_id = ? limit 5";
                PreparedStatement ppProduct = conn.prepareStatement(stmtProduct);
                ppProduct.setInt(1, category.getId());
                ResultSet rsProduct = ppProduct.executeQuery();
                ArrayList<Product> listProducts = new ArrayList<>();
                while (rsProduct.next()) {
                    Product product = new Product();
                    product.setId(rsProduct.getInt("id"));
                    product.setName(rsProduct.getString("name"));
                    product.setDescription(rsProduct.getString("description"));
                    product.setImageLink(rsProduct.getString("image_Link"));
                    product.setImageList(rsProduct.getString("image_List"));
                    product.setPrice(rsProduct.getFloat("price"));
                    listProducts.add(product);
                }
                category.setListProducts(listProducts);
                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createCategory(Category category) {
        try (Connection conn = getConnection()) {
            String stmt = "insert into category (name) VALUES (?)";
            PreparedStatement ppStmt = conn.prepareStatement(stmt);
            ppStmt.setString(1, category.getName());
            ppStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
