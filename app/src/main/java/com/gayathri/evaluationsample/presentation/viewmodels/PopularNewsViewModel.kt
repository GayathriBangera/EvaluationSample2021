package com.gayathri.evaluationsample.presentation.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gayathri.domain.models.NewsArticle
import com.gayathri.domain.models.response.NewsResponse
import com.gayathri.domain.models.room.Bookmark
import com.gayathri.domain.models.room.NewsArticleRoom
import com.gayathri.domain.usecases.BookmarkUseCase
import com.gayathri.domain.usecases.PopularNewsRoomUseCase
import com.gayathri.domain.usecases.PopularNewsUseCase
import com.gayathri.evaluationsample.utils.AppUtils.logMessage
import com.gayathri.evaluationsample.utils.STATUS_OK
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class PopularNewsViewModel(
    private val context: Context,
    private val popularNewsUseCase: PopularNewsUseCase,
    private val bookmarkUseCase: BookmarkUseCase,
    private val popularNewsRoomUseCase: PopularNewsRoomUseCase
) : ViewModel() {

    val popularNewList: LiveData<List<NewsArticle>> get() = _popularNewList
    private val _popularNewList = MutableLiveData<List<NewsArticle>>()
    private val compositeDisposable = CompositeDisposable()
    val liveDataProgressBarState: LiveData<Boolean> get() = _liveDataProgressBarState
    private val _liveDataProgressBarState = MutableLiveData<Boolean>()

    init {
        run {
            val bookmark = bookmarkUseCase.getBookMark("")
            Log.d("evaluation_sample_log", "bookmark $bookmark")

            val bookmarks = bookmarkUseCase.getBookMarks()
            bookmarks.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ bookmarkList ->
                    Log.d("evaluation_sample_log", "bookmarkList ${bookmarkList.size}")
                    bookmarkList.forEach {
                        Log.d("evaluation_sample_log", "bookmarks ${it.title}")
                    }
                }, {
                    Log.d("evaluation_sample_log", "bookmarks $it")
                }).addTo(compositeDisposable)

            /*bookmarks.subscribe {
                Log.d("evaluation_sample_log", "bookmarks subscribe ${it}")
            }*/

            popularNewsRoomUseCase.getPopularNewsList().subscribe { data ->
                data.forEach {
                    Log.d("evaluation_sample_log", "getPopularNewsList subscribe title ${it.title} author ${it.author}")
                }
            }
        }
    }

    private fun handleBookmarkData(bookmarks: Bookmark) {
        Log.d("evaluation_sample_log", "bookmarks $bookmarks")
    }

    fun getPopularList(pageSize: Int) {
        //Get list of popular news items
        setProgressBarState(true)
        popularNewsUseCase.getPopularNewsList(pageSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleData, ::handleError).addTo(compositeDisposable)
    }

    private fun handleError(throwable: Throwable?) {
        // handle error here, let the user know if there is an error
        logMessage("throwable $throwable")
        setProgressBarState(false)
        Toast.makeText(context, "No more data to load", Toast.LENGTH_SHORT).show()
    }

    private fun handleData(newsResponse: NewsResponse) {
        logMessage("newsResponse $newsResponse")
        setProgressBarState(false)
        when (newsResponse.status) {
            STATUS_OK -> {
                _popularNewList.value = newsResponse.articles
                storePopularNewsInRoomDb(newsResponse.articles)
            }
        }
    }

    private fun storePopularNewsInRoomDb(articles: List<NewsArticle>) {
        val list = mutableListOf<NewsArticleRoom>()
        articles.forEach { article ->
            val news = NewsArticleRoom(
                title = article.title,
                author = article.author,
                description = article.description,
                url = article.url,
                urlToImage = article.urlToImage ?: "",
                publishedAt = article.publishedAt,
                content = article.content
            )
            list.add(news)
        }
        popularNewsRoomUseCase.insertPopularNews(list)
    }

    private fun setProgressBarState(state: Boolean) {
        _liveDataProgressBarState.value = state
    }

    fun setPageSize(pageSize: Int) {
        //When the user scrolls down through the recyclerview and reaches bottom end then this function will be called
        getPopularList(pageSize)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun setBookMark(newsArticle: NewsArticle) {
        Toast.makeText(context, "Bookmark ${newsArticle.title}", Toast.LENGTH_SHORT).show()
        val bookmark = Bookmark(title = newsArticle.title)
        bookmarkUseCase.insertBookMark(bookmark)
        /*.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            Log.d("evaluation_sample_log", "complete $bookmark")
        }, {
            Log.d("evaluation_sample_log", "error $it")
        }).addTo(compositeDisposable)*/
    }
}
