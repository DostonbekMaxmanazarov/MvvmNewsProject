package com.example.newsproject.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.domain.usecase.ICategoryAddBookmarkUseCase
import com.example.newsproject.model.CategoryNewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailNewsViewModel @Inject constructor(
    private val categoryAddBookmarkUseCase: ICategoryAddBookmarkUseCase
) : ViewModel() {

    private val _bookmarkSharedFlow =
        MutableSharedFlow<ResultEvent<Boolean>>()

    val bookmarkSharedFlow: SharedFlow<ResultEvent<Boolean>>
        get() = _bookmarkSharedFlow.asSharedFlow()

    fun addBookmarkNews(categoryNewsItemModel: CategoryNewsModel) {
        viewModelScope.launch {
            _bookmarkSharedFlow.emit(categoryAddBookmarkUseCase(categoryNewsItemModel))
        }
    }
}