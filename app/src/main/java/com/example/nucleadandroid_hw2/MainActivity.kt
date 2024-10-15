package com.example.nucleadandroid_hw2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val buttonToActivityB = findViewById<Button>(R.id.button_to_activity_B)

        buttonToActivityB.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        val buttonToFragmentB = findViewById<Button>(R.id.button_to_fragment_B)

        buttonToFragmentB.setOnClickListener {

        }

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
    }
}