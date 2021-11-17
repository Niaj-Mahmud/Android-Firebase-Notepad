package com.example.loginapp;

public class DataText {
   private String Title;
   private String MultiText;

    public DataText(String title, String multiText) {
        Title = title;
        MultiText = multiText;
    }
    public DataText(){

    }
    public String getTitle() {

        return Title;
    }

    public void setTitle(String title) {

        Title = title;
    }

    public String getMultiText() {
        return MultiText;
    }

    public void setMultiText(String multiText) {

        MultiText = multiText;
    }
}
