package com.maker.register.model;

public class Project {
    private String pid;
    private String pname;
    private String money;
    private String type;
    private String phost;
    private String account;
    private String informant;
    private String email;
    private String date;
    private Long timestamp;
    private Double bonus;
    private String note;
    private Integer isGrant;

    private Double charge;
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhost() {
        return phost;
    }

    public void setPhost(String phost) {
        this.phost = phost;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getInformant() {
        return informant;
    }

    public void setInformant(String informant) {
        this.informant = informant;
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getIsGrant() {
        return isGrant;
    }

    public void setIsGrant(Integer isGrant) {
        this.isGrant = isGrant;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }
}
