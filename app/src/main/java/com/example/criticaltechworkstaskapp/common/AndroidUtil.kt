package com.example.criticaltechworkstaskapp.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


fun AppCompatActivity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun showSnackbar(view: View, string: String) {

        Snackbar.make(view, string, Snackbar.LENGTH_LONG).show()
    }

fun formatDateTime(date: String?): String {
    return try{
        val parsing = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val output = SimpleDateFormat("dd/MMM/yyyy h:mm a", Locale.getDefault())
        output.format(parsing.parse(date ?: "") ?: Date())
    }catch (ex: Exception){
        ex.printStackTrace()
        formatDateTime2(date)
    }
}

fun formatDateTime2(date: String?): String {
    return try{
        val parsing = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val output = SimpleDateFormat("dd/MMM/yyyy h:mm a", Locale.getDefault())
        output.format(parsing.parse(date ?: "") ?: Date())
    }catch (ex: Exception){
        ex.printStackTrace()
        ""
    }
}


@Suppress("DEPRECATION")
fun getConnectionType(context: Context): Boolean {
    var result = false
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        cm?.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    result = true
                } else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    result = true
                }
            }
        }
    } else {
        cm?.run {
            cm.activeNetworkInfo?.run {
                if (type == ConnectivityManager.TYPE_WIFI) {
                    result = true
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    result = true
                }
            }
        }
    }
    return result

}

