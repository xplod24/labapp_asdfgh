package com.app.labapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.google.android.material.snackbar.Snackbar

class UpgradesActivity : AppCompatActivity() {

    private val channelId = "10001"
    private val defaultChannelId = "default"
    private var notcontent:String = ""
    private var notcontenttitle:String = ""

    private fun createNotification(view: View) {
        val notificationIntent = Intent(applicationContext, MainActivity::class.java)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(applicationContext, defaultChannelId)
        notificationIntent.putExtra("fromNotification", true)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
        builder.setContentTitle(notcontenttitle)
        builder.setContentIntent(pendingIntent)
        builder.setContentText(notcontent)
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setAutoCancel(true)
        builder.setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(channelId, "NOTIFICATION_CHANNEL_NAME", importance)
            builder.setChannelId(channelId)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrades)
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        val prefs = getSharedPreferences("PREF_NAME", MODE_PRIVATE)
        val edit: SharedPreferences.Editor = prefs.edit()
        var upgrade1lvl = prefs.getInt("upgrade1lvl", 0)
        var upgrade2lvl = prefs.getInt("upgrade2lvl", 0)
        var upgrade3lvl = prefs.getInt("upgrade3lvl", 0)
        var upgrade1cost = prefs.getInt("upgrade1cost", 2)
        var upgrade2cost = prefs.getInt("upgrade2cost", 2)
        var upgrade3cost = prefs.getInt("upgrade3cost", 2)
        var coins = prefs.getInt("coins", 0)
        edit.putInt("coins", coins)
        var tickrate = prefs.getLong("tickrate", 2000)

        //achievements declare
        var ach_first = prefs.getBoolean("ach_first", false)
        var ach_1000 = prefs.getBoolean("ach_1000", false)
        var ach_10000 = prefs.getBoolean("ach_10000", false)
        var ach_100000 = prefs.getBoolean("ach_100000", false)
        var ach_1000000 = prefs.getBoolean("ach_1000000", false)
        var ach_up1_5 = prefs.getBoolean("ach_up1_5", false)
        var ach_up1_10 = prefs.getBoolean("ach_up1_10", false)
        var ach_up1_15 = prefs.getBoolean("ach_up1_15", false)
        var ach_up1_20 = prefs.getBoolean("ach_up1_20", false)
        var ach_up2_5 = prefs.getBoolean("ach_up2_5", false)
        var ach_up2_10 = prefs.getBoolean("ach_up2_10", false)
        var ach_up2_15 = prefs.getBoolean("ach_up2_15", false)
        var ach_up2_20 = prefs.getBoolean("ach_up2_20", false)
        var ach_up3_5 = prefs.getBoolean("ach_up3_5", false)
        var ach_up3_10 = prefs.getBoolean("ach_up3_10", false)
        var ach_up3_15 = prefs.getBoolean("ach_up3_15", false)
        var ach_up3_20 = prefs.getBoolean("ach_up3_20", false)
        var ach_all = prefs.getBoolean("ach_all", false)
        val a1:String = resources.getString(R.string.not_enough)
        val a2:String = resources.getString(R.string.upgraded)
        val a3:String = resources.getString(R.string.ach_unl)
        val a4:String = resources.getString(R.string.up_max)
        val name1:String = resources.getString(R.string.ach_up1_5)
        val name2:String = resources.getString(R.string.ach_up1_10)
        val name3:String = resources.getString(R.string.ach_up1_15)
        val name4:String = resources.getString(R.string.ach_up1_20)

        val name5:String = resources.getString(R.string.ach_up2_5)
        val name6:String = resources.getString(R.string.ach_up2_10)
        val name7:String = resources.getString(R.string.ach_up2_15)
        val name8:String = resources.getString(R.string.ach_up2_20)

        val name9:String = resources.getString(R.string.ach_up3_5)
        val name10:String = resources.getString(R.string.ach_up3_10)
        val name11:String = resources.getString(R.string.ach_up3_15)
        val name12:String = resources.getString(R.string.ach_up3_20)

