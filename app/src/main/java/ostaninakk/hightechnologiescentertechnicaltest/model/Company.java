package ostaninakk.hightechnologiescentertechnicaltest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Company {
    @SerializedName("employees")
    private List<Employee> employees = null;

    public List<Employee> getEmployees() {
        return employees;
    }

}