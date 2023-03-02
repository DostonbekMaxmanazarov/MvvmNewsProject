package com.example.newsproject.domain.usecase

interface IDeleteBookmarkNewsUseCase {
    suspend operator fun invoke(id: Int)
}