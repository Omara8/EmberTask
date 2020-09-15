package com.planatech.embertask.home.model

import java.io.Serializable

data class LoadArticlesResponse(val status: String,
                                val totalResults: Int,
                                val articles: List<Article>): Serializable