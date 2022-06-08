package com.example.anhntph09707_assignment.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLite  extends SQLiteOpenHelper {

    //tao bang student
    public  static  final String SQL_STUDENT= "CREATE TABLE STUDENT(id INTEGER PRIMARY KEY AUTOINCREMENT,name text,birthday text)";
    //tao bang classroom
    public  static  final String SQL_CLASSROOM= "CREATE TABLE CLASSROOM("+
            "id text PRIMARY KEY," +
            "name text)";

    //tao database quan ly sinh vien
    public SQLite( Context context) {

            super(context,"STUDENTMANAGE.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_STUDENT);//lệnh tạo bảng Product
        sqLiteDatabase.execSQL(SQL_CLASSROOM);//lệnh tạo bảng classroom
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS STUDENT");//xóa bảng cũ, tạo bảng mới
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CLASSROOM");//xóa bảng cũ, tạo bảng mới

    }
}
