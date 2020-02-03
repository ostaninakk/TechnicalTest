package ostaninakk.hightechnologiescentertechnicaltest.model;

import com.google.gson.annotations.SerializedName;

public class CompanyResponse {
    @SerializedName("company")
    private Company company;

    public Company getCompany() {
        return company;
    }
}
