package ostaninakk.hightechnologiescentertechnicaltest;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import ostaninakk.hightechnologiescentertechnicaltest.model.Employee;

public class ListFragment extends Fragment {
    private ListView listView;
    private EmployeeListViewModel employeeListViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Загрузить данные работников один раз при создании фрагмента (
         * (использовать ViewModel, чтобы данные при повороте экрана не грузились повторно) */
        employeeListViewModel = ViewModelProviders.of(this).get(EmployeeListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = view.findViewById(R.id.list_view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Подписаться на изменение загружаемых объектов
        employeeListViewModel.getEmployeeLiveData().observe(this,
                new Observer<List<Employee>>() {
                    @Override
                    public void onChanged(List<Employee> employees) {
                        listView.setAdapter(new EmployeeAdapter(employees));
                    }
                }
        );
    }

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    private class EmployeeAdapter extends BaseAdapter {
        private List<Employee> employeeList;

        public EmployeeAdapter(List<Employee> employeeList) {
            this.employeeList = employeeList;
        }


        @Override
        public int getCount() {
            return employeeList.size();
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
                view = getLayoutInflater().inflate(R.layout.list_item, viewGroup, false);
            }

            // Имя
            TextView nameTextView = view.findViewById(R.id.name);
            nameTextView.setText(employeeList.get(i).getName());

            // Номер телефона
            TextView phoneTextView = view.findViewById(R.id.phone_number);
            phoneTextView.setText(employeeList.get(i).getPhoneNumber());

            // Компетенции
            TextView skillsTextView = view.findViewById(R.id.skills);
            List<String> skills = employeeList.get(i).getSkills();
            skillsTextView.setText(TextUtils.join(", ", skills));

            return view;
        }
    }
}
