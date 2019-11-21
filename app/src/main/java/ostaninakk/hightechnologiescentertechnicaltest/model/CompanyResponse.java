package ostaninakk.hightechnologiescentertechnicaltest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ostaninakk.hightechnologiescentertechnicaltest.model.Company;

public class CompanyResponse {
    @SerializedName("company")
    @Expose
    private Company company;

    public Company getCompany() {
        return company;
    }
}
