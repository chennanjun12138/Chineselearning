package com.example.chineselearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class xiandailist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiandailist);
        TextView t1=findViewById(R.id.xiandai1);
        TextView t2=findViewById(R.id.xiandai2);
        TextView t3=findViewById(R.id.xiandai3);
        Intent i=getIntent();
        String s1 =i.getStringExtra("name");
        String s2 =i.getStringExtra("zuozhe");
        String s3 =i.getStringExtra("text");
        t1.setText(s1);
        t2.setText(s2);
        t3.setText(s3);
    }
}