package com.dev4fun.dao;

import com.dev4fun.model.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentDAO extends DAO {
    public ArrayList<Comment> getCommentByProductId(int id) {
        ArrayList<Comment> comments = new ArrayList<>();
        try (Connection con = getConnection()) {
            String stmt = "SELECT * FROM comment where product_id = ?";
            PreparedStatement ps = con.prepareStatement(stmt);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment temp = new Comment();
                temp.setId(rs.getInt("id"));
                temp.setContent(rs.getString("content"));
                temp.setProduct_id(rs.getInt("product_id"));
                temp.setUser(new AccountDAO().getAccountById(rs.getInt("user_id")));
                comments.add(temp);
            }
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createComment(Comment comment) {
        try (Connection con = getConnection()) {
            String stmt = "insert into comment ( content, product_id,user_id) values( ? , ? , ?)";
            PreparedStatement ps = con.prepareStatement(stmt);
            ps.setString(1,comment.getContent());
            ps.setInt(2,comment.getProduct_id());
            ps.setInt(3,comment.getUser().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
