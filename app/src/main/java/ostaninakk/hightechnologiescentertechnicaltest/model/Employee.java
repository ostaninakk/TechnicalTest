package ostaninakk.hightechnologiescentertechnicaltest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Employee {
    @SerializedName("name")
    private String name;

    @SerializedName("phone_number")
    private String phoneNumber;

    @SerializedName("skills")
    private List<String> skills = null;

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<String> getSkills() {
        return skills;
    }
}
