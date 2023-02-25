package com.example.lithoapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lithoapp.domain.Adapter
import com.example.lithoapp.domain.PhonePreference
import com.example.lithoapp.R
import com.example.lithoapp.repository.ApiResponse
import com.example.lithoapp.repository.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SecondActivity : AppCompatActivity() {
    private lateinit var mobilePref: PhonePreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.json_data)
        mobilePref = PhonePreference(this)
        val signOutButton: Button = findViewById(R.id.signOutButton)
        signOutButton.setOnClickListener { logoutUser() }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(applicationContext)

        val retrofit = Retrofit.Builder().baseUrl("https://bfsd.uat.bfsgodirect.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI = retrofit.create(RetrofitAPI::class.java)
        val call: Call<ApiResponse?>? = retrofitAPI.getData()

        call?.enqueue(object : Callback<ApiResponse?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ApiResponse?>, response: Response<ApiResponse?>) {
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        val customAdapter = Adapter(res.personalizationSequence, this@SecondActivity)
                        recyclerView.layoutManager = layoutManager
                        recyclerView.adapter = customAdapter
                        customAdapter.notifyDataSetChanged()
                    }
                }
            }
            override fun onFailure(call: Call<ApiResponse?>, t: Throwable) {
                Log.e("data error", t.toString())
            }
        })
    }

    private fun logoutUser() {
        mobilePref.deleteData()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}

