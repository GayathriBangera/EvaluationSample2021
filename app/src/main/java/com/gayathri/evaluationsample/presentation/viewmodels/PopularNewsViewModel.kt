package com.gayathri.evaluationsample.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gayathri.domain.models.NewsArticle
import com.gayathri.domain.models.response.NewsResponse
import com.gayathri.domain.usecases.PopularNewsUseCase
import com.gayathri.evaluationsample.utils.AppUtils.logMessage
import com.gayathri.evaluationsample.utils.STATUS_OK
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class PopularNewsViewModel(private val popularNewsUseCase: PopularNewsUseCase) : ViewModel() {

    val popularNewList: LiveData<List<NewsArticle>> get() = _popularNewList
    private val _popularNewList = MutableLiveData<List<NewsArticle>>()
    private val compositeDisposable = CompositeDisposable()

    fun getPopularList(pageSize: Int) {
        //Get list of popular news items
        popularNewsUseCase.getPopularNewsList(pageSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleData, ::handleError).addTo(compositeDisposable)
    }

    private fun handleError(throwable: Throwable?) {
        // handle error here, let the user know if there is an error
        logMessage("$throwable")
    }

    private fun handleData(newsResponse: NewsResponse) {
        when (newsResponse.status) {
            STATUS_OK -> _popularNewList.value = newsResponse.articles
        }
    }

    fun setPageSize(pageSize: Int) {
        //When the user scrolls down through the recyclerview and reaches bottom end then this function will be called
        getPopularList(pageSize)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
