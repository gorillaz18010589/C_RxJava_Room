package com.example.c_rxjava_room.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Employee.class, version = 1)
public abstract class EmployeeDataBase extends RoomDatabase {
    public abstract EmployeeDao getEmployeeDao(); //變形方法,取得EmployeeDao實作
}
