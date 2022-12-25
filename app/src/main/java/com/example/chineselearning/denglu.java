package com.example.chineselearning;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.service.autofill.SaveInfo;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class denglu extends AppCompatActivity {
    EditText name;  //用户名
    EditText pass;  //密码
    int jifeng;
    private ImageView iv_eye1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);
        TextView textView = (TextView) findViewById(R.id.zhuce);
        Button btn=(Button)findViewById(R.id.denglu);
        iv_eye1=findViewById(R.id.iv_eye1);
        iv_eye1.setOnTouchListener(new myOnTouchListener());
        name=(EditText) findViewById(R.id.input_user_text);  //获取用户名
        pass=(EditText) findViewById(R.id.input_key_text);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       login(v);
                                   }
                               }

        );
        SpannableString ss = new SpannableString("立刻注册");
        ss.setSpan(new ClickableSpan(){
            @Override
            public  void updateDrawState(TextPaint t){
                super.updateDrawState(t);
                t.setUnderlineText(false); //设置去掉下划线

                t.setColor(Color.BLACK);//设置字体颜色为黑
            }
            @Override
            public void onClick(View widget){
                Intent intent = new Intent(denglu.this,zhuce.class);//当点击详情页时触发事件函数完成页面跳转
                startActivity(intent);
            }},0,4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
    private class myOnTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            if (v.getId() == R.id.iv_eye1) {
                switch (action) {
                    case MotionEvent.ACTION_DOWN://按下（按下动作）
                        iv_eye1.setSelected(true);
                        //密码可见
                        pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        break;
                    case MotionEvent.ACTION_UP://抬起（抬起动作）
                        iv_eye1.setSelected(false);
                        //密码不可见
                        pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        break;
                }
            }
            return true;
        }
    }
    public void login(View view){
        new Thread(){
            @Override
            public void run() {
                UserDo userDao = new UserDo();
                int msg = userDao.login(name.getText().toString(),pass.getText().toString());
                User bengci=userDao.findUser(name.getText().toString());
                hand1.sendEmptyMessage(msg);
                jifeng=bengci.getUserjifeng();
            }
        }.start();

    }

    @SuppressLint("HandlerLeak")
    final Handler hand1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_LONG).show();
            } else if (msg.what == 1) {
                Intent intent = new Intent(denglu.this,MainActivity.class);//当点击详情页时触发事件函数完成页面跳转
                intent.putExtra("data",name.getText().toString().trim());
                intent.putExtra("jifeng",jifeng);
                intent.putExtra("id",2);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_LONG).show();
            } else if (msg.what == 2){
                Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_LONG).show();
            } else if (msg.what == 3){
                Toast.makeText(getApplicationContext(), "账号不存在", Toast.LENGTH_LONG).show();
            }
        }
    };
}