package com.example.nuclearandroid_hw2

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import java.util.Random


class FragmentBB : Fragment(R.layout.fragment_bb) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonSendColor = view.findViewById<Button>(R.id.button_send_color)
        buttonSendColor.setOnClickListener {
            val rnd = Random()
            val randomColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))


            val result = Bundle()
            result.putInt("color", randomColor)
            parentFragmentManager.setFragmentResult("requestColor", result)

            if (resources.configuration.orientation != Configuration.ORIENTATION_LANDSCAPE) {
                parentFragmentManager.popBackStack()
            }

        }
    }
}

