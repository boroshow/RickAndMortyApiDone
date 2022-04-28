package com.example.rickandmortyapi.presentation.fragments.locations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapi.databinding.LocationsItemBinding
import com.example.rickandmortyapi.domain.common.base.BaseDiffCallBack
import com.example.rickandmortyapi.domain.locations.entity.LocationEntity

class LocationsAdapter : ListAdapter<LocationEntity, LocationsAdapter.LocationsViewHolder>(
    BaseDiffCallBack()) {

    class LocationsViewHolder(private val binding: LocationsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(it: LocationEntity) {
            binding.tvLocationsName.text = it.name
            binding.tvLocationsType.text = it.type
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        return LocationsViewHolder(
            LocationsItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

}