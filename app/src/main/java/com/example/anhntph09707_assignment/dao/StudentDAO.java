package com.example.anhntph09707_assignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.anhntph09707_assignment.database.SQLite;
import com.example.anhntph09707_assignment.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private SQLiteDatabase db;
    private SQLite dbhelper;
    private Context context;

    public StudentDAO(Context context) {
        this.context = context;
        this.dbhelper = new SQLite(context);
        this.db= dbhelper.getReadableDatabase();
    }

    //insert du lieu
    public  int InsertStudent(Student student){
        ContentValues contentValues = new ContentValues();//doi tuong chua du lieu
        //dua du lieu vao doi tuong chua
        contentValues.put("id",student.getId());
        contentValues.put("name",student.getName());
        contentValues.put("birthday",student.getBirthday());
        //insert
        long kq = db.insert("student",null,contentValues);
        //check
        if(kq<=0){
            return -1;//insert that bai
        }
        return 1;
    }
    public List<String> getAllStudent(){
        List<String> listStudent = new ArrayList<>();
        Cursor c = db.query("student",null
        ,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            Student student = new Student();
            student.setId(c.getString(0));
            student.setName(c.getString(1));
            student.setBirthday(c.getString(2));
            String chuoi=
                    student.getName()+"                 -                  "+
                    student.getBirthday();
            listStudent.add(chuoi);
            c.moveToNext();
        }
        c.close();
        return listStudent;
    }

    }