        val upgrade1btn = findViewById<TextView>(R.id.up1)
        val upgrade2btn = findViewById<TextView>(R.id.up2)
        val upgrade3btn = findViewById<TextView>(R.id.up3)
        val str1 = findViewById<TextView>(R.id.up1currlvl)
        val str2 = findViewById<TextView>(R.id.up1nextlvl)
        val str3 = findViewById<TextView>(R.id.up2currlvl)
        val str4 = findViewById<TextView>(R.id.up2nextlvl)
        val str5 = findViewById<TextView>(R.id.up3currlvl)
        val str6 = findViewById<TextView>(R.id.up3nextlvl)
        val text2 = findViewById<TextView>(R.id.cookiesCount2)
        str1.text = "$upgrade1lvl"
        str2.text = "$upgrade1cost"
        str3.text = "$upgrade2lvl"
        str4.text = "$upgrade2cost"
        str5.text = "$upgrade3lvl"
        str6.text = "$upgrade3cost"
        text2.text = "$coins"

        fun concat(s1: String, s2: String): String {
            return "$s1 $s2"
        }

        upgrade1btn.setOnClickListener{
            if (coins >= upgrade1cost){
                Log.d("CoinsBefore","$coins")
                coins-=upgrade1cost
                upgrade1cost*=2
                upgrade1lvl++
                Log.d("CoinsAfter", "$coins")
                edit.putInt("coins", coins)
                edit.putInt("upgrade1cost", upgrade1cost)
                edit.putInt("upgrade1lvl", upgrade1lvl)
                when (upgrade1lvl){
                    5 -> {
                        edit.putBoolean("ach_up1_5", true)
                        val message = concat(a3, name1)
//                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        notcontenttitle = a3
                        notcontent = name1
                        createNotification(upgrade1btn)
                    }
                    10 ->{
                        edit.putBoolean("ach_up1_10", true)
                        val message = concat(a3, name2)
//                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        notcontenttitle = a3
                        notcontent = name2
                        createNotification(upgrade1btn)
                    }
                    15 ->{
                        edit.putBoolean("ach_up1_15", true)
                        val message = concat(a3, name3)
//                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        notcontenttitle = a3
                        notcontent = name3
                        createNotification(upgrade1btn)
                    }
                    20 ->{
                        edit.putBoolean("ach_up1_20", true)
                        val message = concat(a3, name4)
//                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        notcontenttitle = a3
                        notcontent = name4
                        createNotification(upgrade1btn)
                    }
                }

                text2.text = coins.toString()
                str1.text = "$upgrade1lvl"
                str2.text =  "$upgrade1cost"
                val snack = Snackbar.make(it,a2, Snackbar.LENGTH_LONG)
                snack.show()
            } else {
                val snack = Snackbar.make(it,a1, Snackbar.LENGTH_LONG)
                snack.show()
            }
            edit.apply()
        }
        // upgrade2 button actions
        // add more cookies to combo
        upgrade2btn.setOnClickListener{
            if (coins >= upgrade2cost){
                Log.d("CoinsBefore","$coins")
                coins-=upgrade2cost
                upgrade2cost*=2
                upgrade2lvl++
                Log.d("CoinsMedium","$coins")
                edit.putInt("coins", coins)
                edit.putInt("upgrade2cost", upgrade2cost)
                edit.putInt("upgrade2lvl", upgrade2lvl)
                text2.text = coins.toString()
                str3.text = "$upgrade2lvl"
                str4.text = "$upgrade2cost"
                when (upgrade2lvl){
                    5 -> {
                        val message = concat(a3, name5)
                        edit.putBoolean("ach_up2_5", true)
//                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        notcontenttitle = a3
                        notcontent = name5
                        createNotification(upgrade1btn)
                    }
                    10 ->{
                        edit.putBoolean("ach_up2_10", true)
                        val message = concat(a3, name6)
//                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        notcontenttitle = a3
                        notcontent = name6
                        createNotification(upgrade1btn)
                    }
                    15 ->{
                        edit.putBoolean("ach_up2_15", true)
                        val message = concat(a3, name7)
//                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        notcontenttitle = a3
                        notcontent = name7
                        createNotification(upgrade1btn)
                    }
                    20 ->{
                        edit.putBoolean("ach_up2_20", true)
                        val message = concat(a3, name8)
//                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        notcontenttitle = a3
                        notcontent = name8
                        createNotification(upgrade1btn)
                    }
                }
                val snack = Snackbar.make(it,a2, Snackbar.LENGTH_LONG)
                snack.show()
            } else {
                val snack = Snackbar.make(it,a1, Snackbar.LENGTH_LONG)
                snack.show()
            }
            edit.apply()
        }
        // upgrade3 button actions
        // lower combo tick rate
        upgrade3btn.setOnClickListener{
            if (coins >= upgrade3cost){
                if (tickrate <= 10){
                    val snack = Snackbar.make(it,"Reached Max LVL!", Snackbar.LENGTH_LONG)
                    snack.show()
                } else {
                    Log.d("CoinsBefore","$coins")
                    coins-=upgrade3cost
                    upgrade3cost*=2
                    upgrade3lvl++
                    Log.d("CoinsAfter", "$coins")
                    edit.putInt("coins", coins)
                    edit.putInt("upgrade3cost", upgrade3cost)
                    edit.putInt("upgrade3lvl", upgrade3lvl)
                    edit.apply()
                    text2.text = coins.toString()
                    str5.text = "$upgrade3lvl"
                    str6.text = "$upgrade3cost"

                }
                when (upgrade3lvl){
                    5 -> {
                        edit.putBoolean("ach_up3_5", true)
                        val message = concat(a3, name9)
//                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        notcontenttitle = a3
                        notcontent = name9
                        createNotification(upgrade1btn)
                    }
                    10 ->{
                        edit.putBoolean("ach_up3_10", true)
                        val message = concat(a3, name10)
//                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        notcontenttitle = a3
                        notcontent = name10
                        createNotification(upgrade1btn)
                    }
                    15 ->{
                        edit.putBoolean("ach_up3_15", true)
                        val message = concat(a3, name11)
//                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        notcontenttitle = a3
                        notcontent = name11
                        createNotification(upgrade1btn)
                    }
                    20 ->{
                        edit.putBoolean("ach_up3_20", true)
                        val message = concat(a3, name12)
//                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        notcontenttitle = a3
                        notcontent = name12
                        createNotification(upgrade1btn)
                    }
                }
                if (tickrate in 1501..2000){
                    tickrate-=100
                    edit.putLong("tickrate", tickrate)
                    val snack = Snackbar.make(it,a2, Snackbar.LENGTH_LONG)
                    snack.show()
                } else if (tickrate in 1001..1500){
                    tickrate-=80
                    edit.putLong("tickrate", tickrate)
                    val snack = Snackbar.make(it,a2, Snackbar.LENGTH_LONG)
                    snack.show()
                } else if (tickrate in 501..1000){
                    tickrate-=50
                    edit.putLong("tickrate", tickrate)
                    val snack = Snackbar.make(it,a2, Snackbar.LENGTH_LONG)
                    snack.show()
                } else if (tickrate in 101..500) {
                    tickrate-=20
                    edit.putLong("tickrate", tickrate)
                    val snack = Snackbar.make(it,a2, Snackbar.LENGTH_LONG)
                    snack.show()
                } else if (tickrate in 10..100) {
                    tickrate-=10
                    edit.putLong("tickrate", tickrate)
                    val snack = Snackbar.make(it,a2, Snackbar.LENGTH_LONG)
                    snack.show()
                } else {
                    edit.putLong("tickrate", 10)
                }
            } else {
                val snack = Snackbar.make(it,a1, Snackbar.LENGTH_LONG)
                snack.show()
            }
            edit.apply()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        onBackPressed()
        return true
    }
}
