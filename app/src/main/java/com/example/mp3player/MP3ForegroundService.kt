package com.example.mp3player

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MP3ForegroundService: Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    override fun onBind(p0: Intent?): IBinder? {

        return null
    }
}