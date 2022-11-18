package com.app.labapp


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
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    var soundEnabler = false
    var animEnabler = false
    var running = false

//    override fun onRestart() {
//        this.recreate()
//        super.onRestart()
//    }

    override fun onResume() {
        super.onResume()
        val prefs = getSharedPreferences("PREF_NAME", MODE_PRIVATE)
        val edit: SharedPreferences.Editor = prefs.edit()
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
        cookie1.setOnClickListener{
            if (!running){
                running = true
                Thread(Runnable {
                    while(running){
                        Thread.sleep(tickrate.toLong())
                        runOnUiThread{
                            if (clicks>0) {
                                coins++
                                clicks--
                                edit.putInt("clicks", clicks)
                                edit.putInt("coins", coins)
                                edit.apply()
                                Log.d("Coins","$coins")
                                text2.text = coins.toString()
                                combos.text = clicks.toString()
                            } else if (clicks == 0){
                                running = false
                            } else {
                                running = false
                            }
                        }
                    }
                }).start()
            }
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
        text2.text = coins.toString()
        combos.text = clicks.toString()

//        cookie1.setOnClickListener{
//            if (!running){
//                running = true
//                Thread(Runnable {
//                    while(running){
//                        Thread.sleep(tickrate.toLong())
//                        runOnUiThread{
//                            if (clicks>0) {
//                                coins++
//                                clicks--
//                                edit.putInt("clicks", clicks)
//                                edit.putInt("coins", coins)
//                                edit.apply()
//                                Log.d("Coins","$coins")
//                                text2.text = coins.toString()
//                                combos.text = clicks.toString()
//                            } else if (clicks == 0){
//                                running = false
//                            } else {
//                                running = false
//                            }
//                        }
//                    }
//                }).start()
//            }
//
//            Log.d("Leci?", "$running")
//            if (upgrade1lvl==0){
//                coins++
//                edit.putInt("coins", coins).apply()
//                Log.d("Coins","$coins")
//            } else {
//                coins++
//                coins+=upgrade1lvl
//                edit.putInt("coins", coins).apply()
//                Log.d("Coins","$coins")
//            }
//            if (upgrade2lvl==0){
//                clicks++
//                edit.putInt("clicks", clicks).apply()
//            } else {
//                clicks++
//                clicks+=upgrade2lvl
//                edit.putInt("clicks", clicks).apply()
//            }
//            text2.text = coins.toString()
//            combos.text = clicks.toString()
//            Log.d("TotalClicks", "$clicks")
//            if (animEnabler){
//                val aniSlide = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
//                cookie1.startAnimation(aniSlide)
//            }
//            if (soundEnabler){
//                val mp = MediaPlayer.create(this, R.raw.glasssmash)
//                mp.start()
//            }

//        }

        val switch1 = findViewById<ImageView>(R.id.buttonAchiev)
        val switch2 = findViewById<ImageView>(R.id.buttonUpgr)

        // achievements
        switch1.setOnClickListener{
            startActivity(Intent(this@MainActivity, AchievementsActivity::class.java))
        }

        // upgrades
        switch2.setOnClickListener{
            startActivity(Intent(this@MainActivity, UpgradesActivity::class.java))
            running = false
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

