package com.thiago.fipp.appsongs;

import java.util.List;

public class ApiVagalume {

    private String type;
    private Art art;
    private List<Mus> mus;
    private Boolean badwords;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Art getArt() {
        return art;
    }

    public void setArt(Art art) {
        this.art = art;
    }

    public List<Mus> getMus() {
        return mus;
    }

    public void setMus(List<Mus> mus) {
        this.mus = mus;
    }

    public Boolean getBadwords() {
        return badwords;
    }

    public void setBadwords(Boolean badwords) {
        this.badwords = badwords;
    }
}

class Art {
    private String id;
    private String name;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

class Mus {
    private String id;
    private String name;
    private String url;
    private String lang;
    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
