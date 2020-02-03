package ostaninakk.hightechnologiescentertechnicaltest.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import ostaninakk.hightechnologiescentertechnicaltest.R;
import ostaninakk.hightechnologiescentertechnicaltest.model.Employee;
import ostaninakk.hightechnologiescentertechnicaltest.util.Resource;
import ostaninakk.hightechnologiescentertechnicaltest.viewmodel.EmployeeViewModel;

public class ListFragment extends Fragment {
    private EmployeeAdapter adapter;
    private EmployeeViewModel employeeListViewModel;
    private ProgressBar progressBar;
    private LinearLayout errorSection;
    private Button refresh;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initListView(view);

        employeeListViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        subscribeObservers();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employeeListViewModel.getEmployees();
            }
        });
    }

    private void initView(View view) {
        progressBar = view.findViewById(R.id.progress_bar);
        errorSection = view.findViewById(R.id.error_not_found);
        refresh = view.findViewById(R.id.refresh);
    }

    private void initListView(View view) {
        ListView listView = view.findViewById(R.id.list_view);
        adapter = new EmployeeAdapter(getActivity());
        listView.setAdapter(adapter);
    }

    private void subscribeObservers() {
        employeeListViewModel.getEmployees().removeObservers(getViewLifecycleOwner());
        employeeListViewModel.getEmployees().observe(getViewLifecycleOwner(), new Observer<Resource<List<Employee>>>() {
            @Override
            public void onChanged(Resource<List<Employee>> resource) {
                if(resource != null){
                    switch (resource.status){
                        // Show progress bar
                        case LOADING:{
                            showError(false);
                            showProgressBar(true);
                            break;
                        }
                        // Show data
                        case SUCCESS:{
                            showProgressBar(false);
                            showError(false);

                            adapter.setEmployeeList(resource.data);
                            break;
                        }
                        // Show error
                        case ERROR:{
                            showProgressBar(false);
                            showError(true);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void showProgressBar(boolean visibility){
        progressBar.setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
    }

    private void showError(boolean visibility){
        errorSection.setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
    }
}
