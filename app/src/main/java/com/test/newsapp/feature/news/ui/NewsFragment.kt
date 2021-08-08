package com.test.newsapp.feature.news.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.test.common.util.binding.Binder
import com.test.common.util.binding.viewBinding
import com.test.common.util.delegates.lazyViewLifecycle
import com.test.common.util.extensions.observe
import com.test.common.util.extensions.setOnEditorSearchActionListener
import com.test.common.util.keyboard.SoftKeyboardController
import com.test.common.util.keyboard.SystemSoftKeyboardController
import com.test.newsapp.R
import com.test.newsapp.databinding.FragmentNewsBinding
import com.test.newsapp.feature.news.adapter.NewsAdapterImpl
import com.test.newsapp.feature.news.binder.NewsBinder
import com.test.newsapp.feature.news.model.NewsEffect
import com.test.newsapp.feature.news.model.NewsViewState
import com.test.newsapp.feature.news.viewmodel.NewsViewModel
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment(R.layout.fragment_news) {

    private val binding by viewBinding(FragmentNewsBinding::bind)
    private val viewModel by viewModel<NewsViewModel>()
    private val adapter = NewsAdapterImpl()
    private val softKeyboardController: SoftKeyboardController by lazyViewLifecycle {
        SystemSoftKeyboardController(
            view = requireView(),
            imm = get()
        )
    }
    private val binder: Binder<NewsViewState> by lazyViewLifecycle {
        with(binding) {
            NewsBinder(
                adapter = adapter,
                recyclerView = recyclerView,
                noResults = noResults,
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setViews()
        setListeners()
    }

    private fun setViews() = with(binding) {
        recyclerView.adapter = adapter
        softKeyboardController.showKeyboard()
    }

    private fun setListeners() {
        binding.searchInputView.setOnEditorSearchActionListener {
            viewModel.onSearchTriggered(it.text.toString())
        }
    }

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        super.onDestroyView()
    }

    private fun setupObservers() = with(viewModel) {
        viewState.observe(viewLifecycleOwner, binder::bind)
        effect.observe(viewLifecycleOwner) {
            when(it) {
                is NewsEffect.HideProgressDialog -> {} //TODO: handle news loading
                is NewsEffect.ShowError ->
                    Toast.makeText(requireContext(), getString(it.text), Toast.LENGTH_SHORT).show()
                is NewsEffect.ShowProgressDialog -> {} //TODO: handle news loading
            }
        }
    }
}
