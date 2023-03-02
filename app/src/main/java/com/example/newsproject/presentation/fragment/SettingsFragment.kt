package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.newsproject.App
import com.example.newsproject.R
import com.example.newsproject.databinding.FragmentSettingBinding
import com.example.newsproject.presentation.enum.ThemeModeType
import com.example.newsproject.presentation.screen.MainActivity
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
            ThemeModeType.FOLLOW_SYSTEM_MODE.type -> {
                binding.rbDefault.isChecked = true
            }
            ThemeModeType.LIGHT_MODE.type -> {
                binding.rbLight.isChecked = true
            }
            ThemeModeType.NIGHT_MODE.type -> {
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
                    App.mApp.sharedPref.themeMode = ThemeModeType.FOLLOW_SYSTEM_MODE.type
                    (requireActivity() as MainActivity).updateThemeMode(ThemeModeType.FOLLOW_SYSTEM_MODE.type)
                }
                R.id.rbLight -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    App.mApp.sharedPref.themeMode = ThemeModeType.LIGHT_MODE.type
                    (requireActivity() as MainActivity).updateThemeMode(ThemeModeType.LIGHT_MODE.type)

                }
                R.id.rbNight -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    App.mApp.sharedPref.themeMode = ThemeModeType.NIGHT_MODE.type
                    (requireActivity() as MainActivity).updateThemeMode(ThemeModeType.NIGHT_MODE.type)

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}