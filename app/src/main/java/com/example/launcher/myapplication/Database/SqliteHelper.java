package com.example.launcher.myapplication.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "MY_DB";
    public static final String CARPET_TABLE_NAME = "AppCarpets";
    public static final String PRICE = "Price";
    public static final String PATH = "Path";

    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + CARPET_TABLE_NAME + " (id, INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PRICE + " INTEGER," + PATH + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + CARPET_TABLE_NAME);
        onCreate(db);
    }
}
