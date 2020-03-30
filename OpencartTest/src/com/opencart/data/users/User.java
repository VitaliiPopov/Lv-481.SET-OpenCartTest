package com.opencart.data.users;

public class User implements IUser, IUserCommon {

    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String password;
    private String optionalCompany;
    private String mainAddress;
    private String optionalAddress;
    private String city;
    private String optionalPostCode;
    private String county;
    private String region;

    private User() {
        optionalCompany = new String("");
        optionalAddress = new String("");
        optionalPostCode = new String("");
    }

    public static IFirstName get() {
        return new User();
    }

    public User build() {
        return this;
    }

    //region Setters
    public ILastName setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public IEmail setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ITelephone setEmail(String email) {
        this.email = email;
        return this;
    }

    public IPassword setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public IMainAddress setPassword(String password) {
        this.password = password;
        return this;
    }

    public IBuildUser setOptionalCompany(String optionalCompany) {
        this.optionalCompany = optionalCompany;
        return this;
    }

    public ICity setMainAddress(String mainAddress) {
        this.mainAddress = mainAddress;
        return this;
    }

    public IBuildUser setOptionalAddress(String optionalAddress) {
        this.optionalAddress = optionalAddress;
        return this;
    }

    public ICounty setCity(String city) {
        this.city = city;
        return this;
    }

    public IBuildUser setOptionalPostCode(String optionalPostCode) {
        this.optionalPostCode = optionalPostCode;
        return this;
    }

    public IRegion setCounty(String county) {
        this.county = county;
        return this;
    }

    public IBuildUser setRegion(String region) {
        this.region = region;
        return this;
    }
    //endregion

    //region Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPassword() {
        return password;
    }

    public String getOptionalCompany() {
        return optionalCompany;
    }

    public String getMainAddress() {
        return mainAddress;
    }

    public String getOptionalAddress() {
        return optionalAddress;
    }

    public String getCity() {
        return city;
    }

    public String getOptionalPostCode() {
        return optionalPostCode;
    }

    public String getCounty() {
        return county;
    }

    public String getRegion() {
        return region;
    }
    //endregion
}