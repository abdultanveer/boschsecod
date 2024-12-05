package com.example.bosch2

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class AdditionService : Service() {
    private val localBinder = LocalBinder()

    fun addNos( a:Int,b:Int)= a + b  //7


    inner class LocalBinder : Binder() {
        fun getService(): AdditionService = this@AdditionService //5

    }

        override fun onBind(intent: Intent): IBinder {  //2

            return localBinder//2a
    }
}