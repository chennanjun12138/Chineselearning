package com.example.chineselearning;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
public class DbContect extends SQLiteOpenHelper {
    private static final int VERSION=3;
    private static final String DBNAME="Chinese.db";   //  创建数据库名叫 Users
    private Context mContext;

    public DbContect(Context context){
        super(context,DBNAME,null,VERSION);
        mContext = context;
    }
    //创建数据库
    public void onCreate(SQLiteDatabase db){

        db.execSQL("create table gushi_tb(gushiname varchar(10),gushizuizhe varchar(10) , gushitext varchar(200)," +
                        " gushizhushi varchar(200))");


        db.execSQL("create table wenyanwen_tb(wenyanwenname varchar(10) ,wenyanwenzuozhe varchar(10), wenyanwentext varchar(200)," +
                " wenyanwenzhushi varchar(250))");
        db.execSQL("create table xiandai_tb(xiandainame varchar(10) ,xiandaizuozhe varchar(10), " +
                " xiandaitext varchar(20845))");

    }
    //数据库版本更新
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {
        db.execSQL("drop table if exists gushi_tb");
        db.execSQL("drop table if exists wenyanwen_tb");

        onCreate(db);
    }


}
