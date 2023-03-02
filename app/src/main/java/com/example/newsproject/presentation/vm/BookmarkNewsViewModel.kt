package com.example.newsproject.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.domain.usecase.IDeleteBookmarkNewsUseCase
import com.example.newsproject.domain.usecase.IGetAllBookmarkNewsUseCase
import com.example.newsproject.model.BookmarkNewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkNewsViewModel @Inject constructor(
    private val getAllBookmarkNewsUseCase: IGetAllBookmarkNewsUseCase,
    private val deleteBookmarkUseCase: IDeleteBookmarkNewsUseCase
) : ViewModel() {

    val bookmarkNewsStateFlow: SharedFlow<ResultEvent<List<BookmarkNewsModel>>>
        get() = getAllBookmarkNewsUseCase.bookmarksSharedFlow

    init {
        getBookmarkNews()
    }

    private fun getBookmarkNews() {
        getAllBookmarkNewsUseCase().launchIn(viewModelScope)
    }

    fun deleteBookmark(id: Int) {
        viewModelScope.launch {
            deleteBookmarkUseCase(id)
        }
    }
}