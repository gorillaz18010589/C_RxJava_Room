package com.example.c_rxjava_room.database;



import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

//4.EmployeeDao,接口,Db指令轉換

@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM employee")
    Flowable<List<Employee>> getEmployee();

    @Insert()
    void insertEmployee(Employee employee);

}
