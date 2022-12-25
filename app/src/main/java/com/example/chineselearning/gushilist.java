package com.example.chineselearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.widget.TextView;
public class gushilist extends AppCompatActivity {
    private MyApp myApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gushilist);
        myApp=(MyApp)getApplication();
        TextView t1=findViewById(R.id.gushi1);
        TextView t2=findViewById(R.id.gushi2);
        TextView t3=findViewById(R.id.gushi3);
        TextView t4=findViewById(R.id.gushi4);
        Intent i=getIntent();
        String s1 =i.getStringExtra("name");
        String s2 =i.getStringExtra("zuozhe");
        String s3 =i.getStringExtra("text");
        String s4 =i.getStringExtra("zhushi");
        t1.setText(s1);
        t2.setText(s2);
        t3.setText(s3);
        t4.setText(s4);
    }
}