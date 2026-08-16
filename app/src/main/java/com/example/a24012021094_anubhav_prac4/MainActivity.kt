package com.example.a24012021094_anubhav_prac4

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var textAlarm : TextView
    lateinit var cardView: MaterialCardView

    lateinit var alarmcancel : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textAlarm = findViewById<TextView>(R.id.showing_time)
        cardView = findViewById<MaterialCardView>(R.id.showing_alarm_list)
        alarmcancel = findViewById<TextView>(R.id.cancel_button)
        cardView.visibility = View.GONE
        findViewById<Button>(R.id.button).setOnClickListener {
            showTimeDialog()
        }
        findViewById<Button>(R.id.cancel_button).setOnClickListener {
            val alarmString = alarmcancel.text.toString()
            val format = SimpleDateFormat("hh:mm:ss a MMM, dd yyyy", Locale.ENGLISH)
            val date = format.parse(alarmString)
            val alarmillis = date.time
            setAlarm(alarmillis, AlarmBroadcastReceiver.STOP_VAL)


        }
    }
    private  fun showTimeDialog() {
        var clar: Calendar = Calendar.getInstance()
        val h: Int = clar.get(Calendar.HOUR_OF_DAY)
        val m: Int = clar.get(Calendar.MINUTE)

        val picker = TimePickerDialog(
            this, { tp, sHour, sMinute->sendDialogDataToActivity(sHour,sMinute) },h,m,false

        )
        picker.show()
    }
    private  fun sendDialogDataToActivity(hour:Int,minitue:Int) {

        val alarmCalendar = Calendar.getInstance()
        val year: Int = alarmCalendar.get(Calendar.YEAR)
        val month: Int = alarmCalendar.get(Calendar.MONTH)
        val day: Int = alarmCalendar.get(Calendar.DATE)
        alarmCalendar.set(year, month, day, hour, minitue, 0)
        textAlarm.text = SimpleDateFormat("hh:mm:ss a").format(alarmCalendar.time)
        cardView.visibility = View.VISIBLE
        setAlarm(alarmCalendar.timeInMillis, AlarmBroadcastReceiver.START_VAL)
        Toast.makeText(
            this,
            "Time: hours:${hour}, minutes:${minitue} , millis: ${alarmCalendar.timeInMillis}",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun setAlarm(millisTime: Long , str : String)
    {
        val intent = Intent(this, AlarmBroadcastReceiver::class.java)
        intent.putExtra(AlarmBroadcastReceiver.SERVICE_KEY,str)

        val pendingIntent = PendingIntent.getBroadcast(applicationContext,23432443,intent,
            PendingIntent.FLAG_IMMUTABLE)


        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        if (str == AlarmBroadcastReceiver.START_VAL)
        {
            if(alarmManager.canScheduleExactAlarms())
            {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP,
                    millisTime,
                    pendingIntent)
            }
            else {
                Toast.makeText(this, "Permission Error while setting Alarm", Toast.LENGTH_SHORT)
                    .show()
                Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM,
                    "packagae:$packageName".toUri()).apply { startActivity(this) }
            }
        }
        else if (str == AlarmBroadcastReceiver.STOP_VAL)
        {
            alarmManager.cancel ( pendingIntent )
            sendBroadcast(intent)
        }


    }

}