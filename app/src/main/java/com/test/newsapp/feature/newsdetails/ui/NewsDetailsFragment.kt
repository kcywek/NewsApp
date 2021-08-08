package com.test.newsapp.feature.newsdetails.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.test.common.util.binding.Binder
import com.test.common.util.binding.viewBinding
import com.test.common.util.delegates.lazyViewLifecycle
import com.test.newsapp.R
import com.test.newsapp.databinding.FragmentDetailsNewsBinding
import com.test.newsapp.feature.newsdetails.binder.NewsDetailsBinder
import com.test.newsapp.feature.newsdetails.model.NewsDetailsViewState
import com.test.newsapp.feature.newsdetails.router.NewsDetailsRouterImpl
import com.test.newsapp.feature.newsdetails.viewmodel.NewsDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NewsDetailsFragment : Fragment(R.layout.fragment_details_news) {

    private val args: NewsDetailsFragmentArgs by navArgs()
    private val binding by viewBinding(FragmentDetailsNewsBinding::bind)
    private val router by lazy { NewsDetailsRouterImpl(requireContext()) }
    private val viewModel by viewModel<NewsDetailsViewModel> {
        parametersOf(args.news)
    }
    private val binder: Binder<NewsDetailsViewState> by lazyViewLifecycle {
        with(binding) {
            NewsDetailsBinder(
                titleView = title,
                descriptionView = description,
                imageView = image,
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.url.setOnClickListener { viewModel.onShowFullNews(router) }
    }

    private fun setupObservers() = with(viewModel) {
        viewState.observe(viewLifecycleOwner, binder::bind)
    }
}
