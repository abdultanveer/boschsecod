package com.example.bosch2.contentproviders;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class ContactProvider extends ContentProvider {

    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        // Create or open the database
        SQLiteOpenHelper helper = new SQLiteOpenHelper(getContext(), "contacts.db", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("CREATE TABLE " + ContactContract.Contacts.TABLE_NAME + " ("
                        + ContactContract.Contacts.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + ContactContract.Contacts.COLUMN_NAME + " TEXT, "
                        + ContactContract.Contacts.COLUMN_PHONE + " TEXT)");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + ContactContract.Contacts.TABLE_NAME);
                onCreate(db);
            }
        };
        db = helper.getWritableDatabase();
        return db != null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (uri.equals(ContactContract.CONTENT_URI)) {
            return db.query(ContactContract.Contacts.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        }
        throw new IllegalArgumentException("Unsupported URI: " + uri);
    }

    @Override
    public String getType(Uri uri) {
        if (uri.equals(ContactContract.CONTENT_URI)) {
            return "vnd.android.cursor.dir/vnd.com.example.appa.contacts";
        }
        throw new IllegalArgumentException("Unsupported URI: " + uri);
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = db.insert(ContactContract.Contacts.TABLE_NAME, null, values);
        return ContentUris.withAppendedId(ContactContract.CONTENT_URI, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return db.delete(ContactContract.Contacts.TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return db.update(ContactContract.Contacts.TABLE_NAME, values, selection, selectionArgs);
    }
}
