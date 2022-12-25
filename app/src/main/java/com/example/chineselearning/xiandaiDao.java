package com.example.chineselearning;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class xiandaiDao {

    public List<HashMap<String,Object>> getxiandaiwen() {
        Connection connection = JDBCUtils.getConn();
        List<HashMap<String,Object>> list=new ArrayList<HashMap<String, Object>>();
        try {
            Statement sta=connection.createStatement();
            String sql = "select * from xiandaiwenzhang";
            ResultSet result=sta.executeQuery(sql);
            while (result.next()) {
                HashMap<String,Object> map=new HashMap<>();
                map.put("name",result.getString("bookname"));
                map.put("zuozhe",result.getString("bookzhuozhe"));
                map.put("text",result.getString("booktext"));
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
