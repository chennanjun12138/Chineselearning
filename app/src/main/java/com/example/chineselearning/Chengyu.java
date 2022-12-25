package com.example.chineselearning;

public class Chengyu {

    private String chengyuname;
    private String chengyupingyin;
    private String chengyuyisi;
    private String chengyudiangu;

    public Chengyu() {
    }
    public Chengyu(String chengyuname, String chengyupingyin, String chengyuyisi,String chengyudiangu) {
        this.chengyuname = chengyuname;
        this.chengyupingyin = chengyupingyin;
        this.chengyuyisi = chengyuyisi;
        this.chengyudiangu= chengyudiangu;

    }
    public String getChengyuname() {
        return chengyuname;
    }

    public void setChengyuname(String chengyuname) {
        this.chengyuname = chengyuname;
    }

    public String getChengyupingyin() {
        return chengyupingyin;
    }

    public void setChengyupingyin(String chengyupingyin) {
        this.chengyupingyin = chengyupingyin;
    }

    public String getChengyuyisi() {
        return chengyuyisi;
    }

    public void setChengyuyisi(String chengyuyisi) {
        this.chengyuyisi = chengyuyisi;
    }

    public String getChengyudiangu() {
        return chengyudiangu;
    }

    public void setChengyudiangu(String chengyudiangu) {
        this.chengyudiangu = chengyudiangu;
    }

}
