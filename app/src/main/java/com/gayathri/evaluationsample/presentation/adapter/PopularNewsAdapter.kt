package com.gayathri.evaluationsample.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.gayathri.domain.models.NewsArticle
import com.gayathri.evaluationsample.R
import com.gayathri.evaluationsample.presentation.viewmodels.PopularNewsViewModel
import com.gayathri.evaluationsample.utils.setImage
import kotlinx.android.synthetic.main.layout_popular_news.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class PopularNewsAdapter(lifecycleOwner: ViewModelStoreOwner) :
    RecyclerView.Adapter<PopularNewsAdapter.NewsViewHolder>() {

    var itemList: List<NewsArticle> by Delegates.observable(listOf()) { _, _, _ -> notifyDataSetChanged() }

    private val popularNewsViewHolder by lifecycleOwner.viewModel<PopularNewsViewModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularNewsAdapter.NewsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_popular_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: PopularNewsAdapter.NewsViewHolder, position: Int) {
        holder.bindData(itemList[position])
    }

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(newsArticle: NewsArticle) {
            newsArticle.urlToImage?.let { url ->
                itemView.ivPopularNewsImage.setImage(url)
            }
            itemView.tvPopularNewsName.text = newsArticle.title
            itemView.tvPopularNewsDescription.text = newsArticle.description
            itemView.tvPopularNewsChannelName.text = newsArticle.source.name
            itemView.ivBookmark.setOnClickListener {
                popularNewsViewHolder.setBookMark(newsArticle)
            }
        }
    }
}
