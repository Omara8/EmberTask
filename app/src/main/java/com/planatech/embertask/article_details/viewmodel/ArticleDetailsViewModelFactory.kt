package com.planatech.embertask.article_details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ArticleDetailsViewModelFactory(
    private val articleAuthor: String?, private val articleTitle: String?,
    private val articleContent: String?, private val articleImageUrl: String?,
    private val articleSourceName: String?, private val articleSourceUrl: String?
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArticleDetailsViewModel(
            articleAuthor, articleTitle, articleContent,
            articleImageUrl, articleSourceName, articleSourceUrl
        ) as T
    }

}