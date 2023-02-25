package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.newsproject.R
import com.example.newsproject.databinding.FragmentDetailNewsBinding
import com.example.newsproject.model.CategoryNewsItemModel
import com.example.newsproject.util.Constants
import com.example.newsproject.util.fullScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailNewsFragment : Fragment(R.layout.fragment_detail_news) {

    private var _binding: FragmentDetailNewsBinding? = null
    private val binding get() = _binding!!

    private var categoryNewsModel: CategoryNewsItemModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDetailNewsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        fullScreen()
        initView()
        initClickView()
    }

    private fun initView() {
        categoryNewsModel = requireArguments().getParcelable(Constants.CATEGORY_DETAIL_NEWS)

        if (categoryNewsModel?.imageUrl?.isNotEmpty() == true) Glide.with(requireContext())
            .load(categoryNewsModel?.imageUrl).placeholder(R.drawable.ic_place_holder)
            .transition(DrawableTransitionOptions.withCrossFade()).into(binding.ivNews)
        else binding.ivNews.setImageResource(R.drawable.nature_photo)

        binding.tvDate.text = categoryNewsModel?.publishedAt
        binding.tvName.text = categoryNewsModel?.name
        binding.tvTitle.text = categoryNewsModel?.title
        binding.tvDescription.text = categoryNewsModel?.description
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