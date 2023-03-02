package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsproject.R
import com.example.newsproject.databinding.FragmentSearchNewsBinding
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.domain.enum.MessageType
import com.example.newsproject.presentation.adapter.SearchNewsAdapter
import com.example.newsproject.presentation.dialog.LoaderDialog
import com.example.newsproject.presentation.vm.SearchNewsViewModel
import com.example.newsproject.util.Constants
import com.example.newsproject.util.extension.addFragment
import com.example.newsproject.util.extension.snackBar
import com.example.newsproject.util.extension.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    private val vm by viewModels<SearchNewsViewModel>()

    private var _binding: FragmentSearchNewsBinding? = null
    private val binding get() = _binding!!

    private var loadingDialog: LoaderDialog? = null

    private lateinit var searchNewsAdapter: SearchNewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSearchNewsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadDataByViewModel()
        initClickView()
    }

    private fun initView() {
        searchNewsAdapter = SearchNewsAdapter()
        binding.apply {
            rv.layoutManager = GridLayoutManager(requireContext(), 2)
            rv.adapter = searchNewsAdapter
        }
    }

    private fun initClickView() {
        binding.appBarLayout.tvCancel.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.fab.setOnClickListener {
            addFragment(
                R.id.container,
                VerticalSearchingNewsFragment(),
                bundleOf(Constants.SEARCH_TEXT_NEWS to binding.appBarLayout.etSearch.text.toString()),
                true
            )
        }

        binding.appBarLayout.etSearch.addTextChangedListener {
            vm.getTopStories(it.toString())
        }

        searchNewsAdapter.setOnClickListener {
            addFragment(
                R.id.container,
                SearchDetailNewsFragment(),
                bundleOf(Constants.SEARCHING_DETAIL_NEWS to it),
                addToBackStack = true
            )
        }

        searchNewsAdapter.setOnClickBookmarkListener {
            vm.addBookmarkNews(it)
        }
    }

    private fun loadDataByViewModel() {
        vm.newsStateFlow.onEach { data ->
            when (data) {
                is ResultEvent.Success -> {
                    if (data.data.isNotEmpty()) {
                        searchNewsAdapter.submitList(data.data.toMutableList())
                        binding.rv.visibility = View.VISIBLE
                        binding.fab.visibility = View.VISIBLE
                        binding.tvWriteToSearch.visibility = View.GONE
                    }
                }
                is ResultEvent.Loading -> {
                    if (data.isLoading) {
                        if (loadingDialog == null) {
                            loadingDialog = LoaderDialog()
                            loadingDialog?.setStyle(
                                DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Translucent
                            )
                            loadingDialog?.isCancelable = false
                            loadingDialog?.show(
                                requireActivity().supportFragmentManager, this.javaClass.name
                            )
                        }
                    } else {
                        loadingDialog?.dismiss()
                        loadingDialog = null
                    }
                }
                is ResultEvent.Failure -> {
                    if (data.message == MessageType.EMPTY_DATA.message) {
                        binding.fab.visibility = View.GONE
                        binding.rv.visibility = View.GONE
                        binding.tvWriteToSearch.visibility = View.VISIBLE
                        binding.tvWriteToSearch.text = "no information found"
                    } else {
                        data.message?.toast(
                            requireContext()
                        )
                    }
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        vm.bookmarkStateFlow.onEach { data ->
            when (data) {
                is ResultEvent.Success -> {
                    if (data.data) "Saved".snackBar(binding.constraintLayout)
                }
                is ResultEvent.Loading -> {
                }
                is ResultEvent.Failure -> {
                    data.message?.snackBar(binding.constraintLayout)
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}