package com.example.newsproject.util

object Constants {

    /**
     * Http connection
     * */
    const val API_KEY = "1270cdb52ea64830a16c882179172952"
    const val BASE_URL = "https://newsapi.org/v2/"
    const val TOP_HEADING: String = "top-headlines"
    const val EVERY_THING: String = "everything"

    /**
     * Room database
     * */
    const val CATEGORY_NEWS_TABLE = "category_news"
    const val BOOKMARK_NEWS_TABLE = "bookmark_news"
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "news_database"

    /**
     * Http error
     * */
    const val MESSAGE = "message"
    const val EXCEPTION = "exception"
    const val ERROR = "error"
    const val UNKNOWN_ERROR = "Unknown error structure, we can not find error message"

    /**
     * Bundle
     * */
    const val CATEGORY_TITLE = "category_title"
    const val CATEGORY_DETAIL_NEWS = "category_detail_news"
    const val SEARCHING_DETAIL_NEWS = "searching_detail_news"
    const val SEARCH_TEXT_NEWS = "search_text_news"

}