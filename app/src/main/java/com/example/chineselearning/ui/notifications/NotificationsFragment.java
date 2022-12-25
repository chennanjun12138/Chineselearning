package com.example.chineselearning.ui.notifications;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.chineselearning.JDBCUtils;
import com.example.chineselearning.MyApp;
import com.example.chineselearning.R;
import com.example.chineselearning.User;
import com.example.chineselearning.UserDo;
import com.example.chineselearning.databinding.FragmentNotificationsBinding;
import com.example.chineselearning.denglu;
import com.example.chineselearning.jifengpaihang;

public class NotificationsFragment extends Fragment {
    private MyApp myApp;
    TextView tv2;
    TextView t3;
   int x1;
    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ImageView imgc = root.findViewById(R.id.ivCategory);
        ImageView imgc2 = root.findViewById(R.id.paiming);
        imgc.setOnClickListener(new MyJump());
         tv2=root.findViewById(R.id.yonghu);
         t3=root.findViewById(R.id.t3);
        Button b1=root.findViewById(R.id.zongjie);
        imgc2.setOnClickListener(new zhanshi());
        myApp=(MyApp)getActivity().getApplication();
        Intent i=getActivity().getIntent();
        String str =i.getStringExtra("data");
        int ans=i.getIntExtra("jifeng",0);
        String res=i.getStringExtra("yonghu");
        myApp.setNum(ans);
        myApp.setAns(ans);
        myApp.setYonghuming(str);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   int chngyu=i.getIntExtra("date",0);
                update(v);
            }
        });
       if(res==null)
       {
           tv2.setText(myApp.getYonghuming());
       }
       else
       {
           tv2.setText(res);
       }
      x1=myApp.getName()+myApp.getAns();
        t3.setText(x1+"");
      // myApp.setName(ans);
        return root;

    }
    private class MyJump implements View.OnClickListener{
        @Override
        public void onClick(View view){
            Intent intent = new Intent();
            intent.setClass(getActivity(), denglu.class);
            startActivity(intent);
        }
    }

    private class zhanshi implements View.OnClickListener{
        @Override
        public void onClick(View view){
            Intent intent = new Intent();
            intent.setClass(getActivity(), jifengpaihang.class);
            startActivity(intent);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void update(View view){
        String userAccount1 = tv2.getText().toString();
        String userPassword1 = t3.getText().toString();
        new Thread(){
            @Override
            public void run() {
                int msg = 0;
                UserDo userDao = new UserDo();

                    boolean flag = userDao.updateuser(userAccount1,userPassword1);
                    if(flag){
                        msg = 1;
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
                Toast.makeText(getActivity(),"失败",Toast.LENGTH_LONG).show();
            } else if(msg.what == 1) {
                Toast.makeText(getActivity(),"成功",Toast.LENGTH_LONG).show();
            }
        }
    };
}