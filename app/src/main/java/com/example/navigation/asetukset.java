package com.example.navigation;

import java.io.Serializable;

public class asetukset implements Serializable {
    private String fontsize;
    private String fontname;
    private String fontcolor;
    private String fontbold;
    private String choice;
    private String language;

    public asetukset(String n, String s, String c, String b, String l, String ch){
        fontsize = s;
        fontname = n;
        fontcolor = c;
        fontbold = b;
        choice = ch;
        language = l;
    }

    public String getSize(){
        return fontsize;
    }
    public String getColor(){
        return fontcolor;
    }
    public String getName(){
        return fontname;
    }
    public String getBold(){
        return fontbold;
    }
    public String getChoice(){
        return choice;
    }
    public String getLanguage(){
        return language;
    }

    public void setSize(String s){
        fontsize = s;
    }
    public void setColor(String c){
        fontcolor = c;
    }
    public void setName(String n){
        fontname = n;
    }
    public void setBold(String b){
        fontbold = b;
    }
    public void setChoice(String ch){
        choice = ch;
    }
    public void setLanguage(String l){
        language = l;
    }
}