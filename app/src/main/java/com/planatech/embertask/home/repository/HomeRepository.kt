package com.planatech.embertask.home.repository

import com.planatech.embertask.common.network.RetrofitBuilder
import com.planatech.embertask.home.model.LoadArticlesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository {

    private val retrofit = RetrofitBuilder.getRetrofit()
    private val client = retrofit.create(HomeAPI::class.java)

    fun loadArticles(country: String, apiKey: String, successCallback: (LoadArticlesResponse?) -> Unit, failureCallback: () -> Unit) {
        client.loadArticles(country, apiKey).enqueue(object : Callback<LoadArticlesResponse> {
            override fun onResponse(call: Call<LoadArticlesResponse>, response: Response<LoadArticlesResponse>) {
                successCallback(response.body())
            }

            override fun onFailure(call: Call<LoadArticlesResponse>, t: Throwable) {
                failureCallback()
            }
        })
    }

}