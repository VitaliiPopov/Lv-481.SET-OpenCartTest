package com.opencart.tools;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencart.data.users.CustomUser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonDataConfig {

    private List<CustomUser> listUser;

    public JsonDataConfig(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        listUser = null;
        try {
            listUser = objectMapper.readValue(new File(fileName), new TypeReference<List<CustomUser>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getEmailFromJson(int user_index) {
        return listUser.get(user_index).getEmail();
    }

    public String getPasswordFromJson(int user_index) {
        return listUser.get(user_index).getPassword();
    }

    public String getFirstNameFromJson(int user_index) {
        return listUser.get(user_index).getFirstName();
    }

    public String getLastNameFromJson(int user_index) {
        return listUser.get(user_index).getLastName();
    }

    public String getTelephoneFromJson(int user_index) {
        return listUser.get(user_index).getTelephone();
    }

    public String getAddressFromJson(int user_index) {
        return listUser.get(user_index).getAddress();
    }

    public String getCityFromJson(int user_index) {
        return listUser.get(user_index).getCity();
    }

    public String getCountryFromJson(int user_index) {
        return listUser.get(user_index).getCountry();
    }

    public String getRegionFromJson(int user_index) {
        return listUser.get(user_index).getRegion();
    }

    public CustomUser getUserFromJson(int user_index) {
        return listUser.get(user_index);
    }

}