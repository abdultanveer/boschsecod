package com.example.bosch2

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class AdditionService : Service() {
    private val localBinder = LocalBinder()
var TAG = AdditionService::class.java.simpleName


    override fun onCreate() {
        super.onCreate()
        Log.i(TAG,"addition service created")
    }

    private val aidlBinder = object : IAddition.Stub() {
        override fun sumAdd(a: Int, b: Int): Int {
            Log.i(TAG,"sumAdd method called--"+a)
            return  a+b
        }

    }


        fun addNos( a:Int,b:Int)= a + b  //7


    inner class LocalBinder : Binder() {
        fun getService(): AdditionService = this@AdditionService //5

    }

        override fun onBind(intent: Intent): IBinder {  //2
            Log.i(TAG,"onBind method called--")

            return aidlBinder   //2a
    }
}