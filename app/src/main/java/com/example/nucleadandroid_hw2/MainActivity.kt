package com.example.nucleadandroid_hw2

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

const val IS_FRAGMENT_ACTIVE = "is_fragment_active"
const val WITH_FRAGMENT = "with_fragment"


class MainActivity : AppCompatActivity() {
    var isFragmentActive: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Log.d("ActivityA", "Task ID: $taskId")

        isFragmentActive = savedInstanceState?.getBoolean(IS_FRAGMENT_ACTIVE)?:false

        val buttonToActivityB = findViewById<Button>(R.id.button_to_activity_B)
        buttonToActivityB.setOnClickListener {
            isFragmentActive = false

            val intent = Intent(this, ActivityB::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }


        if (isFragmentActive) {
//            if (savedInstanceState == null) {
                clearFragmentManagerWhenRotating()
                openFragmentsBx()
//            }
        }
        val buttonToFragmentB = findViewById<Button>(R.id.button_to_fragment_B)
        buttonToFragmentB.setOnClickListener {
            isFragmentActive = true
            openFragmentsBx()
        }


        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Проверяем, есть ли фрагмент в BackStack
                val fragmentManager = supportFragmentManager
                if (fragmentManager.backStackEntryCount > 0) {
                    // Выполняем действие при закрытии фрагмента
                    isFragmentActive = false
                    // Удаляем фрагмент с BackStack
                    fragmentManager.popBackStack()
                } else {
                    // Если нет фрагментов, вызываем стандартное поведение кнопки "Назад"
                    finish()
                }
            }
        })

    }




    // ACTIVITY
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d("ActivityAnewIntent", "onNewIntent called")
        Toast.makeText(this, "onNewIntent called", Toast.LENGTH_SHORT).show()
    }



    // FRAGMENT
    private fun openFragmentsBx() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val fragmentContainer = findViewById<FrameLayout>(R.id.fragment_container)
            fragmentContainer.visibility = View.VISIBLE

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentBA())
                .addToBackStack(null)
                .commit()

        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_a, FragmentBA())
                .replace(R.id.fragment_container_b, FragmentBB())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_FRAGMENT_ACTIVE, isFragmentActive)
    }


    private fun clearFragmentManagerWhenRotating() {
        if (//resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE &&
            supportFragmentManager.backStackEntryCount != 0) {
            supportFragmentManager.popBackStack()
        }
    }
}