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
    private static final String TABLE_NAME = "songs";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_LINK = "link";
    private static final String KEY_LYRIC = "lyric";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_IMAGE = "image";



    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table songs " +
                        "(id integer primary key, title text,image int,link text, author text,lyric text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);
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
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from songs where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
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
        db.update(TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteSong (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<Song> getAll() {
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

}