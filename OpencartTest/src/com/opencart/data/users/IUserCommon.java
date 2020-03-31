package com.opencart.data.users;

public interface IUserCommon extends IFirstName, ILastName, IEmail, ITelephone,
        IPassword, IMainAddress, ICity,
        ICounty, IRegion, IBuildUser {
}

interface IFirstName {
    ILastName setFirstName(String firstName);
}

interface ILastName {
    IEmail setLastName(String lastName);
}

interface IEmail {
    ITelephone setEmail(String email);
}

interface ITelephone {
    IPassword setTelephone(String telephone);
}

interface IPassword {
    IMainAddress setPassword(String password);
}

interface IMainAddress {
    ICity setMainAddress(String mainAddress);
}

interface ICity {
    ICounty setCity(String city);
}

interface ICounty {
    IRegion setCounty(String county);
}

interface IRegion {
    IBuildUser setRegion(String region);
}

interface IBuildUser {
    IBuildUser setOptionalCompany(String optionalCompany);

    IBuildUser setOptionalAddress(String optionalAddress);

    IBuildUser setOptionalPostCode(String optionalPostCode);

    User build();
}