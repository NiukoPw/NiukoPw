package com.example.libreria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class LibraryDB implements BaseColumns { //COSTRUISCE E POPOLA IL DATABASE

    public static final String TABLE_NAME = "libri";
    public static final String TITOLO = "titolo";
    public static final String DESCRIZIONE = "descrizione";
    public static final String AUTORE = "autore";
    public static final String URL = "url";
    public static final String TRUNCATE_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String DB_NAME = "books.db";
    public static final int VERSION = 1;

    public static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TITOLO + " TEXT , "
            + AUTORE + " TEXT , "
            + URL + " TEXT , "
            + DESCRIZIONE + " TEXT );";

    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public String getUrl() {
        return URL;
    }
    public LibraryDB(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }


    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DB_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(DatabaseHelper.class.getName(), "Aggiornamento database dalla versione " + oldVersion + " alla "
                    + newVersion + ". I dati esistenti verranno eliminati.");
            db.execSQL("DROP TABLE IF EXISTS libri");
            onCreate(db);
        }
    }

    public LibraryDB open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    public void close() {
        DBHelper.close();
    }


    public long inserisciLibro(String titolo, String descrizione, String autore, String url) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(TITOLO, titolo);
        initialValues.put(DESCRIZIONE, descrizione);
        initialValues.put(AUTORE, autore);
        initialValues.put(URL, url);

        return db.insert(TABLE_NAME, null, initialValues);
    }

    public void svuotaDatabase() {
        db.execSQL("DELETE FROM libri");
    }


    public boolean cancellaLibro(long rigaId) {
        return db.delete(TABLE_NAME, _ID + "=" + rigaId, null) > 0;
    }


    public Cursor ottieniTuttiLibri() {
        return db.query(
                LibraryDB.TABLE_NAME,
                new String[]{
                        LibraryDB._ID,
                        LibraryDB.TITOLO,
                        LibraryDB.DESCRIZIONE,
                        LibraryDB.URL,
                        LibraryDB.AUTORE},
                null,
                null,
                null,

                null,
                null
        );
    }


    public Cursor ottieniLibro(long rigaId) throws SQLException {
        Cursor mCursore = db.query(true, TABLE_NAME, new String[]{_ID, TITOLO, DESCRIZIONE, AUTORE, URL}, _ID + "=" + rigaId, null, null, null, null, null);
        if (mCursore != null) {
            mCursore.moveToFirst();
        }
        return mCursore;
    }


    public boolean aggiornaLibro(long rigaId, String titolo, String descrizione, String autore, String url) {
        ContentValues args = new ContentValues();
        args.put(TITOLO, titolo);
        args.put(DESCRIZIONE, descrizione);
        args.put(AUTORE, autore);
        args.put(URL, url);
        return db.update(TABLE_NAME, args, _ID + "=" + rigaId, null) > 0;
    }

}
