package com.liu.covid.util;

import java.sql.Connection;
import java.sql.SQLException;

public class test{
    public static void main(String[] args) throws SQLException {
        JDBCUtils jdbcConnection=new JDBCUtils();
        Connection connection=jdbcConnection.getConnection();
        if(connection!=null){
            System.out.println("数据库连接成功");
        }else {
            System.out.println("数据库连接失败");
        }
    }
}
