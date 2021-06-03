package com.example.c_rxjava_room.remote;

import com.example.c_rxjava_room.database.Employee;

import java.util.List;

//5.創一個DataBaseCallBack(interface),資料取得成功方法,資料失敗方法
public interface DataBaseCallBack {
    void dataAdded(); //當insert成功時接口
    void errorAdded(); //當insert失敗時接口
    void loadEmployeeData(List<Employee> employeeList); //讀取資料,
}
