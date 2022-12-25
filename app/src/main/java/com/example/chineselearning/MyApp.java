package com.example.chineselearning;


import android.app.Application;

public class MyApp extends Application {
    public int name;
    public  int num;
    public  int ans;
    public int getAns()
    {
        return ans;
    }
    public void setAns(int ans)
    {
        this.ans=ans;
    }
    public int getNum()
    {
        return num;
    }
    public void setNum(int num)
    {
        this.num=num;
    }


    public String yonghuming;
    public int getName() {
        return name;
    }
    public String getYonghuming() {
        return yonghuming;
    }
    public void setYonghuming(String yonghuming)
    {
        this.yonghuming=yonghuming;
    }
    public void setName(int name) {
        this.name = this.name+name;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        setName(0);
        setNum(0);
        setAns(0);
    }
}