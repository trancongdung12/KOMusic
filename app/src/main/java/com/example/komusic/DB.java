package com.example.komusic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "okMusic.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SONG = "songs";
    private static final String TABLE_ACCOUNT = "accounts";




    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table songs " +
                        "(id integer primary key, title text,image int,link text, author text,lyric text)"
        );
        db.execSQL(
                "create table accounts " +
                        "(id integer primary key, firstName text, lastName text,phone text, email text,nickname text, password)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_songs_table = String.format("DROP TABLE IF EXISTS %s", TABLE_SONG);
        db.execSQL(drop_songs_table);
        String drop_accounts_table = String.format("DROP TABLE IF EXISTS %s", TABLE_ACCOUNT);
        db.execSQL(drop_accounts_table);
        onCreate(db);
    }
    public boolean insertSong (String title, int image, String link,
                               String author, String lyric) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("image", image);
        contentValues.put("link", link);
        contentValues.put("author", author);
        contentValues.put("lyric", lyric);
        db.insert(TABLE_SONG, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from songs where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_SONG);
        return numRows;
    }

    public boolean updateSong (Integer id, String title, int image, String link,
                               String author, String lyric) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("image", image);
        contentValues.put("link", link);
        contentValues.put("author", author);
        contentValues.put("lyric", lyric);
        db.update(TABLE_SONG, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteSong (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_SONG,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<Song> getAllSong() {
        ArrayList<Song> array_list = new ArrayList<Song>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from songs", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new Song(res.getInt(0),res.getString(1),res.getInt(2), res.getString(3), res.getString(4), res.getString(5)));
            res.moveToNext();
        }
        return array_list;
    }
    //account

    public boolean insertAccount(String firstName, String lastName, String phone, String email, String nickname, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", firstName);
        contentValues.put("lastName", lastName);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("nickname", nickname);
        contentValues.put("password", password);
        db.insert(TABLE_ACCOUNT, null, contentValues);
        return true;
    }

    public Cursor getAccount(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from accounts where id=" + id + "", null);
        return res;
    }

    public boolean updateAccount(Integer id, String firstName, String lastName, String phone, String email, String nickname, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", firstName);
        contentValues.put("lastName", lastName);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("nickname", nickname);
        contentValues.put("password", password);
        db.update(TABLE_ACCOUNT, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteAccount(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_ACCOUNT,
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList<Account> getAll() {
        ArrayList<Account> array_list = new ArrayList<Account>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from accounts", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(new Account(res.getInt(0), res.getString(1),
                    res.getString(2), res.getString(3),
                    res.getString(4), res.getString(5), res.getString(6)));
            res.moveToNext();
        }
        return array_list;
    }
    // login
    public boolean loginAccount(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from accounts where email='" + email + "' and password='" + password+"'", null);
        //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
    public boolean checkEmailExist(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from accounts where email='" + email + "'", null);
        //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

}