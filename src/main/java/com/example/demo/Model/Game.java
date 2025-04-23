package com.example.demo.Model;

public class Game {

    int id;
    String name;
    String img;
    String link;
    String date;
    String platform;

    public Game(){

    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getLink() {
        return link;
    }

    public String getDate() {
        return date;
    }

    public String getPlatform() {
        return platform;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", link='" + link + '\'' +
                ", date='" + date + '\'' +
                ", platform='" + platform + '\'' +
                '}';
    }
}
