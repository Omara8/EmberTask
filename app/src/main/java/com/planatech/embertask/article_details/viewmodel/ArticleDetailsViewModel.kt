package com.planatech.embertask.article_details.viewmodel

import androidx.lifecycle.ViewModel

class ArticleDetailsViewModel(
    val articleAuthor: String?, val articleTitle: String?,
    val articleContent: String?, val articleImageUrl: String?,
    val articleSourceName: String?, val articleSourceUrl: String?
) : ViewModel() {

}