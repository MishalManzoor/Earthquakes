package com.test1.earthquakes.networkConnection

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.os.Build
import androidx.lifecycle.LiveData

class Connection(private val context : Context) : LiveData<Boolean>() {

    private var connectivityManager : ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE)
        as ConnectivityManager

    private lateinit var  networkConnectionCallback :
        ConnectivityManager.NetworkCallback

    override fun onActive() {
        super.onActive()

        updateNetworkConnection()

        when{
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {

                connectivityManager
                    .registerDefaultNetworkCallback(connectionCallback())
            }
            else -> {
                context.registerReceiver(
                    networkReceiver,
                    IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                )
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        try{
            connectivityManager.unregisterNetworkCallback(connectionCallback())
        }
        catch (e : IllegalArgumentException){
            e.printStackTrace()
        }
    }

    private fun connectionCallback() :
            ConnectivityManager.NetworkCallback{

                networkConnectionCallback =
                    object : ConnectivityManager.NetworkCallback() {

                        override fun onAvailable(network: Network) {
                            super.onAvailable(network)

                            postValue(true)
                        }

                        override fun onLost(network: Network) {
                            super.onLost(network)
                            postValue(false)
                        }
                    }
        return networkConnectionCallback
            }

    private fun updateNetworkConnection(){
        val networkConnect : NetworkInfo? =
            connectivityManager.activeNetworkInfo

        postValue(networkConnect?.isConnected == true)
    }

    private val networkReceiver = object : BroadcastReceiver(){

        override fun onReceive(p0: Context?, p1: Intent?) {
           updateNetworkConnection()
        }
    }
}