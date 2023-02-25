package com.example.lithoapp.domain

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.lithoapp.ui.SecondActivity
import java.util.regex.Pattern

class Validations(ctx: Context) {

    private val ctx: Context
    private val mobilePref = PhonePreference(ctx)
    private val phoneRegex = "^[+]?[(]?\\d{3}[)]?[-\\s.]?\\d{3}[-\\s.]?\\d{4,6}\$"

    init {
        this.ctx = ctx
    }

    fun validatePhone(phoneNumber: String){
        if (phoneNumber.isNotEmpty()) {
            if (Pattern.matches(phoneRegex, phoneNumber)) {
                Toast.makeText(
                    ctx, "Phone number is valid", Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(ctx, SecondActivity::class.java)
                ContextCompat.startActivity(ctx, intent, null)
                mobilePref.saveData(phoneNumber)
            } else {
                Toast.makeText(
                    ctx, "Please enter a valid Phone number", Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                ctx, "Please fill the input fields", Toast.LENGTH_SHORT
            ).show()
        }
    }
}