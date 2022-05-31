package com.phonerastreador.backend.controller.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenStreetAddress implements Serializable {
    private String road;
    private String suburb;
    private String city;
    private String state;
    private String postcode;
    private String country;
    @JsonProperty("country_code")
    private String countryCode;

    public String getRoad() {
        return this.road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getSuburb() {
        return this.suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "{" +
                " road='" + getRoad() + "'" +
                ", suburb='" + getSuburb() + "'" +
                ", city='" + getCity() + "'" +
                ", state='" + getState() + "'" +
                ", postcode='" + getPostcode() + "'" +
                ", country='" + getCountry() + "'" +
                ", countryCode='" + getCountryCode() + "'" +
                "}";
    }

}
