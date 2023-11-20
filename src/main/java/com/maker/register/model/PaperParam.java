package com.maker.register.model;

public class PaperParam {
    private String informant;
    private String account;
    private String email;
    private String date;
    private String[] doi;
    private String[] title;
    private String[] sn;
    private String[] pUrl;
    private String[] jtitleVol;
    private String[] paperClass;

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

    public String[] getDoi() {
        return doi;
    }

    public void setDoi(String[] doi) {
        this.doi = doi;
    }

    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public String[] getSn() {
        return sn;
    }

    public void setSn(String[] sn) {
        this.sn = sn;
    }

    public String[] getpUrl() {
        return pUrl;
    }

    public void setpUrl(String[] pUrl) {
        this.pUrl = pUrl;
    }

    public String[] getJtitleVol() {
        return jtitleVol;
    }

    public void setJtitleVol(String[] jtitleVol) {
        this.jtitleVol = jtitleVol;
    }

    public String[] getPaperClass() {
        return paperClass;
    }

    public void setPaperClass(String[] paperClass) {
        this.paperClass = paperClass;
    }
}
