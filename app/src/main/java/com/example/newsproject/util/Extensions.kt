package com.example.newsproject.util

import android.graphics.Color
import android.view.View
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment

fun Fragment.fullScreen() {
    requireActivity().window?.decorView?.systemUiVisibility =
        (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    requireActivity().window.statusBarColor = Color.TRANSPARENT
    WindowCompat.getInsetsController(requireActivity().window, requireActivity().window.decorView)
        .apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }
}