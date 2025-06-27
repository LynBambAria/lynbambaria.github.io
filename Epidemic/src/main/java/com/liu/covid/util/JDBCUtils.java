package com.liu.covid.util;
import java.sql.*;

/**
 * JDBCUtils 是一个工具类，提供了获取数据库连接的方法。
 * 该类使用了 MySQL 连接数据库，封装了获取数据库连接的过程。
 */
public class JDBCUtils {

    // 数据库连接的 URL，包含数据库名称、字符编码及时区设置
    static final String url = "jdbc:mysql://localhost:3306/covid?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";

    // 数据库的用户名
    static final String user = "root";

    // 数据库的密码
    static final String password = "123456";

    // 数据库连接对象
    private static Connection con;

    /**
     * 连接数据库
     * @return 返回数据库连接对象，如果连接失败则返回 null
     */
    public static Connection getConnection(){
        // 加载数据库驱动
        try {
            Class.forName("coym.msql.cj.jdbc.Driver"); // 加载 MySQL 驱动（8.0 版本及以上）
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  // 如果驱动加载失败，打印异常
        }

        // 进行数据库连接
        try {
            con = DriverManager.getConnection(url, user, password);  // 使用 JDBC URL、用户名和密码进行连接
            con.setAutoCommit(true);  // 设置自动提交事务
        } catch (SQLException e) {
            e.printStackTrace();  // 如果连接失败，打印异常
        }

        return con;  // 返回连接对象
    }
}
