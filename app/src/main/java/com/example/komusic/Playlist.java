package com.example.komusic;

public class Playlist {
    private int resourceId;

    public Playlist(int resourceId) {
        this.resourceId = resourceId;
    }

    public Playlist() {
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
