package com.dev4fun.dao;

import com.dev4fun.model.Account;


import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class AccountDAO extends DAO {
    public Account getAccountByUsername(String username) throws ParseException {
        try (Connection conn = getConnection()) {
            String statement = "select * from account where username = ?";
            PreparedStatement ppStmt = conn.prepareStatement(statement);
            ppStmt.setString(1, username);
            ResultSet rs = ppStmt.executeQuery();

            Account account = null;
            while (rs.next()) {
                account = new Account();
                account.setId(rs.getInt("id"));
                account.setUsername(rs.getString("username"));
                account.setEmail(rs.getString("email"));
                account.setPassword(rs.getString("password"));
                account.setRole(rs.getString("role"));
                account.setImageLink(rs.getString("image_link"));
                account.setFullName(rs.getString("full_name"));
                account.setDob((rs.getString("dob")));
                account.setGender((rs.getString("gender")));
                account.setPhoneNumber(rs.getString("phone_number"));
                account.setAddress(rs.getString("address"));
            }
            return account;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Account getAccountById(int id) {
        try (Connection conn = getConnection()) {
            String statement = "select * from account where id = ?";
            PreparedStatement ppStmt = conn.prepareStatement(statement);
            ppStmt.setInt(1, id);
            ResultSet rs = ppStmt.executeQuery();

            Account account = null;
            while (rs.next()) {
                account = new Account();
                account.setId(rs.getInt("id"));
                account.setUsername(rs.getString("username"));
                account.setEmail(rs.getString("email"));
                account.setPassword(rs.getString("password"));
                account.setRole(rs.getString("role"));
                account.setImageLink(rs.getString("image_link"));
                account.setFullName(rs.getString("full_name"));
                account.setDob(rs.getString("dob"));
                account.setGender((rs.getString("gender")));
                account.setPhoneNumber(rs.getString("phone_number"));
                account.setAddress(rs.getString("address"));
            }
            return account;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Account> getAccountByElement(String temp, String value) {
        try (Connection conn = getConnection()) {
            String statement = "select * from account where " + temp + " like '%" + value + "%'";
            PreparedStatement ppStmt = conn.prepareStatement(statement);
//            ppStmt.setString(1,value);
            ResultSet rs = ppStmt.executeQuery();

            ArrayList<Account> accounts = new ArrayList<>();
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setUsername(rs.getString("username"));
                account.setEmail(rs.getString("email"));
//                account.setPassword(rs.getString("password"));
                account.setRole(rs.getString("role"));
                account.setImageLink(rs.getString("image_link"));
                account.setFullName(rs.getString("full_name"));
                account.setDob(rs.getString("dob"));
                account.setGender((rs.getString("gender")));
                account.setPhoneNumber(rs.getString("phone_number"));
                account.setAddress(rs.getString("address"));
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Account> getAllAccount() {
        try (Connection conn = getConnection()) {
            String statement = "select * from account";
            ArrayList<Account> accounts = new ArrayList<>();
            ResultSet rs = conn.createStatement().executeQuery(statement);
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setUsername(rs.getString("username"));
                account.setEmail(rs.getString("email"));
//                account.setPassword(rs.getString("password"));
                account.setRole(rs.getString("role"));
                account.setFullName(rs.getString("full_name"));
                account.setImageLink(rs.getString("image_link"));
                account.setDob(rs.getString("dob"));
                account.setGender((rs.getString("gender")));
                account.setPhoneNumber(rs.getString("phone_number"));
                account.setAddress(rs.getString("address"));
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getTotalAccounts() {
        try (Connection conn = getConnection()) {
            String statement = "select count(*) from account";
            int n = 0;
            ResultSet rs = conn.createStatement().executeQuery(statement);
            while (rs.next()) {
                n = rs.getInt(1);
            }
            return n;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createAccount(Account account) {
        try (Connection conn = getConnection()) {
            String stmt = "insert into account (username, password, email, role, full_name, dob, gender, phone_number, image_link,address) VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?)";
            PreparedStatement ppStmt = conn.prepareStatement(stmt);
            ppStmt.setString(1, account.getUsername());
            ppStmt.setString(2, account.getPassword());
            ppStmt.setString(3, account.getEmail());
            ppStmt.setString(4, account.getRole());
            ppStmt.setString(5, account.getFullName());
            ppStmt.setString(6, account.getDob());
            ppStmt.setString(7, account.getGender());
            ppStmt.setString(8, account.getPhoneNumber());
            ppStmt.setString(9, account.getImageLink());
            ppStmt.setString(10, account.getAddress());
            ppStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAccount(Account account) {
        try (Connection conn = getConnection()) {
            String stmt = "update account set username = ?, email = ?, role = ?, full_name = ?, dob = ?, gender = ?, phone_number = ?, image_link = ? , address = ?, password = ?  where id = ?";
            PreparedStatement ppStmt = conn.prepareStatement(stmt);
            ppStmt.setString(1, account.getUsername());
            ppStmt.setString(2, account.getEmail());
            ppStmt.setString(3, account.getRole());
            ppStmt.setString(4, account.getFullName());
            ppStmt.setString(5, account.getDob());
            ppStmt.setString(6, account.getGender());
            ppStmt.setString(7, account.getPhoneNumber());
            ppStmt.setString(8, account.getImageLink());
            ppStmt.setString(9, account.getAddress());
            ppStmt.setString(10, account.getPassword());
            ppStmt.setInt(11, account.getId());
            ppStmt.executeUpdate();
            return true;
        } catch (SQLException err) {
            throw new RuntimeException();
        }
    }

    public boolean deleteAccount(int id) {
        try (Connection conn = getConnection()) {
            String stmt = "DELETE FROM account WHERE id = ?";
            PreparedStatement ppStmt = conn.prepareStatement(stmt);
            ppStmt.setInt(1, id);
            ppStmt.executeUpdate();
            return true;
        } catch (SQLException err) {
            throw new RuntimeException();
        }
    }

    public int getTotalStaffAccounts() {
        try (Connection conn = getConnection()) {
            String statement = "select count(*) from account where account.role = 'STAFF'";
            int n = 0;
            ResultSet rs = conn.createStatement().executeQuery(statement);
            while (rs.next()) {
                n = rs.getInt(1);
            }
            return n;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getTotalClientAccounts() {
        try (Connection conn = getConnection()) {
            String statement = "select count(*) from account where account.role = 'USER'";
            int n = 0;
            ResultSet rs = conn.createStatement().executeQuery(statement);
            while (rs.next()) {
                n = rs.getInt(1);
            }
            return n;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
