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
    private static final String TABLE_COLLECTION = "collections";

    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table songs " +
                        "(id integer primary key, title text,image int,link int, author text,lyric text)"
        );
        db.execSQL(
                "create table accounts " +
                        "(id integer primary key, firstName text, lastName text,phone text, email text,nickname text, password)"
        );
        db.execSQL("CREATE TABLE collections(\n" +
                "   account_id INTEGER,\n" +
                "   song_id INTEGER,\n" +
                "   PRIMARY KEY (account_id, song_id),\n" +
                "   FOREIGN KEY (account_id) \n" +
                "      REFERENCES accounts (id) \n" +
                "         ON DELETE CASCADE \n" +
                "         ON UPDATE NO ACTION,\n" +
                "   FOREIGN KEY (song_id) \n" +
                "      REFERENCES songs (song_id) \n" +
                "         ON DELETE CASCADE \n" +
                "         ON UPDATE NO ACTION\n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_songs_table = String.format("DROP TABLE IF EXISTS %s", TABLE_SONG);
        db.execSQL(drop_songs_table);
        String drop_accounts_table = String.format("DROP TABLE IF EXISTS %s", TABLE_ACCOUNT);
        db.execSQL(drop_accounts_table);
        onCreate(db);
    }
    public boolean insertSong (String title, int image, int link,
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

    public Song getData(int id) {
        ArrayList<Song> array_list = new ArrayList<Song>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from songs where id="+id+"", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new Song(res.getInt(0),res.getString(1),res.getInt(2), res.getInt(3), res.getString(4), res.getString(5)));
            res.moveToNext();
        }
        return array_list.get(0);
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_SONG);
        return numRows;
    }

    public boolean updateSong (Integer id, String title, int image, int link,
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
            array_list.add(new Song(res.getInt(0),res.getString(1),res.getInt(2), res.getInt(3), res.getString(4), res.getString(5)));
            res.moveToNext();
        }
        return array_list;
    }
    public ArrayList<Song> getSearchSong(String input) {
        ArrayList<Song> array_list = new ArrayList<Song>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM songs WHERE title like '%"+input+"%' or author like '%"+input+"%'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new Song(res.getInt(0),res.getString(1),res.getInt(2), res.getInt(3), res.getString(4), res.getString(5)));
            res.moveToNext();
        }
        return array_list;
    }

    //collect

    public boolean checkExistCollection(int account_id, int song_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from collections where song_id=" + song_id + " and account_id="+account_id+"", null);
        //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
    public boolean insertCollection(int account_id, int song_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("account_id", account_id);
        contentValues.put("song_id", song_id);
        db.insert(TABLE_COLLECTION, null, contentValues);
        return true;
    }

    public Integer deleteCollection(int account_id, int song_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("DELETE FROM collections\n" +
                "WHERE account_id="+account_id+" and song_id="+ song_id+ "", null);
        cursor.close();
        db.close();
        return song_id;
    }

    public ArrayList<Song> getAllCollection(int account_id) {
        ArrayList<Song> array_list = new ArrayList<Song>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();



        Cursor res =  db.rawQuery( "SELECT\n" +
                "\tsongs.id,\n" +
                "\tsongs.title,\n" +
                "\tsongs.image,\n" +
                "\tsongs.link,\n" +
                "\tsongs.author,\n" +
                "\tsongs.lyric\n" +
                "FROM\n" +
                "\tcollections\n" +
                "INNER JOIN songs ON collections.song_id = songs.id\n" +
                "WHERE\n" +
                "\t collections.account_id="+
                account_id +";", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new Song(res.getInt(0),res.getString(1),res.getInt(2), res.getInt(3), res.getString(4), res.getString(5)));
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
    public int loginAccount(String email, String password) {
        int result=-1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from accounts where email='" + email + "' and password='" + password+"'", null);
        //The sort order
        int cursorCount = cursor.getCount();

        if (cursorCount > 0) {
            cursor.moveToFirst();
            result=cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return result;
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