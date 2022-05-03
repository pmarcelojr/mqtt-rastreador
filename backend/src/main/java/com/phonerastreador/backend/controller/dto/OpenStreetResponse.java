package com.phonerastreador.backend.controller.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenStreetResponse implements Serializable {

    @JsonProperty("display_name")
    private String displayName;
    private OpenStreetAddress address;

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public OpenStreetAddress getAddress() {
        return this.address;
    }

    public void setAddress(OpenStreetAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "{" +
                " displayName='" + getDisplayName() + "'" +
                ", address='" + getAddress() + "'" +
                "}";
    }

}
