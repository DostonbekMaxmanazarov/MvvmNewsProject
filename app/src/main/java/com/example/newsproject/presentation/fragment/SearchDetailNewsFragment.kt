package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.newsproject.R
import com.example.newsproject.databinding.FragmentDetailNewsBinding
import com.example.newsproject.databinding.FragmentSearchDetailNewsBinding
import com.example.newsproject.model.CategoryNewsItemModel
import com.example.newsproject.model.SearchNewsItemModel
import com.example.newsproject.util.Constants
import com.example.newsproject.util.fullScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchDetailNewsFragment : Fragment(R.layout.fragment_search_detail_news) {

    private var _binding: FragmentSearchDetailNewsBinding? = null
    private val binding get() = _binding!!

    private var searchNewsModel: SearchNewsItemModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSearchDetailNewsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        fullScreen()
        initView()
        initClickView()
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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}