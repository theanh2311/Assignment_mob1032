package com.example.anhntph09707_assignment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.anhntph09707_assignment.MainActivity;
import com.example.anhntph09707_assignment.database.SQLite;
import com.example.anhntph09707_assignment.model.ClassRoom;
import com.example.anhntph09707_assignment.model.Student;

import java.util.ArrayList;
import java.util.List;

public class ClassRoomDAO {
    public static final String TAG = MainActivity.class.getSimpleName();
    private SQLiteDatabase db;
    private SQLite dbhelper;
    private Context context;

    public ClassRoomDAO(Context context) {
        this.context = context;
        dbhelper = new SQLite(context);
        db= dbhelper.getWritableDatabase();
    }

    //insert du lieu
    public  int InsertClassRoom(ClassRoom classRoom){
        ContentValues contentValues = new ContentValues();//doi tuong chua du lieu
        //dua du lieu vao doi tuong chua
        contentValues.put("id",classRoom.getIdClass());
        contentValues.put("name",classRoom.getName());
        //insert
        long kq = db.insert("classroom",null,contentValues);
        //check
        if(kq<=0){
            Log.e(TAG,"insert failed");
            return -1;//insert that bai
        }
        return 1; //insert thanh cong
    }
    public List<String> getAllClass(){
        List<String> listClass = new ArrayList<>();//tao list moi
        Cursor c = db.query("classroom",null //truy van
                ,null,null,null,null,null);
        c.moveToFirst(); // chuyen con tro ve vi tri dau tien
        while (c.isAfterLast()==false){//neu chưa phải dòng cuối,lấy tiếp dữ liệu
           ClassRoom classRoom = new ClassRoom(); // tạo dối tượng classroom mới
            classRoom.setIdClass(c.getString(0)); //set các trường cho đối tượng = kq vừa truy vấn dc
            classRoom.setName(c.getString(1));
            //tạo chuỗi để truyền vào list
            String chuoi= classRoom.getIdClass() + "                           -                            "+
                    classRoom.getName();
            //truyền chuỗi vào list
            listClass.add(chuoi);
            c.moveToNext();//di chuyển con trỏ đến dòng tiếp theo
        }
        c.close();//đóng con trỏ
        return listClass; //trả về danh sách class
    }
    public int deleteClass(String id){  //delete class bằng id
        int kq = db.delete("classroom","id=?",//truy vấn
                new String[]{id});
        if (kq<=0){
        return -1;//thất bại
        }
        return 1;//thành công
    }
    public List<String> getNameClass() {
        List<String> listClass = new ArrayList<>();
        Cursor c = db.query("classroom", null
                , null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            ClassRoom classRoom = new ClassRoom();
            classRoom.setName(c.getString(1));
            String chuoiTen =
                    classRoom.getName();
            listClass.add(chuoiTen);
            c.moveToNext();
        }
        c.close();
        return listClass;
    }
}
