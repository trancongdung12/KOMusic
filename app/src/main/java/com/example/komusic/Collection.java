package com.example.komusic;

public class Collection {
    int account_id;
    int song_id;

    public Collection(int account_id, int song_id) {
        this.account_id = account_id;
        this.song_id = song_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }
}
