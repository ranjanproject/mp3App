package com.example.mp3player

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mp3player.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickListeners()
    }


    private fun initClickListeners() {
        binding.startBtn.setOnClickListener {
//            startService(Intent(this, MP3Service::class.java))
//            Toast.makeText(this, "start Clicked", Toast.LENGTH_LONG).show()
            if (!checkForGroundServiceIsRunning()) {
                val serviceIntent = Intent(
                    this,
                    MP3ForegroundService::class.java
                )
                //check for older version
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(serviceIntent)
                }
            }
        }

        binding.stopBtn.setOnClickListener {
            stopService(Intent(this, MP3ForegroundService::class.java))
//            Toast.makeText(this, "stop Cl`icked", Toast.LENGTH_LONG).show()
        }
    }


    /**
     * This method will check if any service
     * is already running or not
     * Check will help to avoid any repeated service start
     */
    private fun checkForGroundServiceIsRunning(): Boolean{
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        for(service in activityManager.getRunningServices(Int.MAX_VALUE)){
            if (MP3ForegroundService::class.java.name == service.service.className) {
                return true
            }
        }

        return false
    }


}