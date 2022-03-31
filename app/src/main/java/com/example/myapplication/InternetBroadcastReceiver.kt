package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class InternetBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetwork
        if(cm.getNetworkCapabilities(netInfo)!=null)
            Toast.makeText(context, "Internet connection established", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(context, "Internet connection lost", Toast.LENGTH_LONG).show()
    }
}