package com.example.chineselearning;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class jifengpaihang extends AppCompatActivity {
    private MyApp myApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jifengpaihang);
        TextView t1 = findViewById(R.id.panghan1);
         TextView t2=findViewById(R.id.panghan2);
         TextView t3=findViewById(R.id.panghan3);
          TextView t4=findViewById(R.id.panghan4);
          TextView t5=findViewById(R.id.panghan5);

        UserDo userDao = new UserDo();
        Thread thread = new Thread(new Runnable() {
            List<HashMap<String, Object>> list1 = new ArrayList<HashMap<String, Object>>();
            @Override
            public void run() {
                JDBCUtils.getConn();
                list1 = userDao.findmaxfiveUser();
                t1.setText(list1.get(0).get("name").toString());
                t2.setText(list1.get(1).get("name").toString());
                t3.setText(list1.get(2).get("name").toString());
                t4.setText(list1.get(3).get("name").toString());
                t5.setText(list1.get(4).get("name").toString());
                Log.d(TAG, list1.toString());

            }
        });
        thread.start();

    }
}