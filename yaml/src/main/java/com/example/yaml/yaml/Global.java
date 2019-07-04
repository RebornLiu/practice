package com.example.yaml.yaml;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Configuration
@Component("global")
@ConfigurationProperties(prefix = "global")
public class Global {
    List<String> cities;
    Map<String, String> people;
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public Map<String, String> getPeople() {
        return people;
    }

    public void setPeople(Map<String, String> people) {
        this.people = people;
    }
}
