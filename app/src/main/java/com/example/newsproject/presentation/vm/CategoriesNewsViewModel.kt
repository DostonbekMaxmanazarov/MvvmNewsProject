package com.example.newsproject.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.domain.usecase.ICategoryNewsUseCase
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.domain.usecase.ICategoryAddBookmarkUseCase
import com.example.newsproject.model.CategoryNewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesNewsViewModel @Inject constructor(
    private val categoryNewsUseCase: ICategoryNewsUseCase,
    private val categoryAddBookmarkUseCase: ICategoryAddBookmarkUseCase
) : ViewModel() {

    private val _newsStateFlow =
        MutableStateFlow<ResultEvent<List<CategoryNewsModel>>>(ResultEvent.Loading(true))

    val newsStateFlow: StateFlow<ResultEvent<List<CategoryNewsModel>>>
        get() = _newsStateFlow.asStateFlow()

    private val _bookmarkStateFlow =
        MutableStateFlow<ResultEvent<Boolean>>(ResultEvent.Success(false))

    val bookmarkStateFlow: StateFlow<ResultEvent<Boolean>>
        get() = _bookmarkStateFlow.asStateFlow()

    fun getTopStories(isLoadingLocal: Boolean = false, category: String = "") {
        viewModelScope.launch {
            _newsStateFlow.emit(ResultEvent.Loading(true))
            delay(300)
            _newsStateFlow.emit(categoryNewsUseCase(isLoadingLocal, category))
            delay(300)
            _newsStateFlow.emit(ResultEvent.Loading(false))
        }
    }

    fun addBookmarkNews(categoryNewsItemModel: CategoryNewsModel) {
        viewModelScope.launch {
            _bookmarkStateFlow.emit(categoryAddBookmarkUseCase(categoryNewsItemModel))
        }
    }
}