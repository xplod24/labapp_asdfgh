package com.app.labapp

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class UpgradesActivity : AppCompatActivity() {
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
        val a1 = (R.string.not_enough)
        val a2 = (R.string.upgraded)

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
        //cookies text
        text2.text = "$coins"
        Handler(Looper.getMainLooper()).post(Runnable {
            text2.text = coins.toString()
        })

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
                if (upgrade1lvl == 5){
                    edit.putBoolean("ach_up1_5", true)
                    val snack = Snackbar.make(it,"Achievement unlocked!", Snackbar.LENGTH_LONG)
                    snack.show()
                }
                if (upgrade1lvl == 10){
                    edit.putBoolean("ach_up1_10", true)
                    val snack = Snackbar.make(it,"Achievement unlocked!", Snackbar.LENGTH_LONG)
                    snack.show()
                }
                if (upgrade1lvl == 15){
                    edit.putBoolean("ach_up1_10", true)
                    val snack = Snackbar.make(it,"Achievement unlocked!", Snackbar.LENGTH_LONG)
                    snack.show()
                }
                if (upgrade1lvl == 20){
                    edit.putBoolean("ach_up1_10", true)
                    val snack = Snackbar.make(it,"Achievement unlocked!", Snackbar.LENGTH_LONG)
                    snack.show()
                }
                edit.apply()
                text2.text = coins.toString()
                str1.text = "$upgrade1lvl"
                str2.text =  "$upgrade1cost"
                val snack = Snackbar.make(it,a2, Snackbar.LENGTH_LONG)
                snack.show()

            } else {
                val snack = Snackbar.make(it,a1, Snackbar.LENGTH_LONG)
                snack.show()
            }
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
                edit.apply()
                text2.text = coins.toString()
                str3.text = "$upgrade2lvl"
                str4.text = "$upgrade2cost"
                val snack = Snackbar.make(it,a2, Snackbar.LENGTH_LONG)
                snack.show()
            } else {
                val snack = Snackbar.make(it,a1, Snackbar.LENGTH_LONG)
                snack.show()
            }
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
                if (tickrate in 1501..2000){
                    tickrate-=100
                    edit.putLong("tickrate", tickrate)
                    edit.apply()
                    val snack = Snackbar.make(it,a2, Snackbar.LENGTH_LONG)
                    snack.show()
                } else if (tickrate in 1001..1500){
                    tickrate-=80
                    edit.putLong("tickrate", tickrate)
                    edit.apply()
                    val snack = Snackbar.make(it,a2, Snackbar.LENGTH_LONG)
                    snack.show()
                } else if (tickrate in 501..1000){
                    tickrate-=50
                    edit.putLong("tickrate", tickrate)
                    edit.apply()
                    val snack = Snackbar.make(it,a2, Snackbar.LENGTH_LONG)
                    snack.show()
                } else if (tickrate in 101..500) {
                    tickrate-=20
                    edit.putLong("tickrate", tickrate)
                    edit.apply()
                    val snack = Snackbar.make(it,a2, Snackbar.LENGTH_LONG)
                    snack.show()
                } else if (tickrate in 10..100) {
                    tickrate-=10
                    edit.putLong("tickrate", tickrate)
                    edit.apply()
                    val snack = Snackbar.make(it,a2, Snackbar.LENGTH_LONG)
                    snack.show()
                } else {
                    edit.putLong("tickrate", 10)
                    edit.apply()
                }
            } else {
                val snack = Snackbar.make(it,a1, Snackbar.LENGTH_LONG)
                snack.show()
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        onBackPressed()
        return true
    }
}
