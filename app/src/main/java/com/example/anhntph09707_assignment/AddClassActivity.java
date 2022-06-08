package com.example.anhntph09707_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anhntph09707_assignment.dao.ClassRoomDAO;
import com.example.anhntph09707_assignment.model.ClassRoom;

public class AddClassActivity extends AppCompatActivity {
    //khai báo
EditText edtID,edtName;
Button btnEmpty,btnAddClass;
ClassRoomDAO classRoomDAO ;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        //ánh  xạ
        edtID = findViewById(R.id.edt_id_class);
        edtName = findViewById(R.id.edt_name_class);
        btnAddClass = findViewById(R.id.btn_add_class);
        btnEmpty = findViewById(R.id.btn_empty);
        context=this;
        classRoomDAO = new ClassRoomDAO(context);//tạo đối tượng DAO để call với database
        btnEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtID.setText("");//xóa trắng các dòng
                edtName.setText("");
            }
        });
        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClassRoom c = new ClassRoom();//tạo đối tượng classroom
                c.setIdClass(edtID.getText().toString());//set id
                c.setName(edtName.getText().toString());//set tên
                int kq = classRoomDAO.InsertClassRoom(c); // thêm đối tượng vào database
                if (kq==-1){
                    Toast.makeText(context,"Insert thất bại",Toast.LENGTH_LONG).show();
                }
                if(kq==1)
                {
                    Toast.makeText(context,"Insert thành công",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}