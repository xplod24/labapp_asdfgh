package com.app.labapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.isVisible

class AchievementsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        val prefs = getSharedPreferences("PREF_NAME", MODE_PRIVATE)
        val edit: SharedPreferences.Editor = prefs.edit()
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

        if (ach_first){
            findViewById<LinearLayout>(R.id.ach_firsttap).visibility = View.VISIBLE
        }
        if (ach_1000){
            findViewById<LinearLayout>(R.id.ach_1000).visibility = View.VISIBLE
        }
        if (ach_10000){
            findViewById<LinearLayout>(R.id.ach_10000).visibility = View.VISIBLE
        }
        if (ach_100000){
            findViewById<LinearLayout>(R.id.ach_100000).visibility = View.VISIBLE
        }
        if (ach_1000000){
            findViewById<LinearLayout>(R.id.ach_1000000).visibility = View.VISIBLE
        }
        if (ach_up1_5){
            findViewById<LinearLayout>(R.id.up1_5).visibility = View.VISIBLE
        }
        if (ach_up1_10){
            findViewById<LinearLayout>(R.id.up1_10).visibility = View.VISIBLE
        }
        if (ach_up1_15){
            findViewById<LinearLayout>(R.id.up1_15).visibility = View.VISIBLE
        }
        if (ach_up1_20){
            findViewById<LinearLayout>(R.id.up1_20).visibility = View.VISIBLE
        }
        if (ach_up2_5){
            findViewById<LinearLayout>(R.id.up2_5).visibility = View.VISIBLE
        }
        if (ach_up2_10){
            findViewById<LinearLayout>(R.id.up2_10).visibility = View.VISIBLE
        }
        if (ach_up2_15){
            findViewById<LinearLayout>(R.id.up2_15).visibility = View.VISIBLE
        }
        if (ach_up2_20){
            findViewById<LinearLayout>(R.id.up2_20).visibility = View.VISIBLE
        }
        if (ach_up3_5){
            findViewById<LinearLayout>(R.id.up3_5).visibility = View.VISIBLE
        }
        if (ach_up3_10){
            findViewById<LinearLayout>(R.id.up3_10).visibility = View.VISIBLE
        }
        if (ach_up3_15){
            findViewById<LinearLayout>(R.id.up3_15).visibility = View.VISIBLE
        }
        if (ach_up3_20){
            findViewById<LinearLayout>(R.id.up3_20).visibility = View.VISIBLE
        }
        if (ach_first&&ach_1000&&ach_10000&&ach_100000&&ach_1000000&&ach_up1_10&&ach_up1_15&&ach_up1_20&&ach_up1_5&&ach_up2_10&&ach_up2_15&&ach_up2_20&&ach_up2_5&&ach_up3_10&&ach_up3_15&&ach_up3_20&&ach_up3_5){
            edit.putBoolean("ach_all", true)

        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return true
    }
}