package com.example.passaggicompany2k25.bean;

public class Letter {

    private String title, text, password;
    private boolean isVisible = false;

    public Letter(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Letter(String line) {
        String[] temp = line.split("\\+");
        this.title = temp[0].trim();
        this.text = temp[1].replace("/n","\n").trim();
        this.password = temp[2].trim();
    }

    public String getTitle() {
        return title;
    }

    public Boolean check(String input) {
        if (input.equals(password)) {
            isVisible = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPassword() {
        return this.password;
    }

}
