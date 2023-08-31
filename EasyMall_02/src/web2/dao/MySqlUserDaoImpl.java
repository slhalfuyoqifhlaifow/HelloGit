package web2.dao;

import web2.domain.User;
import web2.utiles.JDBCUtiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlUserDaoImpl {
    /**
     * 根据用户查找用户名的方法
     *
     * @param username 要查找的用户名
     * @return 找到要返回封装的用户信息bean，找不到就返回null
     */
    public User queryUserByUsername(String username) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtiles.getConn();
            ps = conn.prepareStatement("select * from user where username=?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setNickname(rs.getString("nickname"));
                u.setEmail(rs.getString("email"));
                return u;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JDBCUtiles.close(conn, ps, rs);
        }
    }

    /**
     * 向用户表中插入数据
     *
     * @param user
     */
    public void addUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JDBCUtiles.getConn();
            ps = conn.prepareStatement("insert into user values (null,?,?,?,?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNickname());
            ps.setString(4, user.getEmail());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JDBCUtiles.close(conn, ps, null);
        }
    }

    /**
     * 根据用户名和密码查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 查到的用户bean, 如果查不到返回null
     */
    public User queryUserByUsernameAndPassword(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtiles.getConn();
            ps=conn.prepareStatement("select * from user where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setNickname(rs.getString("nickname"));
                u.setEmail(rs.getString("email"));
                return u;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            JDBCUtiles.close(conn,ps,rs);
        }
    }
}
