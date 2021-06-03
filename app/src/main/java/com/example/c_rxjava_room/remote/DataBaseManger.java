package com.example.c_rxjava_room.remote;

//6.DataBaseManger創建在這邊初始化創建EmployeeDataBase


import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.c_rxjava_room.database.Employee;
import com.example.c_rxjava_room.database.EmployeeDataBase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class DataBaseManger {
    private EmployeeDataBase employeeDataBase;
    private Context mContent;

    //A.建構式去初始化Db建資料庫,並取得EmployeeDataBase
    public DataBaseManger(Context context) {
        this.mContent = context;
        this.employeeDataBase = Room.databaseBuilder(context, EmployeeDataBase.class, "employee.db").build();
    }

    //B.AddData
    public void addData(final DataBaseCallBack dataBaseCallBack, final String firstname, String lastname) {


        //C.時做註冊者與訂閱,CompletableObserver-> 只收到結果,不對數據進行處理
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                //D.執行續要做的事,用執行續將firstNamem,lastName 用Dao insert到Db
                Employee employee = new Employee(firstname, lastname);
                employeeDataBase.getEmployeeDao().insertEmployee(employee); //將員工資料insert到Db
                Log.v("hank", "DataBaseManger -> addData Completable.fromAction ->" + "fistName:" + firstname + "/lastName:" + lastname);

            }
        }).observeOn(AndroidSchedulers.mainThread()) //指定观察者处理事件的线程，每指定一次就会生效一次
                .subscribeOn(Schedulers.io()) //指定被观察者事件发送的线程，如果多次调用此方法，只有第一次有效
                .subscribe(new CompletableObserver() { //訂閱（subscribe） 關聯被觀察者和觀察者
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.v("hank", "DataBaseManger -> addData onSubscribe()");
                    }

                    @Override
                    public void onComplete() {
                        //資料新增成功
                        Log.v("hank", "DataBaseManger -> addData onComplete()");
                        dataBaseCallBack.dataAdded();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //資料新增失敗
                        Log.v("hank", "DataBaseManger ->addData  onError()");
                        dataBaseCallBack.errorAdded();
                    }
                });


    }


    //C.loadData
    public void loadData(DataBaseCallBack dataBaseCallBack){
        employeeDataBase.getEmployeeDao().getEmployee() //取得Employee的DB資料
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Employee>>() {
                    @Override
                    public void accept(List<Employee> employeeList) throws Exception {
                        //取得Db資料成功時
                        Log.v("hank", "DataBaseManger loadData -> employeeDataBase.getEmployeeDao().getEmployee()" + employeeList.get(0).getFirstname());
                        dataBaseCallBack.loadEmployeeData(employeeList);
                    }
                });
    }
}
