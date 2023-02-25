package com.example.lithoapp.domain

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.core.content.edit


class PhonePreference(context: Context) {
    private val numKey = "number"
    private val loginStatus = "status"
    private val context: Context
    private val sharedPrefs: SharedPreferences

    init {
        this.context = context
        sharedPrefs = context.getSharedPreferences("number", 0)
    }

    fun saveData(phoneNumber:String) {
        sharedPrefs.edit{
            putString(numKey, phoneNumber)
        }
        saveLoginStatus()
        Toast.makeText(context, "Data is saved", Toast.LENGTH_SHORT).show()
    }

    fun getData(): String {
        return sharedPrefs.getString(numKey, "") ?: ""
    }

    private fun saveLoginStatus(){
        sharedPrefs.edit{
            putBoolean(loginStatus, true)
        }
        Toast.makeText(context, "Logged In Successfully", Toast.LENGTH_SHORT).show()
    }

    fun getLoginStatus(): Boolean{
        return sharedPrefs.getBoolean(loginStatus,false)
    }

    fun deleteData(){
        val editor: SharedPreferences.Editor = sharedPrefs.edit()
        editor.clear()
        editor.apply()
        Toast.makeText(context, "Logged Out Successfully", Toast.LENGTH_SHORT).show()
    }
}
