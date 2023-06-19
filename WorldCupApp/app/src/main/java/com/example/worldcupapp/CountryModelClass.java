package com.example.worldcupapp;

public class CountryModelClass {
    private String countryName,winCount;
    private int flag_img;

    public CountryModelClass(String countryName, String winCount, int flag_img){
        this.countryName=countryName;
        this.winCount=winCount;
        this.flag_img=flag_img;

    }

    public String getCountryName() {
        return countryName;
    }

    public String getWinCount() {
        return winCount;
    }

    public int getFlag_img() {
        return flag_img;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setWinCount(String winCount) {
        this.winCount = winCount;
    }

    public void setFlag_img(int flag_img) {
        this.flag_img = flag_img;
    }
}
