package com.maker.register.model;

public class ProjectParam {
    private String informant;
    private String account;
    private String email;
    private String date;
    private String[] pid;
    private String[] pname;
    private String[] money;
    private String[] type;
    private String[] phost;

    public String getInformant() {
        return informant;
    }

    public void setInformant(String informant) {
        this.informant = informant;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String[] getPid() {
        return pid;
    }

    public void setPid(String[] pid) {
        this.pid = pid;
    }

    public String[] getPname() {
        return pname;
    }

    public void setPname(String[] pname) {
        this.pname = pname;
    }

    public String[] getMoney() {
        return money;
    }

    public void setMoney(String[] money) {
        this.money = money;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String[] getPhost() {
        return phost;
    }

    public void setPhost(String[] phost) {
        this.phost = phost;
    }
}
