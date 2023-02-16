package com.example.newsproject.util

object Constants {

    /**
     * Http connection
     * */
    const val API_KEY = "1270cdb52ea64830a16c882179172952"
    const val BASE_URL = "https://newsapi.org/v2/"
    const val TOP_HEADING: String = "top-headlines"

    /**
     * Room database
     * */
    const val BREAKING_NEWS_TABLE = "breaking_news"
    const val TOP_NEWS_TABLE = "top_news"
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "news_database"

    /**
     * Http error
     * */
    const val MESSAGE = "message"
    const val EXCEPTION = "exception"
    const val ERROR = "error"
    const val UNKNOWN_ERROR =
        "Unknown error structure, we can not find error message"

    /**
     * Bundle
     * */
    const val SENDING_NEWS_MODEL="sending_news_model"
    //
}