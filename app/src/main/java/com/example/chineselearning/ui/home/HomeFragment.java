package com.example.chineselearning.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.chineselearning.MyApp;
import com.example.chineselearning.R;
import com.example.chineselearning.chenyulianxi;
import com.example.chineselearning.databinding.FragmentHomeBinding;
import com.example.chineselearning.gushi;
import com.example.chineselearning.wenyanwen;
import com.example.chineselearning.xiandaiwenzhang;

public class HomeFragment extends Fragment {
    private MyApp myApp;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    Button b1;
    Button b2;
    Button b3;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        b1=root.findViewById(R.id.wenyanwen);
        b2=root.findViewById(R.id.gushu);
        b3=root.findViewById(R.id.xiandaiwenzhang);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(getActivity(), wenyanwen.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(getActivity(), gushi.class);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(getActivity(), xiandaiwenzhang.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}