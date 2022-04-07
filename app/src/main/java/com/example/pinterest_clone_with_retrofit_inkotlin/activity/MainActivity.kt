package com.example.pinterest_clone_with_retrofit_inkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.pinterest_clone_with_retrofit_inkotlin.R
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.fragment.MessageFragment
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.fragment.PhotosFragment
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.fragment.ProfileFragment
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.fragment.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.reflect.Array.newInstance

class MainActivity : AppCompatActivity() {
    private var isLightStatusBar: Boolean = false
    private lateinit var fm_fragments: FrameLayout
    private lateinit var bottom_nav: BottomNavigationView

    // fragments
    private lateinit var photosFragment: PhotosFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var messageFragment: MessageFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        initFragments()
        initViews()
    }

    private fun initFragments() {
        photosFragment = PhotosFragment.newInstance()!!
        searchFragment = SearchFragment.newInstance()!!
        messageFragment = MessageFragment.newInstance()!!
    }

    private fun initViews() {
        fm_fragments = findViewById(R.id.fm_fragments)

        replaceFragment(ProfileFragment())
        bottom_nav = findViewById(R.id.bottom_navigation)
        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> replaceFragment(photosFragment)
                R.id.nav_search -> replaceFragment(searchFragment)
                R.id.nav_message -> replaceFragment(messageFragment)
                R.id.nav_profile -> replaceFragment(ProfileFragment())
            }
            true
        }
    }


    fun replaceFragment(fragment: Fragment) {
        val backStateName = fragment.javaClass.name
        val manager = supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val ft = manager.beginTransaction()
            ft.replace(R.id.fm_fragments, fragment)
            ft.addToBackStack(backStateName)
            ft.commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1)
            finish()
        else if (supportFragmentManager.fragments.any { it is SearchFragment }) {
            if (searchFragment.onBackPressed())
                super.onBackPressed()
        } else
            super.onBackPressed()
    }

    fun showBottomNavigation() {
        bottom_nav.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        bottom_nav.visibility = View.GONE
    }

}