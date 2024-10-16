package com.example.nucleadandroid_hw2

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment


class FragmentBA : Fragment(R.layout.fragment_ba) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonOpenFragmentBB = view.findViewById<Button>(R.id.button_to_fragment_BB)
        buttonOpenFragmentBB.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentBB())
                .addToBackStack(null)
                .commit()
        }

        // Получаем цвет от FragmentBB через FragmentResultListener
        parentFragmentManager.setFragmentResultListener("requestColor", this) { key, bundle ->
            val color = bundle.getInt("color")
            view.setBackgroundColor(color)
        }
    }
}

