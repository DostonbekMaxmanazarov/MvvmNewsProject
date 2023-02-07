package com.example.newsproject.domain.mapper

fun interface ISingleMapper<T, R> {
    operator fun invoke(value: T): R
}