package ostaninakk.hightechnologiescentertechnicaltest.ui;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ostaninakk.hightechnologiescentertechnicaltest.R;
import ostaninakk.hightechnologiescentertechnicaltest.model.Employee;

public class EmployeeAdapter extends BaseAdapter {
    private List<Employee> employeeList;
    private Activity activity;

    public EmployeeAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        if (employeeList != null) {
            return employeeList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return employeeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.list_item, viewGroup, false);
        }

        TextView nameTextView = view.findViewById(R.id.name);
        TextView phoneTextView = view.findViewById(R.id.phone_number);
        TextView skillsTextView = view.findViewById(R.id.skills);


        nameTextView.setText(employeeList.get(i).getName());
        phoneTextView.setText(employeeList.get(i).getPhoneNumber());
        List<String> skills = employeeList.get(i).getSkills();
        skillsTextView.setText(TextUtils.join(", ", skills));

        return view;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
        notifyDataSetChanged();
    }
}