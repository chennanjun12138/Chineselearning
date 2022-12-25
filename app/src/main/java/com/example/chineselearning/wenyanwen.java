package com.example.chineselearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class wenyanwen extends AppCompatActivity {
    List<gushitab> list = new ArrayList<>();
    DbContect helper;
    private MyApp myApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wenyanwen);
        helper=new DbContect(  wenyanwen.this);
        myApp=(MyApp)getApplication();
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.query("wenyanwen_tb", null, null, null, null, null, null);
        if (c != null && c.getCount() >= 1) {
            while (c.moveToNext()) {

                list.add(new gushitab(c.getString(0), c.getString(1),
                        c.getString(2),c.getString(3)));
            }
            c.close();
            db.close();//关闭数据库
        }
        List<Map<String, Object>> listitem=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("wenyanwenname", list.get(i).gushiname);
            map.put("wenyanwenzuozhe", list.get(i).gushizuozhe);
            map.put("wenyanwentext", list.get(i).gushitext);
            map.put("wenyanwenfanyi",list.get(i).gushifanyi);
            listitem.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,listitem, R.layout.cnjbook_item
                , new String[]{"wenyanwenname", "wenyanwenzuozhe", "wenyanwentext","wenyanwenfanyi"}
                , new int[]{R.id.book_name});

        ListView listView = (ListView)findViewById(R.id.listgwenyanwen);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//设置监听器
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                myApp.setName(1);
                Intent intent=new Intent(wenyanwen.this,wenyanwenlist.class);
                intent.putExtra("name",map.get("wenyanwenname").toString());
                intent.putExtra("zuozhe",map.get("wenyanwenzuozhe").toString());
                intent.putExtra("text",map.get("wenyanwentext").toString());
                intent.putExtra("zhushi",map.get("wenyanwenfanyi").toString());
                startActivity(intent);
            }
        });

    }
}