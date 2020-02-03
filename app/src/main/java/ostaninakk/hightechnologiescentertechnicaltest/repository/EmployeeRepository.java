package ostaninakk.hightechnologiescentertechnicaltest.repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import ostaninakk.hightechnologiescentertechnicaltest.api.NetworkService;
import ostaninakk.hightechnologiescentertechnicaltest.model.Company;
import ostaninakk.hightechnologiescentertechnicaltest.model.CompanyResponse;
import ostaninakk.hightechnologiescentertechnicaltest.model.Employee;

public class EmployeeRepository {
    private static EmployeeRepository instance;

    public static EmployeeRepository getInstance(){
        if(instance == null){
            instance = new EmployeeRepository();
        }
        return instance;
    }


    private EmployeeRepository() {
    }

    public Flowable<List<Employee>> getEmployees(){
        return NetworkService
                .getInstance()
                .getDataApi()
                .getCompanyData()
                .onErrorReturn(new Function<Throwable, CompanyResponse>() {
                    @Override
                    public CompanyResponse apply(Throwable throwable) throws Exception {
                        return null;
                    }
                })
                .map(new Function<CompanyResponse, List<Employee>>() {
                         @Override
                         public List<Employee> apply(CompanyResponse companyResponse) throws Exception {
                             if (companyResponse != null) {
                                 Company company = companyResponse.getCompany();
                                 if (company != null) {
                                     List<Employee> employeeList = company.getEmployees();
                                     return sortEmployeeListByAlphabet(employeeList);
                                 }
                             }
                             return null;
                         }
                     }

                )
                .subscribeOn(Schedulers.io());
    }

    private List<Employee> sortEmployeeListByAlphabet(List<Employee> employeeList) {
        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return e1.getName().compareTo(e2.getName());
            }
        });
        return employeeList;
    }
}
