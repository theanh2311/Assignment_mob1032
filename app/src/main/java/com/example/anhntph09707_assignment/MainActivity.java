package com.example.anhntph09707_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button btnAddClass,btnViewClass,btnStudentManage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddClass= findViewById(R.id.btn_addClass);
        btnStudentManage= findViewById(R.id.btn_studentManager);
        btnViewClass = findViewById(R.id.btn_viewListClass);
        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddClassActivity.class);
                startActivity(i);
            }
        });
        btnViewClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ViewClassListActivity.class);
                startActivity(intent);

            }
        });
        btnStudentManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,StudentManageActivity.class);
                startActivity(i);
            }
        });
    }
}