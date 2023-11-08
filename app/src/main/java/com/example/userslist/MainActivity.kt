package com.example.userslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var rvMain : RecyclerView
    lateinit var myAdapter: MyAdapter

    var BASE_URL = "https://dummyjson.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain = findViewById(R.id.recycler_view)

        rvMain.layoutManager = LinearLayoutManager(this)

        getAllData()

    }

    private fun getAllData() {

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        var retroData = retrofit.getData()

        retroData.enqueue(object : Callback<UsersResponse> {
            override fun onResponse(
                call: Call<UsersResponse>,
                response: Response<UsersResponse>
            ) {
                val usersResponse = response.body()
                if (usersResponse != null) {
                    val data = usersResponse.users
                    myAdapter = MyAdapter(baseContext, data)
                    rvMain.adapter = myAdapter
                    Log.d("data", data.toString())
                }
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                // TODO: Handle the failure
            }
        })
    }
}