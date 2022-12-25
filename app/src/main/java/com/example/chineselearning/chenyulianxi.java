package com.example.chineselearning;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class chenyulianxi extends AppCompatActivity {
    TextView t1;
    TextView t2;
    TextView res;
    int sum=0;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    int i=0;
    int ans;
    String ans2;
    private MyApp myApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chenyulianxi);
         t1=findViewById(R.id.lianxi1);
         t2=findViewById(R.id.lianxi2);
         b1=findViewById(R.id.lianxi3);
         b2=findViewById(R.id.lianxi4);
         b3=findViewById(R.id.lianxi5);
         b4=findViewById(R.id.lianxi6);
        myApp=(MyApp)getApplication();
        Intent r=getIntent();

            refish(i);
        b1.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                        if(ans==0)
                                        {   sum+=10;
                                            i=i+1;
                                            b1.setBackgroundDrawable(getResources().getDrawable(R.drawable.translucent));
                                            b2.setBackgroundDrawable(getResources().getDrawable(R.drawable.translucent));
                                            b3.setBackgroundDrawable(getResources().getDrawable(R.drawable.translucent));
                                            b4.setBackgroundDrawable(getResources().getDrawable(R.drawable.translucent));
                                            if(i==myApp.getNum())
                                            {

                                                Intent intent2=new Intent(chenyulianxi.this,MainActivity.class);
                                                intent2.putExtra("id",1);
                                                intent2.putExtra("yonghu",myApp.getYonghuming());
                                                myApp.setName(sum);
                                                startActivity(intent2);

                                            }
                                            else
                                            {
                                                refish(i);
                                            }

                                        }
                                        else
                                        {
                                            b1.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_button_selector));
                                        }

                                   }
                               }

        );
        b2.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      if(ans==1)
                                      {   sum+=10;
                                          i=i+1;
                                          b1.setBackgroundDrawable(getResources().getDrawable(R.drawable.translucent));
                                          b2.setBackgroundDrawable(getResources().getDrawable(R.drawable.translucent));
                                          b3.setBackgroundDrawable(getResources().getDrawable(R.drawable.translucent));
                                          b4.setBackgroundDrawable(getResources().getDrawable(R.drawable.translucent));
                                          if(i==myApp.getNum())
                                          {

                                              Intent intent2=new Intent(chenyulianxi.this,MainActivity.class);
                                              intent2.putExtra("id",1);
                                              intent2.putExtra("yonghu",myApp.getYonghuming());
                                              myApp.setName(sum);
                                              startActivity(intent2);

                                          }
                                          else
                                          {
                                              refish(i);
                                          }

                                      }
                                      else
                                      {
                                          b2.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_button_selector));
                                      }

                                  }
                              }

        );
        b3.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(ans==2)
                                      { sum+=10;
                                          i=i+1;
                                          b1.setBackgroundDrawable(getResources().getDrawable(R.drawable.translucent));
                                          b2.setBackgroundDrawable(getResources().getDrawable(R.drawable.translucent));
                                          b3.setBackgroundDrawable(getResources().getDrawable(R.drawable.translucent));
                                          b4.setBackgroundDrawable(getResources().getDrawable(R.drawable.translucent));
                                          if(i==myApp.getNum())
                                          {

                                              Intent intent2=new Intent(chenyulianxi.this,MainActivity.class);
                                              intent2.putExtra("id",1);
                                              intent2.putExtra("yonghu",myApp.getYonghuming());
                                              myApp.setName(sum);
                                              startActivity(intent2);

                                          }
                                          else
                                          {
                                              refish(i);
                                          }

                                      }
                                      else
                                      {
                                          b3.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_button_selector));
                                      }
                                  }
                              }

        );
        b4.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      if(ans==3)
                                      {   sum+=10;
                                          i=i+1;
                                          b1.setBackgroundDrawable(getResources().getDrawable(R.drawable.translucent));
                                          b2.setBackgroundDrawable(getResources().getDrawable(R.drawable.translucent));
                                          b3.setBackgroundDrawable(getResources().getDrawable(R.drawable.translucent));
                                          b4.setBackgroundDrawable(getResources().getDrawable(R.drawable.translucent));
                                          if(i==myApp.getNum())
                                          {

                                              Intent intent2=new Intent(chenyulianxi.this,MainActivity.class);
                                              intent2.putExtra("id",1);
                                              intent2.putExtra("yonghu",myApp.getYonghuming());
                                              myApp.setName(sum);
                                              startActivity(intent2);

                                          }
                                          else
                                          {
                                              refish(i);
                                          }

                                      }
                                      else
                                      {
                                         b4.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_button_selector));
                                      }
                                  }
                              }

        );

    }
    private void refish(int i)
    {
        ChengyuDO chengyudo = new ChengyuDO();
        Thread thread = new Thread(new Runnable() {
            List<HashMap<String, Object>> list1 = new ArrayList<HashMap<String, Object>>();
            List<HashMap<String, Object>> list2 = new ArrayList<HashMap<String, Object>>();
            @Override
            public void run() {
                JDBCUtils.getConn();
                list1 = chengyudo.chenyulist();
                list2=chengyudo.chenyucuowulist();
                t1.setText(list1.get(i).get("name").toString());
                t2.setText(list1.get(i).get("pingying").toString());
                 ans=random();
             if(ans==0)
             {
                 b1.setText(list1.get(i).get("yisi").toString());
                 b2.setText(list2.get(i).get("chuowu1").toString());
                 b3.setText(list2.get(i).get("chuowu2").toString());
                 b4.setText(list2.get(i).get("chuowu3").toString());
             }
             else if(ans==1)
             {
                 b2.setText(list1.get(i).get("yisi").toString());
                 b1.setText(list2.get(i).get("chuowu1").toString());
                 b3.setText(list2.get(i).get("chuowu2").toString());
                 b4.setText(list2.get(i).get("chuowu3").toString());
             }
             else if(ans==2)
             {
                 b3.setText(list1.get(i).get("yisi").toString());
                 b2.setText(list2.get(i).get("chuowu1").toString());
                 b1.setText(list2.get(i).get("chuowu2").toString());
                 b4.setText(list2.get(i).get("chuowu3").toString());
             }
             else if(ans==3)
             {   b2.setText(list2.get(i).get("chuowu1").toString());
                 b3.setText(list2.get(i).get("chuowu2").toString());
                 b1.setText(list2.get(i).get("chuowu3").toString());
                 b4.setText(list1.get(i).get("yisi").toString());
             }
                Log.d(TAG, list1.toString());
            }
        });
        thread.start();
    }
    public static int random(){
        Random num = new Random();
        int i = num.nextInt(4);//从零开始左开右闭即大于等于0小于100
        return i;

    }

}