package com.app.labapp


import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("com.app.labapp", MODE_PRIVATE)
        val aniSlide = AnimationUtils.loadAnimation(this, R.anim.zoom_in)

        setContentView(R.layout.activity_main)
        val text2 = findViewById<TextView>(R.id.cookiesCount)
        val cookie1 = findViewById<ImageView>(R.id.cookie)
        val combos = findViewById<TextView>(R.id.comboView)
        val switch1 = findViewById<ImageView>(R.id.buttonAchiev)
        val switch2 = findViewById<ImageView>(R.id.buttonUpgr)
        val str1 = findViewById<TextView>(R.id.upgrade1)
        val str2 = findViewById<TextView>(R.id.upgrade1_cost)
        val str3 = findViewById<TextView>(R.id.upgrade2)
        val str4 = findViewById<TextView>(R.id.upgrade2_cost)
        val str5 = findViewById<TextView>(R.id.upgrade3)
        val str6 = findViewById<TextView>(R.id.upgrade3_cost)
        // persistent values
        var upgrade1lvl = prefs.getInt("upgrade1lvl", 0)
        var upgrade2lvl = prefs.getInt("upgrade2lvl", 0)
        var upgrade3lvl = prefs.getInt("upgrade3lvl", 0)
        var upgrade1cost = prefs.getInt("upgrade1cost", 2)
        var upgrade2cost = prefs.getInt("upgrade2cost", 2)
        var upgrade3cost = prefs.getInt("upgrade3cost", 2)
        var coins = prefs.getInt("coins", 0)
        var running = prefs.getBoolean("running", false)
        var clicks = prefs.getInt("clicks", 0)
        var tickrate = prefs.getLong("tickrate", 2000)
        // TO DO - next upgrade paths
        // var comboclicks = prefs.getInt("comboclicks", 0)
        // var comboMulti = prefs.getInt("combomulti", 0)
        //upgrade texts
        str1.text = "$upgrade1lvl"
        str2.text = "$upgrade1cost"
        str3.text = "$upgrade2lvl"
        str4.text = "$upgrade2cost"
        str5.text = "$upgrade3lvl"
        str6.text = "$upgrade3cost"
        //cookies text
        text2.text = "$coins"
        //combos text
        combos.text = "$clicks"

        // main clicker start
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
                                prefs.edit().putInt("clicks", clicks).commit()
                                prefs.edit().putInt("coins", coins).commit()
                            } else if (clicks == 0){
                                prefs.edit().putBoolean("running", false).commit()
                            } else {
                                prefs.edit().putBoolean("running", false).commit()
                            }
                            text2.text = coins.toString()
                            combos.text = clicks.toString()
                        }
                    }
                }).start()
            } else {
            }
            if (upgrade1lvl==0){
                coins++
                prefs.edit().putInt("coins", coins).commit()
            } else {
                coins++
                coins+=upgrade1lvl
                prefs.edit().putInt("coins", coins).commit()
            }
            if (upgrade2lvl==0){
                clicks++
                prefs.edit().putInt("clicks", clicks).commit()
            } else {
                clicks++
                clicks+=upgrade2lvl
                prefs.edit().putInt("clicks", clicks).commit()
            }
            text2.text = coins.toString()
            combos.text = clicks.toString()
            cookie1.startAnimation(aniSlide)
            var mp = MediaPlayer.create(this, R.raw.glasssmash)
            mp.start()
        }
        // achievements
        switch1.setOnClickListener{
            intent = Intent(applicationContext, AchievementsActivity::class.java)
            startActivity(intent)
        }

        // upgrades
        switch2.setOnClickListener{
            intent = Intent(applicationContext, UpgradesActivity::class.java)
            startActivity(intent)
        }

        // upgrade1 button actions
        // more cookies per click

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            intent = Intent(applicationContext, SettingsActivity::class.java)
            startActivity(intent)
            true
        }

        R.id.action_exitgame -> {
            finish()
            exitProcess(0)
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }

    }
}

