package com.dev4fun.dao;

import com.dev4fun.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO extends DAO {
    public Role getRoleById(int id) {
        try (Connection conn = getConnection()) {
            String statement = "select * from role where id = ?";
            Role role = null;
            PreparedStatement ppStmt = conn.prepareStatement(statement);
            ppStmt.setInt(1, id);
            ResultSet rs = ppStmt.executeQuery();
            while (rs.next()) {
                role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
            }
            return role;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Role> getAllRole() {
        try (Connection conn = getConnection()) {
            String statement = "select * from role";
            ArrayList<Role> categories = new ArrayList<>();
            ResultSet rs = conn.createStatement().executeQuery(statement);
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                categories.add(role);
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createRole(Role role) {
        try (Connection conn = getConnection()) {
            String stmt = "insert into role (name) VALUES (?)";
            PreparedStatement ppStmt = conn.prepareStatement(stmt);
            ppStmt.setString(1, role.getName());
            ppStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public boolean updateRole(Role role) {
        try (Connection conn = getConnection()) {
            String stmt = "update role set name = ?";
            PreparedStatement ppStmt = conn.prepareStatement(stmt);
            ppStmt.setString(1, role.getName());
            ppStmt.executeUpdate();
            return true;
        } catch (SQLException err) {
            throw new RuntimeException();
        }
    }

}
