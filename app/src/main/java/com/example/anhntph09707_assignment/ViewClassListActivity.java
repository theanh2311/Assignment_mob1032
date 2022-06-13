package com.example.anhntph09707_assignment;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.anhntph09707_assignment.dao.ClassRoomDAO;

import java.util.ArrayList;
import java.util.List;

public class ViewClassListActivity extends AppCompatActivity {
    // khai bao
ListView listView;
ArrayAdapter<String> arrayAdapter;
List<String> list = new ArrayList<>();
Context context;
int itemPosition=0;//tạo biến toàn cục để lấy vị trí item
String textItem,subTextItem;
ClassRoomDAO classRoomDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_class_list);
        //ánh xạ
        listView = findViewById(R.id.lv_classroom);
        context = this;
        //clear list cũ ,tạo list mới và cập nhật lên listview khi khởi động activity
        list.clear();
        classRoomDAO = new ClassRoomDAO(context);
        list = classRoomDAO.getAllClass();
        arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);
        //đăng kí context menu cho listview
        registerForContextMenu(listView);
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            itemPosition = position; //gán giá trị biến = vị trí item khi click
        });
     }

     //tạo context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu (menu, v, menuInfo);
        menu.add(Menu.NONE,0,0,"Xóa"); //tạo chức năng xóa item

    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
       // AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        if (item.getItemId()==0){

                try {
                    //lấy all chuỗi tại item mình chọn
                    textItem = (String)listView.getItemAtPosition(itemPosition);
                    //tách chuỗi vừa lấy dc,lấy từ đầu đến trước space (id class)
                    subTextItem = textItem.substring(0, textItem.indexOf(" "));
                    //xóa lớp trong database = id vừa tách dc
                    classRoomDAO.deleteClass(subTextItem);
                    //xóa item trên list = vị trí item
                    list.remove(itemPosition);
                    Toast.makeText(ViewClassListActivity.this,"Deleted Class:"+subTextItem,Toast.LENGTH_LONG).show();
                    arrayAdapter.notifyDataSetChanged();//cập nhật adapter mới lên listview khi có thay đổi
                }catch (Exception ex){
                    Toast.makeText(ViewClassListActivity.this,
                            "Cannot Delete",Toast.LENGTH_LONG).show();
                }

        }
        return super.onContextItemSelected(item);
    }
}