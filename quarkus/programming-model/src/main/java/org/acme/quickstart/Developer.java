package org.acme.quickstart;

import org.acme.quickstart.validator.JvmLanguage;

import javax.validation.constraints.NotBlank;

public class Developer {
    private String name;
    @JvmLanguage
    @NotBlank
    private String favoriteLanguage;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }

    public void setFavoriteLanguage(String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
