package com.example.nucleadandroid_hw2

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

const val BACKGROUND_COLOR = "background_color"

class FragmentBA : Fragment(R.layout.fragment_ba) {

    var backgroundColor: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backgroundColor = savedInstanceState?.getInt(BACKGROUND_COLOR)
        backgroundColor?.let { view.setBackgroundColor(it) }


        val buttonToFragmentBB = view.findViewById<Button>(R.id.button_to_fragment_BB)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            buttonToFragmentBB.visibility = View.GONE
        } else {
            buttonToFragmentBB.visibility = View.VISIBLE
        }


        buttonToFragmentBB.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentBB())
                .addToBackStack(null)
                .commit()
        }


        parentFragmentManager.setFragmentResultListener("requestColor", this) { key, bundle ->
            backgroundColor = bundle.getInt("color")
            backgroundColor?.let { view.setBackgroundColor(it) }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        backgroundColor?.let {outState.putInt(BACKGROUND_COLOR, it)}
    }
}

