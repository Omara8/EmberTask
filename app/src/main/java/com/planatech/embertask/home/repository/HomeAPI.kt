package com.planatech.embertask.home.repository

import com.planatech.embertask.home.model.LoadArticlesResponse
import com.planatech.embertask.home.model.LoadSourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeAPI {

    @GET("top-headlines")
    fun loadArticles(
        @Query("country") country: String?,
        @Query("sources") sources: String?,
        @Query("apiKey") apiKey: String
    ): Call<LoadArticlesResponse>

    @GET("sources")
    fun loadSources(
        @Query("apiKey") apiKey: String
    ): Call<LoadSourcesResponse>

}