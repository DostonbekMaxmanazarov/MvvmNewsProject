package com.example.newsproject.util.extension

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun String.snackBar(view: View) {
    Snackbar.make(view, this, Snackbar.LENGTH_LONG).setBackgroundTint(Color.parseColor("#FFFFFF"))
        .setTextColor(Color.parseColor("#40424C")).show()
}

fun String.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
}