package com.opencart.tools;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonDataConfig {


    private FileReader reader;
    private JSONArray userLoginArray;

    public JsonDataConfig(String fileName) {
        JSONParser jsPars = new JSONParser();
        reader = null;
        try {
            reader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Object obj = null;

        try {
            obj = jsPars.parse(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject userLoginsJsonObj = (JSONObject) obj;
        userLoginArray = (JSONArray) userLoginsJsonObj.get("userLogin");
    }


    public String getEmailFromJson(int userID) {
        String[] emailArray = new String[userLoginArray.size()];

        for (int i = 0; i < userLoginArray.size(); i++) {

            JSONObject usersLog = (JSONObject) userLoginArray.get(i);
            String email = (String) usersLog.get("email");

            emailArray[i] = email;
        }
        return emailArray[userID];
    }

    public String getPasswordFromJson(int userID) {
        String[] passwordArray = new String[userLoginArray.size()];

        for (int i = 0; i < userLoginArray.size(); i++) {

            JSONObject usersLog = (JSONObject) userLoginArray.get(i);
            String password = (String) usersLog.get("password");

            passwordArray[i] = password;
        }
        return passwordArray[userID];
    }

    public String getFirstNameFromJson(int userID) {
        String[] FirstNameArray = new String[userLoginArray.size()];

        for (int i = 0; i < userLoginArray.size(); i++) {

            JSONObject usersLog = (JSONObject) userLoginArray.get(i);
            String firstName = (String) usersLog.get("FirstName");

            FirstNameArray[i] = firstName;
        }
        return FirstNameArray[userID];
    }

    public String getLastNameFromJson(int userID) {
        String[] LastNameArray = new String[userLoginArray.size()];

        for (int i = 0; i < userLoginArray.size(); i++) {

            JSONObject usersLog = (JSONObject) userLoginArray.get(i);
            String LastName = (String) usersLog.get("LastName");

            LastNameArray[i] = LastName;
        }
        return LastNameArray[userID];
    }

    public String getAddressFromJson(int userID) {
        String[] AdddressArray = new String[userLoginArray.size()];

        for (int i = 0; i < userLoginArray.size(); i++) {

            JSONObject usersLog = (JSONObject) userLoginArray.get(i);
            String Address = (String) usersLog.get("Address");

            AdddressArray[i] = Address;
        }
        return AdddressArray[userID];
    }

    public String getCityFromJson(int userID) {
        String[] CityArray = new String[userLoginArray.size()];

        for (int i = 0; i < userLoginArray.size(); i++) {

            JSONObject usersLog = (JSONObject) userLoginArray.get(i);
            String City = (String) usersLog.get("City");

            CityArray[i] = City;
        }
        return CityArray[userID];
    }

    public String getCountryFromJson(int userID) {
        String[] CountryArray = new String[userLoginArray.size()];

        for (int i = 0; i < userLoginArray.size(); i++) {

            JSONObject usersLog = (JSONObject) userLoginArray.get(i);
            String Country = (String) usersLog.get("Country");

            CountryArray[i] = Country;
        }
        return CountryArray[userID];
    }

    public String getRegionFromJson(int userID) {
        String[] RegionArray = new String[userLoginArray.size()];

        for (int i = 0; i < userLoginArray.size(); i++) {

            JSONObject usersLog = (JSONObject) userLoginArray.get(i);
            String Region = (String) usersLog.get("Region");

            RegionArray[i] = Region;
        }
        return RegionArray[userID];
    }

    public String getTelephoneFromJson(int userID) {
        String[] telephoneArray = new String[userLoginArray.size()];

        for (int i = 0; i < userLoginArray.size(); i++) {

            JSONObject usersLog = (JSONObject) userLoginArray.get(i);
            String telephone = (String) usersLog.get("telephone");

            telephoneArray[i] = telephone;
        }
        return telephoneArray[userID];
    }
}
