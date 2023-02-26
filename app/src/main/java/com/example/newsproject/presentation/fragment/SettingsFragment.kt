package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.newsproject.App
import com.example.newsproject.R
import com.example.newsproject.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_setting) {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSettingBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        initView()
        initClickView()
    }

    private fun initView() {
        when (App.mApp.sharedPref.themeMode) {
            1 -> {
                binding.rbDefault.isChecked = true
            }
            2 -> {
                binding.rbLight.isChecked = true
            }
            3 -> {
                binding.rbNight.isChecked = true
            }
        }
    }

    private fun initClickView() {
        binding.ivBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.radioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.rbDefault -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    App.mApp.sharedPref.themeMode = 1
                }
                R.id.rbLight -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    App.mApp.sharedPref.themeMode = 2
                }
                R.id.rbNight -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    App.mApp.sharedPref.themeMode = 3
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}