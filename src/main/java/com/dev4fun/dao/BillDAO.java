package com.dev4fun.dao;

import com.dev4fun.model.Bill;
import com.dev4fun.model.BillDetail;
import com.dev4fun.model.Chart;
import com.dev4fun.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO extends DAO {
    public Bill getBillById(int id) {
        try (Connection conn = getConnection()) {
            String statement = "select * from bill where id = ?";
            Bill bill = null;

            PreparedStatement ppStmt = conn.prepareStatement(statement);
            ppStmt.setInt(1, id);
            ResultSet rs = ppStmt.executeQuery();
            while (rs.next()) {
                bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setStatus(rs.getString("status"));
                bill.setUserId(rs.getInt("user_id"));
                bill.setFullName(rs.getString("full_name"));
                bill.setEmail(rs.getString("email"));
                bill.setAddress(rs.getString("address"));
                bill.setPhoneNumber(rs.getString("phone_number"));
                bill.setTotalAmount(rs.getFloat("total_amount"));
                bill.setPayMethod(rs.getString("pay_method"));
                bill.setNote(rs.getString("note"));
                bill.setCreatedAt(rs.getString("created_at"));
                bill.setInvoice_creator(rs.getString("invoice_creator"));
                bill.setBillDetails(new BillDetailDAO().getBillDetailByBillId(rs.getInt("id")));
            }
            return bill;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Bill> getBillsByAccountId(int id) {
        try (Connection conn = getConnection()) {
            String stmtBill = "select * from bill where user_id = ?";
            String stmtBillDetail = "select * from bill_detail where bill_id = ?";
            PreparedStatement ppStBill = conn.prepareStatement(stmtBill);
            PreparedStatement ppStBillDetail = conn.prepareStatement(stmtBillDetail);
            ppStBill.setInt(1, id);
            ResultSet rsBill = ppStBill.executeQuery();
            ArrayList<Bill> bills = new ArrayList<>();
            while (rsBill.next()) {
                Bill bill = new Bill();
                bill.setId(rsBill.getInt("id"));
                bill.setStatus(rsBill.getString("status"));
                bill.setUserId(rsBill.getInt("user_id"));
                bill.setFullName(rsBill.getString("full_name"));
                bill.setEmail(rsBill.getString("email"));
                bill.setAddress(rsBill.getString("address"));
                bill.setPhoneNumber(rsBill.getString("phone_number"));
                bill.setTotalAmount(rsBill.getFloat("total_amount"));
                bill.setPayMethod(rsBill.getString("pay_method"));
                bill.setNote(rsBill.getString("note"));
                bill.setCreatedAt(rsBill.getString("created_at"));
                bill.setInvoice_creator(rsBill.getString("invoice_creator"));
                ArrayList<BillDetail> listBillDetails = new ArrayList<>();
                ppStBillDetail.setInt(1, bill.getId());
                ResultSet rsBillDetail = ppStBillDetail.executeQuery();
                while (rsBillDetail.next()) {
                    BillDetail billDetail = new BillDetail();
                    billDetail.setId(rsBillDetail.getInt("id"));
                    billDetail.setQuantity(rsBillDetail.getInt("quantity"));
                    billDetail.setAmount(rsBillDetail.getInt("amount"));
                    billDetail.setSize(rsBillDetail.getInt("size"));
                    billDetail.setProduct(new ProductDAO().getProductById(rsBillDetail.getInt("product_id")));
                    listBillDetails.add(billDetail);
                }
                bill.setBillDetails(listBillDetails);
                bills.add(bill);
            }
            return bills;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Bill> getAllBill() {
        try (Connection conn = getConnection()) {
            String statement = "select * from bill";
            ArrayList<Bill> bills = new ArrayList<>();
            ResultSet rs = conn.createStatement().executeQuery(statement);
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setStatus(rs.getString("status"));
                bill.setUserId(rs.getInt("user_id"));
                bill.setFullName(rs.getString("full_name"));
                bill.setEmail(rs.getString("email"));
                bill.setAddress(rs.getString("address"));
                bill.setPhoneNumber(rs.getString("phone_number"));
                bill.setTotalAmount(rs.getFloat("total_amount"));
                bill.setPayMethod(rs.getString("pay_method"));
                bill.setNote(rs.getString("note"));
                bill.setCreatedAt(rs.getString("created_at"));
                bill.setInvoice_creator(rs.getString("invoice_creator"));
                bill.setBillDetails(new BillDetailDAO().getBillDetailByBillId(rs.getInt("id")));
                bills.add(bill);
            }
            return bills;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getTotalBills() {
        try (Connection conn = getConnection()) {
            String statement = "select count(*) from bill";
            int n = -1;
            ResultSet rs = conn.createStatement().executeQuery(statement);
            while (rs.next()) {
                n = rs.getInt(1);
            }
            return n;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int createBillByAdmin(Bill bill) {
        try (Connection conn = getConnection()) {
            String stmt = "insert into bill (status, user_id, full_name, email,address, phone_number, total_amount, pay_method, note,created_at,invoice_creator) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
            PreparedStatement ppStmt = conn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
            ppStmt.setString(1, bill.getStatus());
            ppStmt.setInt(2, bill.getUserId());
            ppStmt.setString(3, bill.getFullName());
            ppStmt.setString(4, bill.getEmail());
            ppStmt.setString(5, bill.getAddress());
            ppStmt.setString(6, bill.getPhoneNumber());
            ppStmt.setFloat(7, bill.getTotalAmount());
            ppStmt.setString(8, bill.getPayMethod());
            ppStmt.setString(9, bill.getNote());
            ppStmt.setString(10, bill.getCreatedAt());
            ppStmt.setString(11, bill.getInvoice_creator());
            ppStmt.executeUpdate();
            ResultSet rs = ppStmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
            else return -1;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public boolean createBill(Bill bill) {
        try (Connection conn = getConnection()) {
            String stmt = "insert into bill (status, user_id, full_name, email,address, phone_number, total_amount, pay_method, note,created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)";
            PreparedStatement ppStmt = conn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
            ppStmt.setString(1, bill.getStatus());
            ppStmt.setInt(2, bill.getUserId());
            ppStmt.setString(3, bill.getFullName());
            ppStmt.setString(4, bill.getEmail());
            ppStmt.setString(5, bill.getAddress());
            ppStmt.setString(6, bill.getPhoneNumber());
            ppStmt.setFloat(7, bill.getTotalAmount());
            ppStmt.setString(8, bill.getPayMethod());
            ppStmt.setString(9, bill.getNote());
            ppStmt.setString(10, bill.getCreatedAt());
//            ppStmt.setString(11, bill.getInvoice_creator());
            ppStmt.executeUpdate();
            int id;
            ResultSet rs = ppStmt.getGeneratedKeys();
            if (rs.next())
            {
                id=rs.getInt(1);
                String stmt1 = "insert into bill_detail (bill_id, amount , product_id ,quantity, size ) values(?,?,?,?,?) ";
                for(BillDetail b:bill.getBillDetails())
                {
                    PreparedStatement pp = conn.prepareStatement(stmt1);
                    pp.setInt(1, id);
                    pp.setFloat(2, b.getAmount());
                    pp.setInt(3, b.getProduct().getId());
                    pp.setInt(4, b.getQuantity());
                    pp.setInt(5, b.getSize());
                    pp.executeUpdate();
                }
                return true;
            }
            return false;
            
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public boolean updateBill(Bill bill) {
        try (Connection conn = getConnection()) {
            String stmt = "update bill set status = ?, user_id = ?, full_name = ?, email = ?,address=?, phone_number = ?, total_amount = ?, pay_method = ?, note = ?, invoice_creator = ? where id = ?";
            PreparedStatement ppStmt = conn.prepareStatement(stmt);
            ppStmt.setString(1, bill.getStatus());
            ppStmt.setInt(2, bill.getUserId());
            ppStmt.setString(3, bill.getFullName());
            ppStmt.setString(4, bill.getEmail());
            ppStmt.setString(5, bill.getAddress());
            ppStmt.setString(6, bill.getPhoneNumber());
            ppStmt.setFloat(7, bill.getTotalAmount());
            ppStmt.setString(8, bill.getPayMethod());
            ppStmt.setString(9, bill.getNote());
            ppStmt.setString(10, bill.getInvoice_creator());
            ppStmt.setInt(11, bill.getId());
            ppStmt.executeUpdate();
            return true;
        } catch (SQLException err) {
            throw new RuntimeException();
        }
    }

    public boolean deleteBill(int id) {
        try (Connection conn = getConnection()) {
            String stmt = "DELETE FROM bill WHERE id = ?";
            PreparedStatement ppStmt = conn.prepareStatement(stmt);
            ppStmt.setInt(1, id);
            ppStmt.executeUpdate();
            return true;
        } catch (SQLException err) {
            throw new RuntimeException();
        }
    }

    public int getTotalIncome() {
        int totalIncome = 0;
        try (Connection con = getConnection()) {
            String stmt = "SELECT sum(bill.total_amount) as total_amount FROM shoes.bill";
            PreparedStatement ps = con.prepareStatement(stmt);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) totalIncome = rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return totalIncome;
    }

    public int getTotalBillCancelled() {
        int totalBillCancelled = 0;
        Connection con = getConnection();
        String stmt = "SELECT count(*) FROM shoes.bill where status = 'cancelled'";
        try {
            PreparedStatement ps = con.prepareStatement(stmt);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalBillCancelled = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return totalBillCancelled;
    }

    public Bill getBillByUserName(String name) {
        try (Connection conn = getConnection()) {
            String stmt = "select * from bill where full_name = ?";
            Bill bill = null;
            PreparedStatement ps = conn.prepareStatement(stmt);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setStatus(rs.getString("status"));
                bill.setUserId(rs.getInt("user_id"));
                bill.setFullName(rs.getString("full_name"));
                bill.setEmail(rs.getString("email"));
                bill.setAddress(rs.getString("address"));
                bill.setPhoneNumber(rs.getString("phone_number"));
                bill.setTotalAmount(rs.getFloat("total_amount"));
                bill.setPayMethod(rs.getString("pay_method"));
                bill.setNote(rs.getString("note"));
                bill.setCreatedAt(rs.getString("created_at"));
                bill.setInvoice_creator(rs.getString("invoice_creator"));
                bill.setBillDetails(new BillDetailDAO().getBillDetailByBillId(rs.getInt("id")));
            }
            return bill;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Bill> getBillByStatus(String st) {
        ArrayList<Bill> bills = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String stmt = "select * from bill where status = ?";
            PreparedStatement ps = conn.prepareStatement(stmt);
            ps.setString(1, st);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setStatus(rs.getString("status"));
                bill.setUserId(rs.getInt("user_id"));
                bill.setFullName(rs.getString("full_name"));
                bill.setEmail(rs.getString("email"));
                bill.setAddress(rs.getString("address"));
                bill.setPhoneNumber(rs.getString("phone_number"));
                bill.setTotalAmount(rs.getFloat("total_amount"));
                bill.setPayMethod(rs.getString("pay_method"));
                bill.setNote(rs.getString("note"));
                bill.setCreatedAt(rs.getString("created_at"));
                bill.setInvoice_creator(rs.getString("invoice_creator"));
                bill.setBillDetails(new BillDetailDAO().getBillDetailByBillId(rs.getInt("id")));
                bills.add(bill);
            }
            return bills;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Chart> getIncomeForChart() {
        ArrayList<Chart> charts = new ArrayList<>();
        try (Connection con = getConnection()) {
            String stmt = "SELECT CONCAT(MONTH(created_at), '/', YEAR(created_at)) AS month_year,\n" +
                    "SUM(total_amount) AS monthly_income\n" +
                    "FROM  shoes.bill\n" +
                    "WHERE created_at >= DATE_SUB(NOW(), INTERVAL 6 MONTH)\n" +
                    "GROUP BY MONTH(created_at), YEAR(created_at)\n" +
                    "ORDER BY YEAR(created_at) ASC, MONTH(created_at) ASC";
            PreparedStatement ps = con.prepareStatement(stmt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Chart temp = new Chart();
                temp.setTime(rs.getString(1));
                temp.setIncome(rs.getFloat(2));
                charts.add(temp);
            }
            return charts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
