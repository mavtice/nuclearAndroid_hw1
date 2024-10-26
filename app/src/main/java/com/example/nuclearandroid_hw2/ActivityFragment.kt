package com.example.nuclearandroid_hw2

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ActivityFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fragment)
        clearFragmentManagerInHorizontalMode()
    }

    private fun clearFragmentManagerInHorizontalMode() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE &&
            supportFragmentManager.backStackEntryCount != 0) {
            supportFragmentManager.popBackStack()
        }
    }
}
