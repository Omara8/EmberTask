package com.planatech.embertask.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.planatech.embertask.BuildConfig
import com.planatech.embertask.home.model.Article
import com.planatech.embertask.home.model.Source
import com.planatech.embertask.home.repository.HomeRepository

class HomeViewModel : ViewModel() {

    private val homeRepository = HomeRepository()
    private val apiKey = BuildConfig.API_KEY
    private var _articleList: MutableLiveData<List<Article>>? = null
    private var _sourcesList: MutableLiveData<List<Source>>? = null

    val articleList: LiveData<List<Article>>
        get() {
            if (_articleList == null)
                _articleList = MutableLiveData()
            return _articleList!!
        }

    val sourcesList: LiveData<List<Source>>
        get() {
            if (_sourcesList == null)
                _sourcesList = MutableLiveData()
            return _sourcesList!!
        }

    fun loadArticles(country: String?, source: String?) {
        homeRepository.loadArticles(country, source, apiKey, {
            _articleList?.postValue(it?.articles)
        }, {
            Log.e("ViewModel", "loading articles failed")
        })
    }

    fun loadSources() {
        homeRepository.loadSources(apiKey, {
            _sourcesList?.postValue(it?.sources)
        }, {
            Log.e("ViewModel", "loading sources failed")
        })
    }

}