package com.example.cov_eid;

public class HomeModel {
    Integer langLogo;
    String langName;

    public HomeModel(Integer langLogo, String langName){
        this.langLogo = langLogo;
        this.langName = langName;
    }

    public Integer getLangLogo(){
        return langLogo;
    }

    public String getLangName(){
        return langName;
    }
}
