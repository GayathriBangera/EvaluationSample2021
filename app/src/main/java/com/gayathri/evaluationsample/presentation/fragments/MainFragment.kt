package com.gayathri.evaluationsample.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gayathri.evaluationsample.R
import com.gayathri.evaluationsample.presentation.adapter.PopularNewsAdapter
import com.gayathri.evaluationsample.presentation.viewmodels.PopularNewsViewModel
import com.gayathri.evaluationsample.presentation.viewmodels.TopNewsViewModel
import kotlinx.android.synthetic.main.fragment_sources.*
import kotlinx.android.synthetic.main.fragment_sources.view.*
import kotlinx.android.synthetic.main.layout_header.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val popularNewsViewModel by viewModel<PopularNewsViewModel>()
    private val topNewsViewModel by viewModel<TopNewsViewModel>()
    private lateinit var popularNewAdapter: PopularNewsAdapter
    private var pageSize = INITIAL_PAGE_SIZE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_sources, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initRecyclerView(view)
        setHeaderTitle(view)
    }

    private fun createLayoutManager(
        context: Context?
    ) = LinearLayoutManager(context, RecyclerView.VERTICAL, false)


    private fun initUi() {
        observePopularNewListLiveData()
        observeTopNewsLiveData()
    }

    private fun observeTopNewsLiveData() {
        //Get top news from an API
        topNewsViewModel.getTopNews()
        topNewsViewModel.topNews.observe(viewLifecycleOwner, Observer { topNews ->
            //Bind the top news data to the view
            view?.layoutTopNews?.bindData(topNews)

        })
    }

    private fun observePopularNewListLiveData() {
        //Get popular news from an API
        popularNewsViewModel.getPopularList(INITIAL_PAGE_SIZE)
        popularNewsViewModel.popularNewList.observe(viewLifecycleOwner, Observer { popularList ->
            popularNewAdapter.itemList = popularList
            setProgressBarVisibility(false)
        })
    }

    private fun setProgressBarVisibility(isVisible: Boolean) {
        progressBar?.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private val recyclerPageScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (!recyclerView.canScrollVertically(1)) {
                setProgressBarVisibility(true)
                //Enable pagination when scrolled and reached end
                pageSize += 10
                popularNewsViewModel.setPageSize(pageSize)
            }
        }
    }

    private fun initRecyclerView(view: View) {
        //Create Adapter and set up recycler view with adapter
        popularNewAdapter = PopularNewsAdapter()
        view.baseRecyclerView?.apply {
            layoutManager = createLayoutManager(context)
            adapter = popularNewAdapter
            addOnScrollListener(recyclerPageScrollListener)
        }
    }

    private fun setHeaderTitle(view: View?) {
        //Set news header title
        view?.apply {
            val layoutHeader = view.findViewById<ConstraintLayout>(R.id.layoutHeader)
            layoutHeader.tvPopularNewsHeader.text =
                context?.resources?.getString(R.string.popular_news)
            layoutTopNews.setHeaderTitle()
        }
    }

    companion object {
        private const val INITIAL_PAGE_SIZE = 10
    }
}
