package com.example.chineselearning;
public class User {

    private int id;
    private String userAccount;
    private String userPassword;
    private int userjifeng;
    public User() {
    }
    public User(int id, String userAccount, String userPassword,int userjifeng) {
        this.id = id;
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.userjifeng= userjifeng;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }



    public int getUserjifeng() {
        return userjifeng;
    }

    public void setUserjifeng(int userjifeng) {
        this.userjifeng = userjifeng;
    }
}




