package com.app.labapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class UpgradesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrades)
//        val prefs = getSharedPreferences("com.app.labapp", MODE_PRIVATE)
//        var upgrade1lvl = prefs.getInt("upgrade1lvl", 0)
//        var upgrade2lvl = prefs.getInt("upgrade2lvl", 0)
//        var upgrade3lvl = prefs.getInt("upgrade3lvl", 0)
//        var upgrade1cost = prefs.getInt("upgrade1cost", 2)
//        var upgrade2cost = prefs.getInt("upgrade2cost", 2)
//        var upgrade3cost = prefs.getInt("upgrade3cost", 2)
//        var coins = prefs.getInt("coins", 0)
//        var running = prefs.getBoolean("running", false)
//        var clicks = prefs.getInt("clicks", 0)
//        var tickrate = prefs.getLong("tickrate", 2000)
//        val upgrade1btn = findViewById<Button>(R.id.button_upgrade1)
//        val upgrade2btn = findViewById<Button>(R.id.button_upgrade2)
//        val upgrade3btn = findViewById<Button>(R.id.button_upgrade3)
//        val str1 = findViewById<TextView>(R.id.upgrade1)
//        val str2 = findViewById<TextView>(R.id.upgrade1_cost)
//        val str3 = findViewById<TextView>(R.id.upgrade2)
//        val str4 = findViewById<TextView>(R.id.upgrade2_cost)
//        val str5 = findViewById<TextView>(R.id.upgrade3)
//        val str6 = findViewById<TextView>(R.id.upgrade3_cost)
//        val text2 = findViewById<TextView>(R.id.cookiesCount)
//        val combos = findViewById<TextView>(R.id.comboView)
//        str1.text = "$upgrade1lvl"
//        str2.text = "$upgrade1cost"
//        str3.text = "$upgrade2lvl"
//        str4.text = "$upgrade2cost"
//        str5.text = "$upgrade3lvl"
//        str6.text = "$upgrade3cost"
//
//        upgrade1btn.setOnClickListener{
//            if (coins >= upgrade1cost){
//                coins-=upgrade1cost
//                upgrade1cost*=2
//                upgrade1lvl++
//                prefs.edit().putInt("coins", coins).commit()
//                prefs.edit().putInt("upgrade1cost", upgrade1cost).commit()
//                prefs.edit().putInt("upgrade1lvl", upgrade1lvl).commit()
//                text2.text = coins.toString()
//                combos.text = clicks.toString()
//                str1.text = "$upgrade1lvl"
//                str2.text =  "$upgrade1cost"
//                val snack = Snackbar.make(it,"Upgraded! Click power: $upgrade1lvl", Snackbar.LENGTH_LONG)
//                snack.show()
//            } else {
//                val snack = Snackbar.make(it,"Not enough cookies!", Snackbar.LENGTH_LONG)
//                snack.show()
//            }
//        }
//        // upgrade2 button actions
//        // add more cookies to combo
//        upgrade2btn.setOnClickListener{
//            if (coins >= upgrade2cost){
//                coins-=upgrade2cost
//                upgrade2cost*=2
//                upgrade2lvl++
//                prefs.edit().putInt("coins", coins).commit()
//                prefs.edit().putInt("upgrade2cost", upgrade2cost).commit()
//                prefs.edit().putInt("upgrade2lvl", upgrade2lvl).commit()
//                text2.text = coins.toString()
//                combos.text = clicks.toString()
//                str3.text = "$upgrade2lvl"
//                str4.text = "$upgrade2cost"
//                val snack = Snackbar.make(it,"Upgraded! Combo x$upgrade2lvl", Snackbar.LENGTH_LONG)
//                snack.show()
//            } else {
//                val snack = Snackbar.make(it,"Not enough cookies!", Snackbar.LENGTH_LONG)
//                snack.show()
//            }
//        }
//        // upgrade3 button actions
//        // lower combo tick rate
//        upgrade3btn.setOnClickListener{
//            if (coins >= upgrade3cost){
//                if (tickrate <= 10){
//                    val snack = Snackbar.make(it,"Reached Max LVL!", Snackbar.LENGTH_LONG)
//                    snack.show()
//                } else {
//                    coins-=upgrade3cost
//                    upgrade3cost*=2
//                    upgrade3lvl++
//                    prefs.edit().putInt("coins", coins).commit()
//                    prefs.edit().putInt("upgrade3cost", upgrade3cost).commit()
//                    prefs.edit().putInt("upgrade3lvl", upgrade3lvl).commit()
//                    text2.text = coins.toString()
////                    combos.text = clicks.toString()
//                    str5.text = "$upgrade3lvl"
//                    str6.text = "$upgrade3cost"
//                }
//                if (tickrate in 1501..2000){
//                    tickrate-=100
//                    prefs.edit().putLong("tickrate", tickrate).commit()
////                    String.format(str7, tickrate)
//                    val snack = Snackbar.make(it,"Upgraded! Current tick rate: $tickrate", Snackbar.LENGTH_LONG)
//                    snack.show()
//                } else if (tickrate in 1001..1500){
//                    tickrate-=80
//                    prefs.edit().putLong("tickrate", tickrate).commit()
////                    String.format(str7, tickrate)
//                    val snack = Snackbar.make(it,"Upgraded! Current tick rate: $tickrate", Snackbar.LENGTH_LONG)
//                    snack.show()
//                } else if (tickrate in 501..1000){
//                    tickrate-=50
//                    prefs.edit().putLong("tickrate", tickrate).commit()
////                    String.format(str7, tickrate)
//                    val snack = Snackbar.make(it,"Upgraded! Current tick rate: $tickrate", Snackbar.LENGTH_LONG)
//                    snack.show()
//                } else if (tickrate in 101..500) {
//                    tickrate-=20
//                    prefs.edit().putLong("tickrate", tickrate).commit()
////                    String.format(str7, tickrate)
//                    val snack = Snackbar.make(it,"Upgraded! Current tick rate: $tickrate", Snackbar.LENGTH_LONG)
//                    snack.show()
//                } else if (tickrate in 10..100) {
//                    tickrate-=10
//                    prefs.edit().putLong("tickrate", tickrate).commit()
////                    String.format(str7, tickrate)
//                    val snack = Snackbar.make(it,"Upgraded! Current tick rate: $tickrate", Snackbar.LENGTH_LONG)
//                    snack.show()
//                } else {
//                    prefs.edit().putLong("tickrate", 10).apply()
////                    String.format(str7, tickrate)
//                }
//            } else {
//                val snack = Snackbar.make(it,"Not enough cookies!", Snackbar.LENGTH_LONG)
//                snack.show()
//            }
//        }
    }
}