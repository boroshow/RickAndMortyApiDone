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


    private lateinit var onClick: OnClick

    fun setOnClick(onClick: OnClick) {
        this.onClick = onClick
    }

    inner class LocationsViewHolder(private val binding: LocationsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(location: LocationEntity) {
            binding.tvLocationsName.text = location.name
            binding.tvLocationsType.text = location.type
            itemView.setOnClickListener {
                onClick.onClicked(location)
            }
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

    interface OnClick {
        fun onClicked(position: LocationEntity)
    }

}