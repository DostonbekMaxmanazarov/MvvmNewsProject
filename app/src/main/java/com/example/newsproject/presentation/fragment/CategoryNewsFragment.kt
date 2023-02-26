package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.newsproject.R
import com.example.newsproject.databinding.FragmentCategoryNewsBinding
import com.example.newsproject.databinding.FragmentMainBinding
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.model.CategoriesTabModel
import com.example.newsproject.model.CategoryNewsModel
import com.example.newsproject.presentation.adapter.CategoryNewsAdapter
import com.example.newsproject.presentation.adapter.CategoryTabAdapter
import com.example.newsproject.presentation.dialog.LoaderDialog
import com.example.newsproject.presentation.vm.CategoriesNewsViewModel
import com.example.newsproject.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CategoryNewsFragment : Fragment(R.layout.fragment_main) {

    private val vm by viewModels<CategoriesNewsViewModel>()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var bindingCategory: FragmentCategoryNewsBinding

    private var loadingDialog: LoaderDialog? = null

    private lateinit var categoryNewsAdapter: CategoryNewsAdapter
    private lateinit var categoryTitleAdapter: CategoryTabAdapter

    private var categories = mutableListOf<CategoryNewsModel>()
    private var categoryTitle: String = ""

    private var categoryNewsModel: CategoryNewsModel? = null

    private val categoriesTitle = listOf(
        CategoriesTabModel("Business"),
        CategoriesTabModel("Entertainment"),
        CategoriesTabModel("General"),
        CategoriesTabModel("Health"),
        CategoriesTabModel("Science"),
        CategoriesTabModel("Sports"),
        CategoriesTabModel("Technology")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentMainBinding.bind(view)
        bindingCategory = _binding!!.fragmentCategoryNews
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadDataByViewModel()
        initClickView()
    }

    private fun initView() {
        vm.getTopStories(category = categoriesTitle[0].title)
        categoryTitle = categoriesTitle[0].title

        categoryTitleAdapter = CategoryTabAdapter()
        categoryNewsAdapter = CategoryNewsAdapter()

        bindingCategory.apply {
            rv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rv.adapter = categoryNewsAdapter

            rvTitle.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvTitle.adapter = categoryTitleAdapter
        }

        categoryTitleAdapter.submitList(categoriesTitle.toMutableList())
        categoryTitleAdapter.updateData(0)

    }

    private fun initClickView() {
        categoryTitleAdapter.setOnClickListener { categoryItem ->
            vm.getTopStories(category = categoryItem.title)
            categoryTitle = categoryItem.title
        }

        bindingCategory.ivNavigation.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        bindingCategory.fab.setOnClickListener {
            addFragment(
                containerId = R.id.container,
                fragment = VerticalCategoryNewsFragment(),
                addToBackStack = true,
                args = bundleOf(Constants.CATEGORY_TITLE to categoryTitle)
            )
        }

        bindingCategory.etSearch.setOnClickListener {
            replaceFragment(R.id.container, SearchNewsFragment(), addToBackStack = true)
        }

        categoryNewsAdapter.setOnClickListener {
            addFragment(
                R.id.container,
                CategoryDetailNewsFragment(),
                bundleOf(Constants.CATEGORY_DETAIL_NEWS to it),
                true
            )
        }

        categoryNewsAdapter.setOnClickBookmarkListener {
            vm.addBookmarkNews(it)
            "Saved".snackBar(bindingCategory.constraint)
        }

        bindingCategory.ivNewsPhoto.setOnClickListener {
            addFragment(
                R.id.container,
                CategoryDetailNewsFragment(),
                bundleOf(Constants.CATEGORY_DETAIL_NEWS to categoryNewsModel),
                true
            )
        }

        bindingCategory.ivBookMark.setOnClickListener {
            categoryNewsModel?.let { categoryModel -> vm.addBookmarkNews(categoryModel) }
            "Saved".snackBar(bindingCategory.constraint)
        }

        binding.layoutBookmark.setOnClickListener {
            addFragment(R.id.container, BookmarkNewsFragment(), addToBackStack = true)
        }

        binding.layoutHistory.setOnClickListener {
            addFragment(R.id.container, HistoryFragment(), addToBackStack = true)

        }

        binding.layoutSettings.setOnClickListener {
            addFragment(R.id.container, SettingsFragment(), addToBackStack = true)

        }

        binding.layoutExit.setOnClickListener {
            requireActivity().finish()
        }

    }

    private fun loadDataByViewModel() {
        vm.newsStateFlow.onEach { data ->
            when (data) {
                is ResultEvent.Success -> {
                    categories = data.data.toMutableList()
                    setTopCategoryNews(categories[0])
                    categoryNewsModel = categories[0]
                    categories.removeAt(0)
                    categoryNewsAdapter.submitList(categories)
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
                is ResultEvent.Failure -> {data.message?.toast(requireContext())}
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setTopCategoryNews(item: CategoryNewsModel) {
        if (item.imageUrl?.isNotEmpty() == true) Glide.with(requireContext()).load(item.imageUrl)
            .placeholder(R.drawable.ic_place_holder)
            .transition(DrawableTransitionOptions.withCrossFade()).into(bindingCategory.ivNewsPhoto)
        else bindingCategory.ivNewsPhoto.setImageResource(R.drawable.nature_photo)

        bindingCategory.tvDate.text = item.publishedAt
        bindingCategory.tvName.text = item.name
        bindingCategory.tvTitle.text = item.title
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}