package com.app.labapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.recreate
import com.google.android.material.snackbar.Snackbar
import java.util.*


class SettingsActivity : AppCompatActivity() {
    private var lang = "pl"
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        val prefs = getSharedPreferences("PREF_NAME", MODE_PRIVATE)
        var sound = prefs.getBoolean("sound", true)
        var animationEnable = prefs.getBoolean("animation", true)
        var storedLang = prefs.getString("lang", "en")

        val a1 = (R.string.soundon)
        val a2 = (R.string.soundoff)
        val a3 = (R.string.animon)
        val a4 = (R.string.animoff)
        val a5 = (R.string.polish)
        val a6 = (R.string.english)

        val toggleSounds = findViewById<Switch>(R.id.switch2)
        val toggleAnimations = findViewById<Switch>(R.id.switch3)
        val toggleLang = findViewById<Switch>(R.id.langSwitch)
        toggleSounds.isChecked = sound
        toggleAnimations.isChecked = animationEnable
        toggleLang.isChecked = storedLang != "pl"
        if(toggleLang.isChecked){
            toggleLang.text="English"
        } else {
            toggleLang.text="Polski"
        }

        toggleSounds?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) prefs.edit().putBoolean("sound", true).apply() else prefs.edit().putBoolean("sound", false).apply()
            val message = if (isChecked) a1 else a2
            Snackbar.make(findViewById(R.id.main_settings), message, Snackbar.LENGTH_SHORT).show()
            Log.d("Soundtoggle", "toggled")
            Log.d("Soundtoggle", "$sound")
//            Toast.makeText(this@SettingsActivity, message, Toast.LENGTH_SHORT).show()
        }
        toggleAnimations?.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) prefs.edit().putBoolean("animation", true).apply() else prefs.edit().putBoolean("animation", false).apply()
            val message = if (isChecked) a3 else a4
            Snackbar.make(findViewById(R.id.main_settings), message, Snackbar.LENGTH_SHORT).show()
            Log.d("Animtoggle", "toggled")
            Log.d("Animtoggle", "$animationEnable")
//            Toast.makeText(this@SettingsActivity, message, Toast.LENGTH_SHORT).show()
        }
        toggleLang?.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                prefs.edit().putString("lang", "en").commit()
                val config = resources.configuration
                val locale = Locale("en")
                Locale.setDefault(locale)
                config.locale = locale
                resources.updateConfiguration(config, resources.displayMetrics)
                this.recreate()
            } else {
                prefs.edit().putString("lang", "pl").commit()
                val config = resources.configuration
                val locale = Locale("pl")
                Locale.setDefault(locale)
                config.locale = locale
                resources.updateConfiguration(config, resources.displayMetrics)
                this.recreate()
            }
            val message = if (isChecked) a6 else a5
            if(isChecked) {
                toggleLang.text = "English"
            } else {
                toggleLang.text = "Polski"
            }
            Snackbar.make(findViewById(R.id.main_settings), message, Snackbar.LENGTH_SHORT).show()
            var storedLang = prefs.getString("lang", "en")
            Log.d("LangToggle", "toggled")
            Log.d("Langtoggle", "$storedLang")
//            Toast.makeText(this@SettingsActivity, message, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        return true
    }
}