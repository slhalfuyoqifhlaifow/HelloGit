package web2.utiles;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtiles {
    private static DataSource source = new ComboPooledDataSource();
    private JDBCUtiles(){}
    /**
     * 获取连接
     */
    public static Connection getConn(){
        try {
            return source.getConnection();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /**
     * 关闭资源
     */
    public static void close(Connection conn, Statement stat, ResultSet rs){
        if (conn!=null){
            try {
                conn.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }finally {
                conn = null;
            }
        }
        if (stat!=null){
            try {
                stat.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }finally {
                stat = null;
            }
        }
        if (rs!=null){
            try {
                rs.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }finally {
                rs = null;
            }
        }
    }
}
