package com.example.bosch2

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var tvMain:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvMain = findViewById(R.id.textView)

    }

    fun insertContentProvider(view: View) {
        var uri = Uri.parse("content://com.example.appa.provider/contacts")
        var cursor = contentResolver.query(uri,null,null,null,null)
        var values = ContentValues()
        values.put("name","abdul-ansari")
        values.put("phone",123456)
        contentResolver.insert(uri,values)


    }

    fun retreiveContentProvider(view: View) {

        var uri = Uri.parse("content://com.example.appa.provider/contacts")
        var cursor = contentResolver.query(uri,null,null,null,null)
        cursor?.moveToLast()
        var name = cursor?.getColumnIndex("name")?.let { cursor.getString(it) }
        var phone = cursor?.getColumnIndex("phone")?.let { cursor.getString(it) }
        tvMain.setText("name--"+ name+"-phone no is--"+phone)
    }
}

//
//ContentResolver contentResolver = getContentResolver();
//
//// Query the ContentProvider in AppA
//Cursor cursor = contentResolver.query(ContactContract.CONTENT_URI, null, null, null, null);
//
//if (cursor != null && cursor.moveToFirst()) {
//    do {
//        // Get data from cursor
//        String name = cursor.getString(cursor.getColumnIndex(ContactContract.Contacts.COLUMN_NAME));
//        String phone = cursor.getString(cursor.getColumnIndex(ContactContract.Contacts.COLUMN_PHONE));
//        Log.d("Contact", "Name: " + name + ", Phone: " + phone);
//    } while (cursor.moveToNext());
//    cursor.close();
//}
//}