package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.newsproject.R
import com.example.newsproject.databinding.FragmentCategoryDetailNewsBinding
import com.example.newsproject.model.CategoryNewsModel
import com.example.newsproject.presentation.vm.CategoryDetailNewsViewModel
import com.example.newsproject.util.Constants
import com.example.newsproject.util.fullScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryDetailNewsFragment : Fragment(R.layout.fragment_category_detail_news) {

    private var _binding: FragmentCategoryDetailNewsBinding? = null
    private val binding get() = _binding!!

    private var categoryNewsModel: CategoryNewsModel? = null

    private val vm by viewModels<CategoryDetailNewsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentCategoryDetailNewsBinding.bind(view)
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
        binding.tvDescription.text = categoryNewsModel?.description ?: ""
    }

    private fun initClickView() {
        binding.ivBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.ivBookMark.setOnClickListener {
            categoryNewsModel?.let { categoryModel ->
                vm.addBookmarkNews(categoryModel)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}