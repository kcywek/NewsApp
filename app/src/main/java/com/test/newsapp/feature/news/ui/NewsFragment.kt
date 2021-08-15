package com.test.newsapp.feature.news.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.test.common.util.binding.Binder
import com.test.common.util.binding.viewBinding
import com.test.common.util.delegates.lazyViewLifecycle
import com.test.common.util.extensions.observe
import com.test.newsapp.R
import com.test.newsapp.databinding.FragmentNewsBinding
import com.test.newsapp.feature.news.adapter.NewsAdapterImpl
import com.test.newsapp.feature.news.binder.NewsBinder
import com.test.newsapp.feature.news.model.NewsEffect
import com.test.newsapp.feature.news.model.NewsViewState
import com.test.newsapp.feature.news.router.NewsRouterImpl
import com.test.newsapp.feature.news.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment(R.layout.fragment_news) {

    private val binding by viewBinding(FragmentNewsBinding::bind)
    private val viewModel by viewModel<NewsViewModel>()
    private val adapter = NewsAdapterImpl()
    private val router by lazy { NewsRouterImpl(findNavController()) }
    private val binder: Binder<NewsViewState> by lazyViewLifecycle {
        with(binding) {
            NewsBinder(
                adapter = adapter,
                viewPager = viewPager,
                noResults = noResults,
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setListeners()
        setViews()
        viewModel.onSearchTriggered("Apple")
    }

    private fun setViews() = with(binding) {
        viewPager.adapter = adapter
    }

    private fun setListeners() {
        adapter.onNewsClickListener = {
            viewModel.onNewsClicked(router, it)
        }
    }

    override fun onDestroyView() {
        binding.viewPager.adapter = null
        super.onDestroyView()
    }

    private fun setupObservers() = with(viewModel) {
        viewState.observe(viewLifecycleOwner, binder::bind)
        effect.observe(viewLifecycleOwner) {
            when(it) {
                is NewsEffect.HideProgressDialog -> binding.progressBar.hide()
                is NewsEffect.ShowError ->
                    Toast.makeText(requireContext(), getString(it.text), Toast.LENGTH_SHORT).show()
                is NewsEffect.ShowProgressDialog -> binding.progressBar.show()
            }
        }
    }
}
