package com.example.earthquakes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.earthquakes.fragmentAdapter.FragmentClass
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionBar?.hide()

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager2)

        tabLayout.addTab(tabLayout.newTab().setText("Latest"))
        tabLayout.addTab(tabLayout.newTab().setText("Last Week"))
        tabLayout.addTab(tabLayout.newTab().setText("Last Month"))

        val fragmentManager = supportFragmentManager

        val adapter = FragmentClass(fragmentManager, lifecycle)
        viewPager.adapter = adapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }
}