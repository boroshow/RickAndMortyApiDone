package com.example.rickandmortyapi.presentation.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.databinding.FragmentSearchBinding
import com.example.rickandmortyapi.domain.common.base.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override fun bind(): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
    }

    override fun setupObservers() {
    }

    override fun setupUI() {
    }

}