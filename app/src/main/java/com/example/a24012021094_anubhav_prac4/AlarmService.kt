package com.example.a24012021094_anubhav_prac4
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class AlarmService : Service() {
    var np : MediaPlayer ?= null
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if(intent != null)
        {
            if(np==null)
            {
                np = MediaPlayer.create(this,R.raw.alarm)
            }
            np?.start()
        }

        return START_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this, "Your Cancel broadcast has been received", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }


}