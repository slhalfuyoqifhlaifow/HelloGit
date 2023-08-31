package web2.dao;

import web2.domain.User;
import web2.utiles.JDBCUtiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlUserDaoImpl {
    /**
     * �����û������û����ķ���
     *
     * @param username Ҫ���ҵ��û���
     * @return �ҵ�Ҫ���ط�װ���û���Ϣbean���Ҳ����ͷ���null
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
     * ���û����в�������
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
     * �����û����������ѯ�û�
     *
     * @param username �û���
     * @param password ����
     * @return �鵽���û�bean, ����鲻������null
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
