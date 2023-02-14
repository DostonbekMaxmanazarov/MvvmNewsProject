package com.example.newsproject.util

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

fun Fragment.addFragment(
    @IdRes containerId: Int,
    fragment: Fragment,
    args: Bundle = bundleOf(),
    addToBackStack: Boolean = false
) {
    requireActivity().supportFragmentManager.commit {
        fragment.arguments = args
        add(containerId, fragment)
        if (addToBackStack) addToBackStack(fragment.javaClass.simpleName)
    }
}