package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.newsproject.R
import com.example.newsproject.databinding.FragmentSearchDetailNewsBinding
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.model.SearchNewsModel
import com.example.newsproject.presentation.vm.SearchDetailNewsViewModel
import com.example.newsproject.util.Constants
import com.example.newsproject.util.extension.fullScreen
import com.example.newsproject.util.extension.snackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchDetailNewsFragment : Fragment(R.layout.fragment_search_detail_news) {

    private var _binding: FragmentSearchDetailNewsBinding? = null
    private val binding get() = _binding!!

    private var searchNewsModel: SearchNewsModel? = null

    private val vm by viewModels<SearchDetailNewsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSearchDetailNewsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        initView()
        initClickView()
        loadDataByViewModel()
    }

    private fun initView() {
        searchNewsModel = requireArguments().getParcelable(Constants.SEARCHING_DETAIL_NEWS)

        if (searchNewsModel?.imageUrl?.isNotEmpty() == true) Glide.with(requireContext())
            .load(searchNewsModel?.imageUrl).placeholder(R.drawable.ic_place_holder)
            .transition(DrawableTransitionOptions.withCrossFade()).into(binding.ivNews)
        else binding.ivNews.setImageResource(R.drawable.nature_photo)

        binding.tvDate.text = searchNewsModel?.publishedAt
        binding.tvName.text = searchNewsModel?.name
        binding.tvTitle.text = searchNewsModel?.title
        binding.tvDescription.text = searchNewsModel?.description ?: ""
    }

    private fun initClickView() {
        binding.ivBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.ivBookMark.setOnClickListener {
            searchNewsModel?.let { searchNews -> vm.addBookmarkNews(searchNews) }
        }
    }

    private fun loadDataByViewModel() {
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


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}