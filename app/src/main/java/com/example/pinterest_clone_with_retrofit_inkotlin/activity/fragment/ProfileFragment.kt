package com.example.pinterest_clone_with_retrofit_inkotlin.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.pinterest_clone_with_retrofit_inkotlin.R
import com.google.android.material.appbar.CollapsingToolbarLayout

class ProfileFragment : Fragment() {

    private lateinit var collapsingTBR: CollapsingToolbarLayout
    private lateinit var fm_inToolbar: FrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        collapsingTBR = view.findViewById(R.id.collapsingTBR)
        fm_inToolbar = view.findViewById(R.id.fm_inToolbar)

        collapsingTBR
    }

}