 package com.example.runningapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.runningapp.R
import com.example.runningapp.db.RunDao
import com.example.runningapp.other.Constant.ACTION_SHOW_TRACKING_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

 @AndroidEntryPoint
 class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // for a relaunch after destroyed
        navigateToTrackingFragmentIfNeeded(intent)

        setSupportActionBar(toolbar)
        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())
        bottomNavigationView.setOnNavigationItemReselectedListener { /**NOOP**/ }

        //specific fragment not all
        navHostFragment.findNavController()
            .addOnDestinationChangedListener { _, destination, _ ->
                when(destination.id){
                    R.id.settingsFragment,R.id.runFragment,R.id.statisticsFragment->
                        bottomNavigationView.visibility = View.VISIBLE
                    else -> bottomNavigationView.visibility = View.GONE
                }
            }

    }

     // activity not destroyed
     override fun onNewIntent(intent: Intent?) {
         super.onNewIntent(intent)
         navigateToTrackingFragmentIfNeeded(intent)
     }

     private fun navigateToTrackingFragmentIfNeeded(intent: Intent?){
         if(intent?.action == ACTION_SHOW_TRACKING_FRAGMENT){
             navHostFragment.findNavController().navigate(R.id.action_global_trackingFragment)
         }
     }
}