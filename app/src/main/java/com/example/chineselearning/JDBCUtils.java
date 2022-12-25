package com.example.chineselearning;

import android.util.Log;

import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCUtils {
    private static final String TAG = "mysql-party-JDBCUtils";

    private static String driver = "com.mysql.jdbc.Driver";// MySql驱动



    public static java.sql.Connection getConn(){

        java.sql.Connection connection = null;
        try{
            Class.forName(driver);// 动态加载类
            String ip = "192.168.56.1";
            int port = 3306;
            String dbName = "mysql";
            String url = "jdbc:mysql://" + ip + ":" + port
                    + "/" + dbName+"?useUnicode=true&characterEncoding=utf-8&useSSL=false";
            // 构建连接mysql的字符串
            String user = "root";
            String password = "123456";
            // 尝试建立到给定数据库URL的连接
            connection = DriverManager.getConnection(url, user, password);

        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

}
