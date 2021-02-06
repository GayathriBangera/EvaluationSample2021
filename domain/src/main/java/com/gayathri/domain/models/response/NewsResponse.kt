package com.gayathri.domain.models.response

import com.gayathri.domain.models.NewsArticle

class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsArticle>
)