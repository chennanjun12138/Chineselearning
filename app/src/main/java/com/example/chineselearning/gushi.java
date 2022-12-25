package com.example.chineselearning;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class gushi extends AppCompatActivity {
    List<gushitab> list = new ArrayList<>();
    DbContect helper;
    private MyApp myApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gushi);
        myApp=(MyApp)getApplication();
        helper=new DbContect(  gushi.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.query("gushi_tb", null, null, null, null, null, null);
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
            map.put("gushiname", list.get(i).gushiname);
            map.put("gushizuozhe", list.get(i).gushizuozhe);
            map.put("gushitext", list.get(i).gushitext);
            map.put("gushifanyi",list.get(i).gushifanyi);
            listitem.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,listitem, R.layout.cnjbook_item
                , new String[]{"gushiname", "gushizuozhe", "gushitext","gushifanyi"}
                , new int[]{R.id.book_name});

        ListView listView = (ListView)findViewById(R.id.listgushi);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//设置监听器
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myApp.setName(1);
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Intent intent=new Intent(gushi.this,gushilist.class);
                intent.putExtra("name",map.get("gushiname").toString());
                intent.putExtra("zuozhe",map.get("gushizuozhe").toString());
                intent.putExtra("text",map.get("gushitext").toString());
                intent.putExtra("zhushi",map.get("gushifanyi").toString());
                startActivity(intent);
            }
        });
    }
}