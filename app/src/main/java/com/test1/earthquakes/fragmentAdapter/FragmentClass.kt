package com.test1.earthquakes.fragmentAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.test1.earthquakes.fragments.LastMonthFragment
import com.test1.earthquakes.fragments.LastWeekFragment
import com.test1.earthquakes.fragments.LatestFragment

class FragmentClass(fm : FragmentManager, lifecycle: Lifecycle) :
 FragmentStateAdapter(fm , lifecycle){

    override fun getItemCount(): Int {
       return 3
    }

    override fun createFragment(position: Int): Fragment {

       if (position == 1){
          return LastWeekFragment()
       }
       else if(position == 2){
          return LastMonthFragment()
       }
       else
          return LatestFragment()
       }
}