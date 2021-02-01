package com.example.komusic;


public class Song {
    private int id;
    private String title;
    private int image;
    private int link;
    private String author;
    private String lyric;

    public Song(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public Song(int id, String title, int image, int link,
                String author, String lyric) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.link = link;
        this.author = author;
        this.lyric = lyric;

    }

    public Song() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public int getLink() {
        return link;
    }

    public void setLink(int link) {
        this.link = link;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }
}
