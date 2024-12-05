package com.example.bosch2

import android.content.ComponentName
import android.content.ContentValues
import android.content.Intent
import android.content.ServiceConnection
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bosch2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var additionService:AdditionService

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

    }

    override fun onStart() {
        super.onStart()
        binding.btnStart.setOnClickListener { startMusicService() }
        binding.btStop.setOnClickListener { stopMusicService() }
        binding.btnBind.setOnClickListener { bindAddService() }

    }

    private fun bindAddService() {
        var bindAddIntent = Intent(this,AdditionService::class.java)
        bindService(bindAddIntent,serviceConn, BIND_AUTO_CREATE)//1
    }

    private val serviceConn = object : ServiceConnection {

        override fun onServiceConnected(p0: ComponentName?, localBinder: IBinder?) {//3
            val mylocalBinder = localBinder as AdditionService.LocalBinder
              additionService = mylocalBinder.getService()//4
            var sum = additionService.addNos(10,20)//6
            binding.textView.text ="sum--"+sum //8
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
        }
    }


        private fun stopMusicService() {
        var msIntent = Intent(this,MusicService::class.java)
        stopService(msIntent)
    }

    private fun startMusicService() {
        var msIntent = Intent(this,MusicService::class.java)
        msIntent.putExtra("url","https://musicdownload.com")
        startService(msIntent)
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
        binding.textView.setText("name--"+ name+"-phone no is--"+phone)
    }
}

