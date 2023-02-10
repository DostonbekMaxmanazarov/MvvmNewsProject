package com.example.newsproject

fun main() {
    val aa = apiCall { 1+1 }
    println(aa)
}

fun apiCall(call: () -> Int): Int {
    return call.invoke()
}