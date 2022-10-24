package com.app.labapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import java.util.*
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity() {
    var coins = 0
    var running = false
    var clicks = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var text2 = findViewById<TextView>(R.id.textowo)
        var cookie1 = findViewById<ImageView>(R.id.cookie)
        var combos = findViewById<TextView>(R.id.comboView)

        cookie1.setOnClickListener{
            if (!running){
                running = true
                Thread(Runnable {
                    while(running){
                        Thread.sleep(1000)
                        runOnUiThread{
                            if (clicks>0) {
                                clicks--
                                coins++
                            } else if (clicks == 0){
                                running = false
                            } else {
                                running = false
                            }
                            text2.text = coins.toString()
                            combos.text = clicks.toString()
                        }
                    }
                    Thread.sleep(1000)
                }).start()
            } else {
            }
            coins++
            clicks++
            text2.text = coins.toString()
            combos.text = clicks.toString()
        }


    }
}

