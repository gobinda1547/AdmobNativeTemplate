package com.gobinda.admob.nativetemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addObserversAndListeners()
    }

    private fun addObserversAndListeners() {
        findViewById<Button>(R.id.small_ad_button).setOnClickListener {
            handleSmallAdButtonClicked()
        }
        findViewById<Button>(R.id.medium_ad_button).setOnClickListener {
            handleMediumAdButtonClicked()
        }
        findViewById<Button>(R.id.large_ad_button).setOnClickListener {
            handleLargeAdButtonClicked()
        }
    }

    private fun handleSmallAdButtonClicked() {
        startActivity(Intent(this, SmallAdActivity::class.java))
    }

    private fun handleMediumAdButtonClicked() {
        startActivity(Intent(this, MediumAdActivity::class.java))
    }

    private fun handleLargeAdButtonClicked() {
        startActivity(Intent(this, LargeAdActivity::class.java))
    }
}