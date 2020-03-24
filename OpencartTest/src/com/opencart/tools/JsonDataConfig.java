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
}