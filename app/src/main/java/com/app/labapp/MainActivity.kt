package com.app.labapp


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    var soundEnabler = false
    var animEnabler = false
    var running = false
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
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, FLAG_IMMUTABLE)
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
//    override fun onRestart() {
//        this.recreate()
//        super.onRestart()
//    }

    override fun onResume() {
        super.onResume()

        val prefs = getSharedPreferences("PREF_NAME", MODE_PRIVATE)
        val edit: SharedPreferences.Editor = prefs.edit()

        //achievements declare
//        var ach_first = prefs.getBoolean("ach_first", false)

        var ach_all = prefs.getBoolean("ach_all", false)
        val a3:String = resources.getString(R.string.ach_unl)

        fun concat(s1: String, s2: String): String {
            return "$s1 $s2"
        }

        val text2 = findViewById<TextView>(R.id.cookiesCount)
        val combos = findViewById<TextView>(R.id.comboView)
        var sound = prefs.getBoolean("sound", true)
        var animationEnable = prefs.getBoolean("animation", true)
        var coins = prefs.getInt("coins", 0)
        var tickrate = prefs.getLong("tickrate", 2000)
        var clicks = prefs.getInt("clicks", 0)
        var upgrade1lvl = prefs.getInt("upgrade1lvl", 0)
        var upgrade2lvl = prefs.getInt("upgrade2lvl", 0)
        val cookie1 = findViewById<ImageView>(R.id.cookie)
        Log.d("CoinsResumed", "$coins")
        Log.d("ClicksResumed", "$clicks")
        soundEnabler = sound
        animEnabler = animationEnable
        running = true
        text2.setText(coins.toString())
        Thread(Runnable {
            while(running){
                Thread.sleep(tickrate.toLong())
                    runOnUiThread{
                        if (clicks>0) {
                            coins++
                            coins+=upgrade2lvl
                            clicks--
                            clicks-=upgrade2lvl
                            text2.text = coins.toString()
                            combos.text = clicks.toString()
                        } else if (clicks == 0){
                            running = false
                        } else {
                            running = false
                            clicks = 0
                        }
                        edit.putInt("clicks", clicks)
                        edit.putInt("coins", coins)
                        Log.d("Coins","$coins")
                    }
                }
            }).start()
        cookie1.setOnClickListener{
            Log.d("Leci?", "$running")
            if (upgrade1lvl==0){
                coins++
                edit.putInt("coins", coins).apply()
                Log.d("Coins","$coins")
            } else {
                coins++
                coins+=upgrade1lvl
                edit.putInt("coins", coins).apply()
                Log.d("Coins","$coins")
            }
            if (upgrade2lvl==0){
                clicks++
                edit.putInt("clicks", clicks).apply()
            } else {
                clicks++
                clicks+=upgrade2lvl
                edit.putInt("clicks", clicks).apply()
            }
            text2.text = coins.toString()
            combos.text = clicks.toString()
            Log.d("TotalClicks", "$clicks")
            if (animEnabler){
                val aniSlide = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
                cookie1.startAnimation(aniSlide)
            }
            if (soundEnabler){
                val mp = MediaPlayer.create(this, R.raw.glasssmash)
                mp.start()
            }
            // coins achievements
            var ach_1000 = prefs.getBoolean("ach_1000", false)
            var ach_10000 = prefs.getBoolean("ach_10000", false)
            var ach_100000 = prefs.getBoolean("ach_100000", false)
            var ach_1000000 = prefs.getBoolean("ach_1000000", false)
            var ach_first = prefs.getBoolean("ach_first", false)
            val name1:String = resources.getString(R.string.ach_1000)
            val name2:String = resources.getString(R.string.ach_10000)
            val name3:String = resources.getString(R.string.ach_100000)
            val name4:String = resources.getString(R.string.ach_1000000)
            val name5:String = resources.getString(R.string.ach_first)
            if (!ach_first){
                edit.putBoolean("ach_first", true).apply()
                val message = concat(a3, name5)
//                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                notcontenttitle = a3
                notcontent = name5
                createNotification(cookie1)
            }
            if (coins >= 1000 && !ach_1000){
                edit.putBoolean("ach_1000", true).apply()
                val message = concat(a3, name1)
//                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                notcontenttitle = a3
                notcontent = name1
                createNotification(cookie1)
            }
            if (coins >= 10000 && !ach_10000){
                edit.putBoolean("ach_10000", true).apply()
                val message = concat(a3, name2)
//                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                notcontenttitle = a3
                notcontent = name2
                createNotification(cookie1)
            }
            if (coins >= 100000 && !ach_100000){
                edit.putBoolean("ach_100000", true).apply()
                val message = concat(a3, name3)
//                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                notcontenttitle = a3
                notcontent = name3
                createNotification(cookie1)
            }
            if (coins >= 1000000 && !ach_1000000){
                edit.putBoolean("ach_1000000", true).apply()
                val message = concat(a3, name4)
//                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                notcontenttitle = a3
                notcontent = name4
                createNotification(cookie1)
            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        val prefs = getSharedPreferences("PREF_NAME", MODE_PRIVATE)
        val edit: SharedPreferences.Editor = prefs.edit()

        val storedLang = prefs.getString("lang", "en")
        Log.d("Lang", "$storedLang")
        val lang = Locale("$storedLang")
        Locale.setDefault(lang)
        resources.configuration.setLocale(lang)
        resources.updateConfiguration(resources.configuration, resources.displayMetrics)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val combos = findViewById<TextView>(R.id.comboView)
        val text2 = findViewById<TextView>(R.id.cookiesCount)
        val cookie1 = findViewById<ImageView>(R.id.cookie)
        var tickrate = prefs.getLong("tickrate", 2000)
        var sound = prefs.getBoolean("sound", true)
        var animationEnable = prefs.getBoolean("animation", true)
        var upgrade1lvl = prefs.getInt("upgrade1lvl", 0)
        var upgrade2lvl = prefs.getInt("upgrade2lvl", 0)
        var coins = prefs.getInt("coins", 0)
        var clicks = prefs.getInt("clicks", 0)
        Log.d("Up1lvl", "$upgrade1lvl")
        Log.d("Up2lvl", "$upgrade2lvl")
        Log.d("CoinsCreate", "$coins")
        soundEnabler = sound
        animEnabler = animationEnable
        running = true
        text2.text = coins.toString()
        combos.text = clicks.toString()


        val switch1 = findViewById<ImageView>(R.id.buttonAchiev)
        val switch2 = findViewById<ImageView>(R.id.buttonUpgr)

        // achievements
        switch1.setOnClickListener{
            running = false
            edit.apply()
            startActivity(Intent(this@MainActivity, AchievementsActivity::class.java))
        }

        // upgrades
        switch2.setOnClickListener{
            running = false
            edit.apply()
            startActivity(Intent(this@MainActivity, UpgradesActivity::class.java))
        }

    }
    // Menu creator
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    // Menu options
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            intent = Intent(applicationContext, SettingsActivity::class.java)
            startActivity(intent)
            finish()
            true
        }

        R.id.action_exitgame -> {
            finish()
            exitProcess(0)
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }


}

