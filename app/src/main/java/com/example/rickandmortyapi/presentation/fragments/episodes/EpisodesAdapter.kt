package com.example.rickandmortyapi.presentation.fragments.episodes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapi.databinding.EpisodesItemBinding
import com.example.rickandmortyapi.domain.common.base.BaseDiffCallBack
import com.example.rickandmortyapi.domain.episodes.entity.EpisodeEntity
import com.example.rickandmortyapi.presentation.models.Episode

class EpisodesAdapter : ListAdapter<Episode, EpisodesAdapter.EpisodesViewHolder>(
    BaseDiffCallBack()) {

    class EpisodesViewHolder(private val binding: EpisodesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(it: Episode) {
            binding.tvEpisodesName.text = it.name
            binding.tvEpisodesData.text = it.airDate
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder(
            EpisodesItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

}
