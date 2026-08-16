package com.example.a24012021094_anubhav_prac4

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmBroadcastReceiver : BroadcastReceiver() {

    companion object {
        val SERVICE_KEY = "Service1"
        val START_VAL = "start"
        val STOP_VAL = "stop"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val str1 = intent.getStringExtra(SERVICE_KEY)

        if (str1 == START_VAL || str1 == STOP_VAL)
        {
            val intentService = Intent(context, AlarmService::class.java)
            if (str1==START_VAL)
                context.startService(intentService)
            else
                context.stopService(intentService)
        }



    }

}