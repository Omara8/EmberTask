package com.planatech.embertask.home.repository

import com.planatech.embertask.common.network.RetrofitBuilder
import com.planatech.embertask.home.model.LoadArticlesResponse
import com.planatech.embertask.home.model.LoadSourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository {

    private val retrofit = RetrofitBuilder.getRetrofit()
    private val client = retrofit.create(HomeAPI::class.java)

    fun loadArticles(
        country: String?,
        source: String?,
        apiKey: String,
        successCallback: (LoadArticlesResponse?) -> Unit,
        failureCallback: () -> Unit
    ) {
        client.loadArticles(country, source, apiKey).enqueue(object : Callback<LoadArticlesResponse> {
            override fun onResponse(
                call: Call<LoadArticlesResponse>,
                response: Response<LoadArticlesResponse>
            ) {
                successCallback(response.body())
            }

            override fun onFailure(call: Call<LoadArticlesResponse>, t: Throwable) {
                failureCallback()
            }
        })
    }

    fun loadSources(apiKey: String,
                    successCallback: (LoadSourcesResponse?) -> Unit,
                    failureCallback: () -> Unit){
        client.loadSources(apiKey).enqueue(object : Callback<LoadSourcesResponse> {
            override fun onResponse(
                call: Call<LoadSourcesResponse>,
                response: Response<LoadSourcesResponse>
            ) {
                successCallback(response.body())
            }

            override fun onFailure(call: Call<LoadSourcesResponse>, t: Throwable) {
                failureCallback()
            }
        })
    }
}