package com.example.anhntph09707_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anhntph09707_assignment.dao.ClassRoomDAO;
import com.example.anhntph09707_assignment.dao.StudentDAO;
import com.example.anhntph09707_assignment.model.ClassRoom;
import com.example.anhntph09707_assignment.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentManageActivity extends AppCompatActivity {
    //khai báo
    EditText edtBirthday,edtName;
    Button btnAddStudent;
    Spinner spinner;
    StudentDAO studentDAO ;
    ClassRoomDAO classRoomDAO;
    Context context;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    List<String> list = new ArrayList<>();
    List<String> listClass = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_manage);
        //ánh xạ
        edtBirthday = findViewById(R.id.edt_birthday);
        edtName = findViewById(R.id.edt_name_student);
        spinner = findViewById(R.id.spinner);
        listView = findViewById(R.id.lv_student);
        context=this;
        btnAddStudent  = findViewById(R.id.btn_add_student);
        classRoomDAO = new ClassRoomDAO(context);//tạo mới đói tượng
        listClass.clear();//xóa list cũ
        listClass = classRoomDAO.getNameClass();//lấy list tên các lớp
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listClass);//truyền list tên các lớp vào adapter
        spinner.setAdapter(spinnerAdapter);//set adapter cho spinner
        studentDAO = new StudentDAO(context);
        list.clear(); //clear list
        list = studentDAO.getAllStudent();// lấy danh sách sv truyền vào list
        arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,list);//tạo adapter với list danh sách sv
        listView.setAdapter(arrayAdapter); // truyền adapter vào listview
        arrayAdapter.notifyDataSetChanged();//cập nhật adapter khi có thay đổi
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student s = new Student();//tạo đối tượng mới
                s.setName(edtName.getText().toString());//set tên,sinh nhật cho đối tượng
                s.setBirthday(edtBirthday.getText().toString());
                int kq = studentDAO.InsertStudent(s);//thêm đối tượng vào DB
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