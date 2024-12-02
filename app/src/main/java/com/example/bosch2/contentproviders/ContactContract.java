package com.example.bosch2.contentproviders;

import android.net.Uri;

public class ContactContract {
    public static final String AUTHORITY = "com.example.appa.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/contacts");

    public static final class Contacts {
        public static final String TABLE_NAME = "contacts";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE = "phone";
    }
}
