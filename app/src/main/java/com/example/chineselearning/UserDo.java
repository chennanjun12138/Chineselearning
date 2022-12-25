package com.example.chineselearning;
import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.util.Log;
import com.mysql.jdbc.StringUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDo {
    public int login(String userAccount, String userPassword){
        HashMap<String, Object> map = new HashMap<>();
        Connection connection = JDBCUtils.getConn();
        int msg = 0;
        try {
            // mysql简单的查询语句。这里是根据user表的userAccount字段来查询某条记录
            //String sql="select id,chineseuserzhanghao,AES_DECRYPT(chinapassword,'key'),userjifen from chineseuser where chineseuserzhanghao = ?";
            String sql = "select id,chineseuserzhanghao,AES_DECRYPT(chinapassword,'key'),userjifen from chineseuser where chineseuserzhanghao = ?";
            if (connection != null){// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null){
                    Log.e(TAG,"账号：" + userAccount);
                    //根据账号进行查询
                    ps.setString(1, userAccount);
                    // 执行sql查询语句并返回结果集
                    ResultSet rs = ps.executeQuery();
                    int count = rs.getMetaData().getColumnCount();
                    //将查到的内容储存在map里
                    while (rs.next()){
                        // 注意：下标是从1开始的
                       // String  f= rs.getMetaData().getColumnName(1);

                        for (int i = 1;i <= count;i++){
                            String field = rs.getMetaData().getColumnName(i);
                            map.put(field, rs.getString(field));

                        }
                    }
                    connection.close();
                    ps.close();
                    if (map.size()!=0){
                        StringBuilder s = new StringBuilder();
                        //寻找密码是否匹配
                        for (String key : map.keySet()){
                           // Log.e(TAG,"密码：" + key);
                            if(key.equals("AES_DECRYPT(chinapassword,'key')")){

                                if(userPassword.equals(map.get(key))){
                                    msg = 1;            //密码正确
                                }
                                else
                                    msg = 2;            //密码错误
                                break;
                            }
                        }
                    }else {
                        Log.e(TAG, "查询结果为空");
                        msg = 3;
                    }
                }
                else {
                    msg = 0;
                }
            }else {
                msg = 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "异常login：" + e.getMessage());
            msg = 0;
        }
        return msg;
    }


    /**
     * function: 注册
     * */
    public boolean register(User user){
        HashMap<String, Object> map = new HashMap<>();
        // 根据数据库名称，建立连接
        Connection connection = JDBCUtils.getConn();
        try {

           String sql = "insert into chineseuser(chineseuserzhanghao,chinapassword,userjifen) values (?,AES_ENCRYPT(?,'key'),?)";
            if (connection != null){// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null){

                    //将数据插入数据库
                    ps.setString(1,user.getUserAccount());
                    ps.setString(2,user.getUserPassword());
                    ps.setInt(3,user.getUserjifeng());
                    // 执行sql查询语句并返回结果集
                    int rs = ps.executeUpdate();
                    if(rs>0)
                        return true;
                    else
                        return false;
                }else {
                    return  false;
                }
            }else {
                return  false;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "异常register：" + e.getMessage());
            return false;
        }

    }

    /**
     * function: 根据账号进行查找该用户是否存在
     * */
    public User findUser(String userAccount) {
        // 根据数据库名称，建立连接
        Connection connection = JDBCUtils.getConn();
        User user = null;
        try {
            String sql = "select * from chineseuser where chineseuserzhanghao = ?";
            if (connection != null){// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null) {
                    ps.setString(1, userAccount);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        //注意：下标是从1开始
                        int id = rs.getInt(1);
                        String userAccount1 = rs.getString(2);
                        String userPassword = rs.getString(3);
                        int userjifeng = rs.getInt(4);
                        user = new User(id, userAccount1, userPassword, userjifeng);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "异常findUser：" + e.getMessage());
            return null;
        }
        return user;
    }

    public List<HashMap<String,Object>> findmaxfiveUser() {
        Connection connection = JDBCUtils.getConn();
        List<HashMap<String,Object>> list=new ArrayList<HashMap<String, Object>>();
        try {
            Statement sta=connection.createStatement();
            String sql = "select * from chineseuser ORDER BY userjifen DESC LIMIT 0,5";
            ResultSet result=sta.executeQuery(sql);
            while (result.next()) {
                HashMap<String,Object> map=new HashMap<>();
                map.put("name",result.getString("chineseuserzhanghao"));
                list.add(map);
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "异常findUser：" + e.getMessage());
            return null;
        }
        return list;
    }
    public void update(String name,int y)
    {    java.sql.Connection connection = JDBCUtils.getConn();
        try {

            Statement sta=connection.createStatement();
            String sql = "update chineseuser set userjifen=' "+y+"' where chineseuserzhanghao= '"+name+"'";
            sta.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "异常findUser：" + e.getMessage());
        }
    }

    public boolean updateuser(String name,String y){

        // 根据数据库名称，建立连接
        Connection connection = JDBCUtils.getConn();
        try {
            String sql = "update chineseuser set userjifen=? where chineseuserzhanghao=?";
            if (connection != null){// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null){

                    //将数据插入数据库
                    ps.setString(1,y);
                    ps.setString(2,name);
                    // 执行sql查询语句并返回结果集
                    int rs = ps.executeUpdate();
                    if(rs>0)
                        return true;
                    else
                        return false;
                }else {
                    return  false;
                }
            }else {
                return  false;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "异常register：" + e.getMessage());
            return false;
        }

    }

}