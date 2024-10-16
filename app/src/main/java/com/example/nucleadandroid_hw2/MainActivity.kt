package com.example.nucleadandroid_hw2

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Log.d("ActivityA", "Task ID: $taskId")


        val buttonToActivityB = findViewById<Button>(R.id.button_to_activity_B)
        buttonToActivityB.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }


        val buttonToFragmentB = findViewById<Button>(R.id.button_to_fragment_B)
        buttonToFragmentB.setOnClickListener {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                val fragmentContainer = findViewById<FrameLayout>(R.id.fragment_container)
                fragmentContainer.visibility = View.VISIBLE

                // Загрузка FragmentBA в контейнер
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, FragmentBA())
                    .addToBackStack(null)
                    .commit()
            } else {
                // В альбомной ориентации - оба фрагмента одновременно
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_a, FragmentBA())
                    .replace(R.id.fragment_container_b, FragmentBB())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d("ActivityAnewIntent", "onNewIntent called")
        Toast.makeText(this, "onNewIntent called", Toast.LENGTH_SHORT).show()
    }
}