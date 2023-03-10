package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.newsproject.R
import com.example.newsproject.databinding.FragmentVerticalSearchingNewsBinding
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.presentation.adapter.VerticalSearchingNewsAdapter
import com.example.newsproject.presentation.dialog.LoaderDialog
import com.example.newsproject.presentation.vm.VerticalSearchNewsViewModel
import com.example.newsproject.util.*
import com.example.newsproject.util.extension.fullScreen
import com.example.newsproject.util.extension.snackBar
import com.example.newsproject.util.extension.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class VerticalSearchingNewsFragment : Fragment(R.layout.fragment_vertical_searching_news) {

    private var _binding: FragmentVerticalSearchingNewsBinding? = null
    private val binding get() = _binding!!

    private val vm by viewModels<VerticalSearchNewsViewModel>()

    private var verticalNewsAdapter: VerticalSearchingNewsAdapter? = null

    private var loadingDialog: LoaderDialog? = null

    private var searchingText: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentVerticalSearchingNewsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadDataByViewModel()
        initClickView()
    }

    private fun initView() {
        searchingText = requireArguments().getString(Constants.SEARCH_TEXT_NEWS)

        val snapHelper = LinearSnapHelper()
        verticalNewsAdapter = VerticalSearchingNewsAdapter()
        binding.rv.adapter = verticalNewsAdapter
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        snapHelper.attachToRecyclerView(binding.rv)
        searchingText?.let {
            vm.getSearchNews(searchText = it)
        }
    }

    private fun loadDataByViewModel() {
        vm.newsStateFlow.onEach { data ->
            when (data) {
                is ResultEvent.Success -> {
                    verticalNewsAdapter?.submitList(data.data.toMutableList())
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
                    data.message?.toast(requireContext())
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        vm.bookmarkSharedFlow.onEach { data ->
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

    private fun initClickView() {
        binding.ivBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        verticalNewsAdapter?.setOnClickBookmarkListener {
            vm.addBookmarkNews(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}