package com.example.mp3player

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mp3player.databinding.ActivityMainBinding
import java.util.Objects


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mBoundService: MyBoundService


    //boolean variable to keep a check
    var isBound: Boolean = false

    private val connection = object: ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {

            mBoundService = (p1 as MyBoundService.MyBinder).getService()

            isBound = true
        }

        /**
         * only receive a call to onServiceDisconnected
         * for a remote Service (that is, it is running in a different process)
         * don't expect this callback on unbind
         */
        override fun onServiceDisconnected(p0: ComponentName?) {

            isBound = false
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickListeners()
    }


    override fun onStart() {
        super.onStart()
        val intent = Intent(this, MyBoundService::class.java)
//        startService(intent)
        bindService(intent, connection, BIND_AUTO_CREATE)
    }

    //The Toast gets displayed after a delay of 3 seconds.
    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    /**
     * calling unbind service
     * if unbind service is called by the last component then
     * system will destroy the service
     */
    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }
    private fun initClickListeners() {
        binding.startBtn.setOnClickListener {
//            val serviceIntent = Intent(this, MyBoundService::class.java)
//            startService(serviceIntent)
//            bindService(intent , boundServiceConnection, BIND_AUTO_CREATE);

            if(isBound) {
                val num = mBoundService.randomNumber

                Toast.makeText(this, "num: $num", Toast.LENGTH_LONG).show()
            }
        }

        binding.stopBtn.setOnClickListener {
            stopService(Intent(this, MyBoundService::class.java))
//            Toast.makeText(this, "stop Clicked", Toast.LENGTH_LONG).show()
        }
    }


}