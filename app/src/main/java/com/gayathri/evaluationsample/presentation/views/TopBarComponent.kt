package com.gayathri.evaluationsample.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.gayathri.domain.models.NewsArticle
import com.gayathri.evaluationsample.R
import com.gayathri.evaluationsample.utils.setImage
import kotlinx.android.synthetic.main.layout_header.view.*
import kotlinx.android.synthetic.main.layout_top_news.view.*

class TopBarComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_top_news, this)
    }

    fun bindData(newsArticle: NewsArticle) {
        newsArticle.urlToImage?.let { url ->
            ivTopNewsImage.setImage(url)
        }
        tvTopNewsTitle.text = newsArticle.title
        tvTopNewsDescription.text = newsArticle.description
        tvTopNewsChannelName.text = newsArticle.source.name
    }

    fun setHeaderTitle() {
        //To set the header title of the component (top news)
        tvPopularNewsHeader.text = context?.resources?.getString(R.string.top_news)
    }
}
