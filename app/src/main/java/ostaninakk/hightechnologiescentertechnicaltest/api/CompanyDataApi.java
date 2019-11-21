package ostaninakk.hightechnologiescentertechnicaltest.api;

import ostaninakk.hightechnologiescentertechnicaltest.model.CompanyResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CompanyDataApi {
    @GET("v2/56fa31e0110000f920a72134")
    public Call<CompanyResponse> getCompanyData();
}
