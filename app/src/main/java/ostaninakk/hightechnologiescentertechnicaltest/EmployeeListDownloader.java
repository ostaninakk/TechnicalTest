package ostaninakk.hightechnologiescentertechnicaltest;

import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import ostaninakk.hightechnologiescentertechnicaltest.api.CompanyDataApi;
import ostaninakk.hightechnologiescentertechnicaltest.model.Company;
import ostaninakk.hightechnologiescentertechnicaltest.model.CompanyResponse;
import ostaninakk.hightechnologiescentertechnicaltest.model.Employee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeListDownloader {
    private static final String LOG_TAG = "EMPLOYEE_LIST_LOG";
    /* В тестовом задании можно не использовать LiveData(),
     * так как, вероятнее всего, подразумевается, что данные не меняются,
     * но с целью расширяемости сделала с отслеживаем изменений */
    private MutableLiveData<List<Employee>> responseLiveData = new MutableLiveData();

    public LiveData<List<Employee>> getEmployees() {
        CompanyDataApi dataApi = NetworkService.getInstance().getDataApi();

        Call<CompanyResponse> request = dataApi.getCompanyData();
        request.enqueue(new Callback<CompanyResponse>() {
            @Override
            public void onResponse(Call<CompanyResponse> call, Response<CompanyResponse> response) {
                CompanyResponse companyResponse = response.body();
                if (companyResponse != null) {
                    Company company = companyResponse.getCompany();
                    if (company != null) {
                        List<Employee> employeeList = company.getEmployees();
                        // Сортировать список работников по алфавиту
                        Collections.sort(employeeList, new Comparator<Employee>() {
                            @Override
                            public int compare(Employee e1, Employee e2) {
                                return e1.getName().compareTo(e2.getName());
                            }
                        });

                        responseLiveData.setValue(employeeList);
                    }
                }
            }

            @Override
            public void onFailure(Call<CompanyResponse> call, Throwable t) {
                Log.e(LOG_TAG, "Failed get data", t);
            }
        });

        return responseLiveData;
    }
}
