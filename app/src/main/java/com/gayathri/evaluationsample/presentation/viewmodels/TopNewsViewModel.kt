package com.gayathri.evaluationsample.presentation.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gayathri.domain.models.NewsArticle
import com.gayathri.domain.usecases.TopNewsUseCase
import com.gayathri.evaluationsample.utils.AppUtils.logMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class TopNewsViewModel(private val topNewsUseCase: TopNewsUseCase) : ViewModel() {

    val topNews: LiveData<NewsArticle> get() = _topNews
    private val _topNews = MutableLiveData<NewsArticle>()
    private val compositeDisposable = CompositeDisposable()

    fun getTopNews() {
        topNewsUseCase.getTopNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { newsResponse ->
                newsResponse.articles[0]
            }
            .subscribe(::handleData, ::handleError).addTo(compositeDisposable)
    }

    private fun handleError(throwable: Throwable) {
        // Handle error here
        logMessage(throwable)
    }

    private fun handleData(article: NewsArticle) {
        _topNews.value = article
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}