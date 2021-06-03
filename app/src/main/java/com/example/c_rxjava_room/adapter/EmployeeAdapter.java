package com.example.c_rxjava_room.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.c_rxjava_room.R;
import com.example.c_rxjava_room.database.Employee;

import org.w3c.dom.Text;

import java.util.List;

public class EmployeeAdapter extends BaseAdapter {
    private Context mContext;
    private List<Employee> employeeList;
    private LayoutInflater mLayoutInflater;

    public EmployeeAdapter(Context mContext, List<Employee> employeeList) {
        this.mContext = mContext;
        this.employeeList = employeeList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return employeeList.size();
    }

    @Override
    public Object getItem(int position) {
        return employeeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        //如果View為空設定holder並且抓取id
        EmployeeHolder employeeHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_user, null);
            employeeHolder = new EmployeeHolder();
            employeeHolder.firstName = convertView.findViewById(R.id.tvFirstName);
            employeeHolder.lastName = convertView.findViewById(R.id.tvLastName);
            convertView.setTag(employeeHolder); //設置ViewHolder的View標籤
        } else {
            employeeHolder = (EmployeeHolder) convertView.getTag();
        }


        //取得item時做畫面
        Employee employee = (Employee) getItem(position);
        employeeHolder.firstName.setText(employee.getFirstname());
        employeeHolder.lastName.setText(employee.getLastname());
        return convertView;
    }


    //創建ViewHolder
    private class EmployeeHolder {
        TextView firstName, lastName;
    }
}
