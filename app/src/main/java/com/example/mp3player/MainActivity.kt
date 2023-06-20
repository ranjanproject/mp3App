package com.example.mp3player

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
            startService(Intent(this, MP3Service::class.java))
//            Toast.makeText(this, "start Clicked", Toast.LENGTH_LONG).show()
        }

        binding.stopBtn.setOnClickListener {
            stopService(Intent(this, MP3Service::class.java))
//            Toast.makeText(this, "stop Clicked", Toast.LENGTH_LONG).show()
        }
    }


}