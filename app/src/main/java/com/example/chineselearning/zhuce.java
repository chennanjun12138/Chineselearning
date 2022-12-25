package com.example.chineselearning;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class zhuce extends AppCompatActivity {
    EditText name;  //用户名
    EditText pass;  //密码
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        name=(EditText) findViewById(R.id.input_user_text2);  //获取用户名
        pass=(EditText) findViewById(R.id.inmima2);
        btn=(Button) findViewById(R.id.zhucebtn);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {

                                       register(v);
                                   }
                               }

        );
    }
    public void register(View view){

        String userAccount1 = name.getText().toString();
        String userPassword1 = pass.getText().toString();
        User user = new User();
        user.setUserAccount(userAccount1);
        user.setUserPassword(userPassword1);
        user.setUserjifeng(0);

        new Thread(){
            @Override
            public void run() {

                int msg = 0;

                UserDo userDao = new UserDo();

                User uu = userDao.findUser(user.getUserAccount());
                if(TextUtils.isEmpty(userAccount1)  || TextUtils.isEmpty(userPassword1))
                {
                    msg=0;
                }
                else
                {
                    if(uu != null){
                        msg = 1;
                    }
                    else{
                        boolean flag = userDao.register(user);
                        if(flag){
                            msg = 2;
                        }
                    }
                }
                hand.sendEmptyMessage(msg);
            }
        }.start();


    }
    @SuppressLint("HandlerLeak")
    final Handler hand = new Handler()
    {
        public void handleMessage(Message msg) {
            if(msg.what == 0) {
                Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_LONG).show();
            } else if(msg.what == 1) {
                Toast.makeText(getApplicationContext(),"该账号已经存在，请换一个账号",Toast.LENGTH_LONG).show();
            } else if(msg.what == 2) {
                Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                //将想要传递的数据用putExtra封装在intent中
              //  intent.putExtra("a","注册");
               // setResult(RESULT_CANCELED,intent);
                finish();
            }
        }
    };
}