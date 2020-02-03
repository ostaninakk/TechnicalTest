package ostaninakk.hightechnologiescentertechnicaltest.api;

import io.reactivex.Flowable;
import ostaninakk.hightechnologiescentertechnicaltest.model.CompanyResponse;
import retrofit2.http.GET;

public interface CompanyDataApi {
    @GET("v2/56fa31e0110000f920a72134")
    Flowable<CompanyResponse> getCompanyData();
}
