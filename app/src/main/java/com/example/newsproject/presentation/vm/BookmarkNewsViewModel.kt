package com.example.newsproject.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.domain.usecase.IGetAllBookmarkNewsUseCase
import com.example.newsproject.model.BookmarkNewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkNewsViewModel @Inject constructor(
    private val getAllBookmarkNewsUseCase: IGetAllBookmarkNewsUseCase
) : ViewModel() {

    private val _bookmarkNewsStateFlow =
        MutableStateFlow<ResultEvent<List<BookmarkNewsModel>>>(ResultEvent.Loading(true))

    val bookmarkNewsStateFlow: StateFlow<ResultEvent<List<BookmarkNewsModel>>>
        get() = _bookmarkNewsStateFlow.asStateFlow()

    init {
        getBookmarkNews()
    }

    private fun getBookmarkNews() {
        viewModelScope.launch {
            _bookmarkNewsStateFlow.emit(ResultEvent.Loading(true))
            delay(300)
            _bookmarkNewsStateFlow.emit(getAllBookmarkNewsUseCase())
            delay(300)
            _bookmarkNewsStateFlow.emit(ResultEvent.Loading(false))
        }
    }
}