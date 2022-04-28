package com.example.rickandmortyapi.presentation.fragments.locations.detail

import com.example.rickandmortyapi.databinding.FragmentLocationsDetailBinding
import com.example.rickandmortyapi.domain.common.base.BaseFragment

class LocationsDetailFragment : BaseFragment<FragmentLocationsDetailBinding>() {

    override fun bind(): FragmentLocationsDetailBinding {
        return FragmentLocationsDetailBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
    }

    override fun setupObservers() {
    }

    override fun setupUI() {
    }

}