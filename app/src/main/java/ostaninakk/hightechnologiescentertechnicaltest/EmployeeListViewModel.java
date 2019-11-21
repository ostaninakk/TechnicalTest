package ostaninakk.hightechnologiescentertechnicaltest;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import ostaninakk.hightechnologiescentertechnicaltest.model.Employee;

public class EmployeeListViewModel extends ViewModel {
    private LiveData<List<Employee>> employeeLiveData;

    public EmployeeListViewModel() {
        employeeLiveData = new EmployeeListDownloader().getEmployees();
    }

    public LiveData<List<Employee>> getEmployeeLiveData() {
        return employeeLiveData;
    }
}
