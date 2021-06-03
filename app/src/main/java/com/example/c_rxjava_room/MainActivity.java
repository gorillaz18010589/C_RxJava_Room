package com.example.c_rxjava_room;
//1.引入api
//2.創建三個package,區分remotem,database,adapter
//3.創建Employee(員工)bean,表格化Entity
//4.EmployeeDao,接口,Db指令轉換
//5.創建EmployeeDataBase

//remote->
//5.創一個DataBaseCallBack(interface)
//6.DataBaseManger創建在這邊初始化創建EmployeeDataBase
//7.回到Activity去時做
//11.處理CustomAdapter
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.c_rxjava_room.adapter.EmployeeAdapter;
import com.example.c_rxjava_room.database.Employee;
import com.example.c_rxjava_room.remote.DataBaseCallBack;
import com.example.c_rxjava_room.remote.DataBaseManger;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DataBaseCallBack {
    private DataBaseManger dataBaseManger;
    private Button btnSendData, btnGetData;
    private ListView listView;
    private EmployeeAdapter employeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnGetData = findViewById(R.id.btnGetData);
        btnSendData = findViewById(R.id.btnSendData);
        listView = findViewById(R.id.listView);
        dataBaseManger = new DataBaseManger(this);


        //按下按鈕insert資料入Db
        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseManger.addData(MainActivity.this,"jason","kidd");
            }
        });

        //按下資料取得Db資料
        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseManger.loadData(MainActivity.this); //DataBaseCallBack
            }
        });

    }

    //8.當Employee成功insert到Db時
    @Override
    public void dataAdded() {
        showToast("Data add!");
    }

    //9.當Employee insert到Db失敗時
    @Override
    public void errorAdded() {
        showToast("Data Fail!");
    }

    //10.讀取資料成功時query,將資料帶入條便器
    @Override
    public void loadEmployeeData(List<Employee> employeeList) {
        employeeAdapter = new EmployeeAdapter(this,employeeList);
        listView.setAdapter(employeeAdapter);
    }

    void  showToast(String msg){
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
}