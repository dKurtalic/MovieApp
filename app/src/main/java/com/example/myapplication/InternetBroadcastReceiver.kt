package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class InternetBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
      val connectivityManager = (context.getSystemService(Context.CONNECTIVITY_SERVICE))
        if (connectivityManager == null) {
            Toast.makeText(context, "Lost internet connection", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Established internet connection", Toast.LENGTH_SHORT).show()
        }
    }


}