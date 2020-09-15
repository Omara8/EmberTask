package com.planatech.embertask.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.planatech.embertask.BuildConfig
import com.planatech.embertask.home.repository.HomeRepository
import com.planatech.embertask.home.model.Article

class HomeViewModel : ViewModel() {

    private val homeRepository = HomeRepository()
    private var _articleList: MutableLiveData<List<Article>>? = null

    val articleList: LiveData<List<Article>>
        get() {
            if (_articleList == null)
                _articleList = MutableLiveData()
            return _articleList!!
        }

    fun loadArticles() {
        homeRepository.loadArticles("us", BuildConfig.API_KEY, {
            _articleList?.postValue(it?.articles)
        },{
            Log.e("ViewModel", "loading articles failed")
        })
    }

}