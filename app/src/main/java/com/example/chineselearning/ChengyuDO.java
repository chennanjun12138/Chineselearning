package com.example.chineselearning;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kotlin.random.Random;

public class ChengyuDO {
    public Chengyu findchengyu(String chengyuname) {
        // 根据数据库名称，建立连接
        Connection connection = JDBCUtils.getConn();
        Chengyu chengyu = null;
        try {
            String sql = "select * from chengyu where chengyu = ?";
            if (connection != null){// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null) {
                    ps.setString(1, chengyuname);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        //注意：下标是从1开始
                        String id = rs.getString(1);
                        String userAccount1 = rs.getString(2);
                        String userPassword = rs.getString(3);
                        String userjifeng = rs.getString(4);
                        chengyu = new Chengyu(id, userAccount1, userPassword, userjifeng);

                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "异常findUser：" + e.getMessage());
            return null;
        }
        return chengyu;
    }
    public List<HashMap<String,Object>> chenyulist() {
        Connection connection = JDBCUtils.getConn();
        List<HashMap<String,Object>> list=new ArrayList<HashMap<String, Object>>();
        try {
            Statement sta=connection.createStatement();
            String sql = "select * from chengyu ";
            ResultSet result=sta.executeQuery(sql);
            while (result.next()) {
                HashMap<String,Object> map=new HashMap<>();
                map.put("name",result.getString("chengyu"));
                map.put("pingying",result.getString("pingying"));
                map.put("yisi",result.getString("yisi"));
                list.add(map);
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "异常findUser：" + e.getMessage());
            return null;
        }
        return list;
    }
    public List<HashMap<String,Object>> chenyucuowulist() {
        Connection connection = JDBCUtils.getConn();
        List<HashMap<String,Object>> list=new ArrayList<HashMap<String, Object>>();
        try {
            Statement sta=connection.createStatement();
            String sql = "select * from cuowu ";
            ResultSet result=sta.executeQuery(sql);
            while (result.next()) {
                HashMap<String,Object> map=new HashMap<>();
                map.put("chuowu1",result.getString("chuowu1"));
                map.put("chuowu2",result.getString("chuowu2"));
                map.put("chuowu3",result.getString("chuowu3"));
                list.add(map);
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "异常findUser：" + e.getMessage());
            return null;
        }
        return list;
    }
}
