package com.example.covidtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.databinding.ActivityCountyListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountyListActivity : AppCompatActivity() {
    companion object{
        const val TAG = "CountyListActivity"
        const val STATE = "CA"
    }
    private lateinit var binding: ActivityCountyListBinding
    lateinit var adapter : CountyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCountyDataByStateApiCall(STATE)
    }

    private fun getCountyDataByStateApiCall(state: String) {
        val covidDataService = RetrofitHelper.getInstance().create(CovidDataService::class.java)
        //create our call object
        val countyDataCall = covidDataService.getCountyDataByState(state,Constants.API_KEY)
        //execute our call object and make an async call
        countyDataCall.enqueue(object: Callback<List<CountyData>>{
            //when it works:
            override fun onResponse(
                call: Call<List<CountyData>>,
                response: Response<List<CountyData>>
            ) {
                Log.d(TAG,"onResponse:${response.body()}")
                if(response.body() != null)
                {
                    adapter = CountyAdapter(response.body()!!)
                }
                binding.countyListRecyclerView.adapter = adapter
                binding.countyListRecyclerView.layoutManager = LinearLayoutManager(this@CountyListActivity)
                //3 lines to create adapter set adapter to recyclerview and set linear
                //layout manager for the adater
                //responce .body is List<CountyData>
            }
            // when it doesnt work
            override fun onFailure(call: Call<List<CountyData>>, t: Throwable) {
                Log.d(TAG,"onFailure:${t.message}")             }
        })
        //all the code here... we might not have the data back yet at this point
        //we cant put what to do with the data below the call
    }
}