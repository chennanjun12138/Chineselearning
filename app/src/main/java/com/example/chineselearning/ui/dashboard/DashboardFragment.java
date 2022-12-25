package com.example.chineselearning.ui.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.chineselearning.Chengyu;
import com.example.chineselearning.ChengyuDO;
import com.example.chineselearning.MyApp;
import com.example.chineselearning.R;
import com.example.chineselearning.User;
import com.example.chineselearning.UserDo;
import com.example.chineselearning.chenyulianxi;
import com.example.chineselearning.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {
    private MyApp myApp;

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    EditText search;
    ImageButton btn;
    TextView t1;
    String tx1;
    String tx2;
    String tx3;
    TextView t2;
    TextView t3;
    Button b1;
    Button b2;
    EditText f1;
    int ans;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        myApp=(MyApp)getActivity().getApplication();
         search=root.findViewById(R.id.select01);
         btn=root.findViewById(R.id.select_p02);
         t1=root.findViewById(R.id.chengyu2);
         t2=root.findViewById(R.id.chengyu3);
         t3=root.findViewById(R.id.chengyu4);
         b1=root.findViewById(R.id.chenyulianxi);
         f1=root.findViewById(R.id.timushu);
          b2=root.findViewById(R.id.zhanshi);
          b2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                  builder.setTitle("成语典故");
                  builder.setMessage(t3.getText().toString());
                  builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialogInterface, int i) {
                      }
                  });

                  builder.show();
              }
          });
         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 show(v);
             }
         });


         b1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                if(f1.getText().toString().equals(""))
                { Toast.makeText(getActivity(),"请输入问题个数",Toast.LENGTH_LONG).show();

                }
                else
                {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), chenyulianxi.class);
                    myApp.setNum(Integer.parseInt(f1.getText().toString().trim()));
                    myApp.setName(myApp.getAns());
                    startActivity(intent);
                }

             }
         });
        return root;
    }
    public void show(View view){
        new Thread(){
            @Override
            public void run() {
                ChengyuDO chengyu=new ChengyuDO();
                Chengyu c=new Chengyu();
                c=chengyu.findchengyu(search.getText().toString());
                if(c==null)
                {
                    Looper.prepare();
                    Toast.makeText(getActivity(),"未找到该成语！",Toast.LENGTH_LONG).show();
                    Looper.loop();
                }
                else
                {
                    tx1=c.getChengyupingyin();
                    tx2=c.getChengyuyisi();
                    tx3=c.getChengyudiangu();
                    t1.setText(tx1);
                    t2.setText(tx2);
                    t3.setText(tx3);
                }

            }
        }.start();

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}