package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
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

    }

    private fun initClickView(){
        binding.ivBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}