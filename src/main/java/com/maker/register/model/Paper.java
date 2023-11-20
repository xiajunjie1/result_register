package com.maker.register.model;

import java.io.Serializable;

public class Paper implements Serializable {
    private String doi;
    private String title;
    private String sn;
    private String jtitleVol;
    private Integer paperClass;
    private String informant;
    private String account;
    private String email;
    private String date;

    private Long timestamp;
    private Integer thanks;
    private Integer isGrant;
    private Double bonus;
    private String pUrl;
    private String note;

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getJtitleVol() {
        return jtitleVol;
    }

    public void setJtitleVol(String jtitleVol) {
        this.jtitleVol = jtitleVol;
    }

    public Integer getPaperClass() {
        return paperClass;
    }

    public void setPaperClass(Integer paperClass) {
        this.paperClass = paperClass;
    }

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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getThanks() {
        return thanks;
    }

    public void setThanks(Integer thanks) {
        this.thanks = thanks;
    }

    public Integer getIsGrant() {
        return isGrant;
    }

    public void setIsGrant(Integer isGrant) {
        this.isGrant = isGrant;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public String getpUrl() {
        return pUrl;
    }

    public void setpUrl(String pUrl) {
        this.pUrl = pUrl;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "doi='" + doi + '\'' +
                ", title='" + title + '\'' +
                ", sn='" + sn + '\'' +
                ", jtitleVol='" + jtitleVol + '\'' +
                ", paperClass=" + paperClass +
                ", informant='" + informant + '\'' +
                ", account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", date='" + date + '\'' +
                ", timestamp=" + timestamp +
                ", thanks=" + thanks +
                ", isGrant=" + isGrant +
                ", bonus=" + bonus +
                ", pUrl='" + pUrl + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
