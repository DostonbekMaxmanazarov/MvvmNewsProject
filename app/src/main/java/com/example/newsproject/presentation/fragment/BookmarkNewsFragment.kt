package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsproject.R
import com.example.newsproject.databinding.FragmentBookmarkNewsBinding
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.presentation.adapter.BookmarkNewsAdapter
import com.example.newsproject.presentation.dialog.LoaderDialog
import com.example.newsproject.presentation.vm.BookmarkNewsViewModel
import com.example.newsproject.util.extension.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class BookmarkNewsFragment : Fragment(R.layout.fragment_bookmark_news) {

    private val vm by viewModels<BookmarkNewsViewModel>()

    private var _binding: FragmentBookmarkNewsBinding? = null
    private val binding get() = _binding!!

    private var loadingDialog: LoaderDialog? = null

    private lateinit var bookmarkNewsAdapter: BookmarkNewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentBookmarkNewsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        initView()
        initClickView()
        loadDataByViewModel()
    }

    private fun initView() {
        bookmarkNewsAdapter = BookmarkNewsAdapter()
        binding.apply {
            rv.layoutManager = LinearLayoutManager(requireContext())
            rv.adapter = bookmarkNewsAdapter
        }
    }

    private fun initClickView() {
        binding.ivBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        bookmarkNewsAdapter.setOnClickListener {
            vm.deleteBookmark(it.id)
        }
    }

    private fun loadDataByViewModel() {
        vm.bookmarkNewsStateFlow.onEach { data ->
            when (data) {
                is ResultEvent.Success -> {
                    bookmarkNewsAdapter.submitList(data.data.toMutableList())
                }
                is ResultEvent.Loading -> {
                    if (loadingDialog == null) {
                        loadingDialog = LoaderDialog()
                        loadingDialog?.setStyle(
                            DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Translucent
                        )
                        loadingDialog?.isCancelable = false
                        loadingDialog?.show(
                            requireActivity().supportFragmentManager, this.javaClass.name
                        )
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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}