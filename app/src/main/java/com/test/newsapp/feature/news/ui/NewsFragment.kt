package com.test.newsapp.feature.news.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.test.common.util.binding.Binder
import com.test.common.util.binding.viewBinding
import com.test.common.util.delegates.lazyViewLifecycle
import com.test.newsapp.R
import com.test.newsapp.databinding.FragmentNewsBinding
import com.test.newsapp.feature.news.binder.NewsBinder
import com.test.newsapp.feature.news.model.NewsViewState
import com.test.newsapp.feature.news.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment(R.layout.fragment_news) {

    private val binding by viewBinding(FragmentNewsBinding::bind)
    private val viewModel by viewModel<NewsViewModel>()
    private val binder: Binder<NewsViewState> by lazyViewLifecycle {
        with(binding) {
            NewsBinder(
            )
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, binder::bind)
    }
}
