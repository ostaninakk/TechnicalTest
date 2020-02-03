package ostaninakk.hightechnologiescentertechnicaltest.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import ostaninakk.hightechnologiescentertechnicaltest.model.Employee;
import ostaninakk.hightechnologiescentertechnicaltest.repository.EmployeeRepository;
import ostaninakk.hightechnologiescentertechnicaltest.util.Resource;

public class EmployeeViewModel extends ViewModel {

    private MediatorLiveData<Resource<List<Employee>>> employeeLiveData = new MediatorLiveData<>();
    private EmployeeRepository repository = EmployeeRepository.getInstance();


    public LiveData<Resource<List<Employee>>> getEmployees(){
        employeeLiveData.setValue(Resource.loading((List<Employee>) null));

        final LiveData<Resource<List<Employee>>> source = LiveDataReactiveStreams.fromPublisher(
                repository.getEmployees()
                        .onErrorReturn(new Function<Throwable, List<Employee>>() {
                            @Override
                            public List<Employee> apply(Throwable throwable) throws Exception {
                                return new ArrayList<>();
                            }
                        })

                        .map(new Function<List<Employee>, Resource<List<Employee>>>() {
                            @Override
                            public Resource<List<Employee>> apply(List<Employee> employeeList) throws Exception {
                                if(employeeList.isEmpty()){
                                    return Resource.error(null, "Something went wrong");
                                }
                                return Resource.success(employeeList, "");
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );

        employeeLiveData.addSource(source, new Observer<Resource<List<Employee>>>() {
            @Override
            public void onChanged(Resource<List<Employee>> resource) {
                employeeLiveData.setValue(resource);
                employeeLiveData.removeSource(source);
            }
        });

        return employeeLiveData;
    }
}

