package com.app.labapp


import android.os.Bundle
import java.util.Formatter
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences("com.app.labapp", MODE_PRIVATE)
        val aniSlide = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
        setContentView(R.layout.activity_main)
        var text2 = findViewById<TextView>(R.id.textowo)
        var cookie1 = findViewById<ImageView>(R.id.cookie)
        var combos = findViewById<TextView>(R.id.comboView)
        var upgrade1btn = findViewById<Button>(R.id.button_upgrade1)
        var upgrade2btn = findViewById<Button>(R.id.button_upgrade2)
        var upgrade3btn = findViewById<Button>(R.id.button_upgrade3)
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
        //upgrade 1 texts
        var str1 = findViewById<TextView>(R.id.upgrade1)
        var str2 = findViewById<TextView>(R.id.upgrade1_cost)
        //upgrade 2 texts
        var str3 = findViewById<TextView>(R.id.upgrade2)
        var str4 = findViewById<TextView>(R.id.upgrade2_cost)
        //upgrade 3 texts
        var str5 = findViewById<TextView>(R.id.upgrade3)
        var str6 = findViewById<TextView>(R.id.upgrade3_cost)
        str1.text = "$upgrade1lvl"
        str2.text = "$upgrade1cost"
        str3.text = "$upgrade2lvl"
        str4.text = "$upgrade2cost"
        str5.text = "$upgrade3lvl"
        str6.text = "$upgrade3cost"
        text2.text = "$coins"
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
        }
        // upgrade1 button actions
        // more cookies per click
        upgrade1btn.setOnClickListener{
            if (coins >= upgrade1cost){
                coins-=upgrade1cost
                upgrade1cost*=2
                upgrade1lvl++
                prefs.edit().putInt("coins", coins).commit()
                prefs.edit().putInt("upgrade1cost", upgrade1cost).commit()
                prefs.edit().putInt("upgrade1lvl", upgrade1lvl).commit()
                text2.text = coins.toString()
                combos.text = clicks.toString()
                str1.text = "$upgrade1lvl"
                str2.text = "$upgrade1cost"
//                String.format(str1, upgrade1lvl)
//                String.format(str2, upgrade1cost)
                val snack = Snackbar.make(it,"Upgraded! Click power: $upgrade1lvl",Snackbar.LENGTH_LONG)
                snack.show()
            } else {
                val snack = Snackbar.make(it,"Not enough cookies!",Snackbar.LENGTH_LONG)
                snack.show()
            }
        }
        // upgrade2 button actions
        // add more cookies to combo
        upgrade2btn.setOnClickListener{
            if (coins >= upgrade2cost){
                coins-=upgrade2cost
                upgrade2cost*=2
                upgrade2lvl++
                prefs.edit().putInt("coins", coins).commit()
                prefs.edit().putInt("upgrade2cost", upgrade2cost).commit()
                prefs.edit().putInt("upgrade2lvl", upgrade2lvl).commit()
                text2.text = coins.toString()
                combos.text = clicks.toString()
                str3.text = "$upgrade2lvl"
                str4.text = "$upgrade2cost"
//                String.format(str3, upgrade2lvl)
//                String.format(str4, upgrade2cost)
                val snack = Snackbar.make(it,"Upgraded! Combo x$upgrade2lvl",Snackbar.LENGTH_LONG)
                snack.show()
            } else {
                val snack = Snackbar.make(it,"Not enough cookies!",Snackbar.LENGTH_LONG)
                snack.show()
            }
        }
        // upgrade3 button actions
        // lower combo tick rate
        upgrade3btn.setOnClickListener{
            if (coins >= upgrade3cost){
                if (tickrate <= 10){
                    val snack = Snackbar.make(it,"Reached Max LVL!",Snackbar.LENGTH_LONG)
                    snack.show()
                } else {
                    coins-=upgrade3cost
                    upgrade3cost*=2
                    upgrade3lvl++
                    prefs.edit().putInt("coins", coins).commit()
                    prefs.edit().putInt("upgrade3cost", upgrade3cost).commit()
                    prefs.edit().putInt("upgrade3lvl", upgrade3lvl).commit()
                    text2.text = coins.toString()
                    combos.text = clicks.toString()
                    str5.text = "$upgrade3lvl"
                    str6.text = "$upgrade3cost"
//                    String.format(str5, upgrade3lvl)
//                    String.format(str6, upgrade3cost)
                }
                if (tickrate in 1501..2000){
                    tickrate-=100
                    prefs.edit().putLong("tickrate", tickrate).commit()
//                    String.format(str7, tickrate)
                    val snack = Snackbar.make(it,"Upgraded! Current tick rate: $tickrate",Snackbar.LENGTH_LONG)
                    snack.show()
                } else if (tickrate in 1001..1500){
                    tickrate-=80
                    prefs.edit().putLong("tickrate", tickrate).commit()
//                    String.format(str7, tickrate)
                    val snack = Snackbar.make(it,"Upgraded! Current tick rate: $tickrate",Snackbar.LENGTH_LONG)
                    snack.show()
                } else if (tickrate in 501..1000){
                    tickrate-=50
                    prefs.edit().putLong("tickrate", tickrate).commit()
//                    String.format(str7, tickrate)
                    val snack = Snackbar.make(it,"Upgraded! Current tick rate: $tickrate",Snackbar.LENGTH_LONG)
                    snack.show()
                } else if (tickrate in 101..500) {
                    tickrate-=20
                    prefs.edit().putLong("tickrate", tickrate).commit()
//                    String.format(str7, tickrate)
                    val snack = Snackbar.make(it,"Upgraded! Current tick rate: $tickrate",Snackbar.LENGTH_LONG)
                    snack.show()
                } else if (tickrate in 10..100) {
                    tickrate-=10
                    prefs.edit().putLong("tickrate", tickrate).commit()
//                    String.format(str7, tickrate)
                    val snack = Snackbar.make(it,"Upgraded! Current tick rate: $tickrate",Snackbar.LENGTH_LONG)
                    snack.show()
                } else {
                    prefs.edit().putLong("tickrate", 10).apply()
//                    String.format(str7, tickrate)
                }
            } else {
                val snack = Snackbar.make(it,"Not enough cookies!",Snackbar.LENGTH_LONG)
                snack.show()
            }
        }
    }
}

