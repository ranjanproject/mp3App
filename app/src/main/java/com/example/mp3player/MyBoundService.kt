package com.example.mp3player

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.util.Log
import java.util.Random

class MyBoundService: Service() {

    private val localBinder = MyBinder()

    /**
     * returns the same binder
     */
    override fun onBind(p0: Intent?): IBinder{

        return localBinder

    }

    /**
    This method is  Called when activity have disconnected
    from a particular interface published by the service.
    Note: Default implementation of the  method just  return false */

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    /**
     * Called when an activity is connected to the service, after it had
     * previously been notified that all had disconnected in its
     * onUnbind method.  This will only be called by system if the
     * implementation of onUnbind method was overridden to return true.
     */
    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    /**
     * Called by the system to notify a Service that it is no longer     used and is being removed.  The
     * service should clean up any resources it holds (threads,       registered
     * receivers, etc) at this point.  Upon return, there will be no more calls
     * in to this Service object and it is effectively dead.
     */
    override fun onDestroy() {
        super.onDestroy()
    }

    // Random number generator.
    private val mGenerator = Random()

    /** Method for clients.  */
    val randomNumber: Int
        get() = mGenerator.nextInt(100)

    fun generateNumberInLoop(){
       while(true) {
           val num = mGenerator.nextInt(100)
           Log.d("BoundService", num.toString())
       }
    }
    /**
     * Returns the instance of bound service class
     * this runs in the same process of client
     * so we don't need to worry about the handling of IPC
     */
    inner class MyBinder: Binder(){
        fun getService(): MyBoundService = this@MyBoundService
    }

}