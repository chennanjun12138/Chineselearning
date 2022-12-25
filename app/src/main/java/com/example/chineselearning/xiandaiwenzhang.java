package com.example.chineselearning;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class xiandaiwenzhang extends AppCompatActivity {
  //  private Handler handler = null;
    List<xiandaitab> list = new ArrayList<>();
    DbContect helper;
    private MyApp myApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiandaiwenzhang);
        helper=new DbContect(  xiandaiwenzhang.this);
        myApp=(MyApp)getApplication();
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.query("xiandai_tb", null, null, null, null, null, null);
        if (c != null && c.getCount() >= 1) {
            while (c.moveToNext()) {

                list.add(new xiandaitab(c.getString(0), c.getString(1),
                        c.getString(2)));
            }
            c.close();
            db.close();//关闭数据库
        }
        List<Map<String, Object>> listitem=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("xiandainame", list.get(i).xiandainame);
            map.put("xiandaizuozhe", list.get(i).xiandaizuozhe);
            map.put("xiandaitext", list.get(i).xiandaitext);
            listitem.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,listitem, R.layout.cnjbook_item
                , new String[]{"xiandainame", "xiandaizuozhe", "xiandaitext"}
                , new int[]{R.id.book_name});

        ListView listView = (ListView)findViewById(R.id.listxiandai);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//设置监听器
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myApp.setName(1);
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Intent intent=new Intent(xiandaiwenzhang.this,xiandailist.class);
                intent.putExtra("name",map.get("xiandainame").toString());
                intent.putExtra("zuozhe",map.get("xiandaizuozhe").toString());
                intent.putExtra("text",map.get("xiandaitext").toString());

                startActivity(intent);
            }
        });

    }


}