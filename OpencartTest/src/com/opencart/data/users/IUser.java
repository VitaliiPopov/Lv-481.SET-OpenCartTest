package com.opencart.data.users;

public interface IUser {
    String getFirstName();

    String getLastName();

    String getEmail();

    String getTelephone();

    String getPassword();

    String getOptionalCompany();

    String getMainAddress();

    String getOptionalAddress();

    String getCity();

    String getOptionalPostCode();

    String getCounty();

    String getRegion();
}