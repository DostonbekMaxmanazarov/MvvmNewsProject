package com.example.newsproject.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.domain.usecase.ICategoryAddBookmarkUseCase
import com.example.newsproject.model.CategoryNewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailNewsViewModel @Inject constructor(
    private val categoryAddBookmarkUseCase: ICategoryAddBookmarkUseCase
) : ViewModel() {

    private val _bookmarkStateFlow =
        MutableStateFlow<ResultEvent<Boolean>>(ResultEvent.Success(false))

    val bookmarkStateFlow: StateFlow<ResultEvent<Boolean>>
        get() = _bookmarkStateFlow.asStateFlow()

    fun addBookmarkNews(categoryNewsItemModel: CategoryNewsModel) {
        viewModelScope.launch {
            _bookmarkStateFlow.emit(categoryAddBookmarkUseCase(categoryNewsItemModel))
        }
    }
}