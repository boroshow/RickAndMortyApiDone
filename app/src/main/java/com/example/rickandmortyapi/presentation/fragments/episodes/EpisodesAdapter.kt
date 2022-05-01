package com.example.rickandmortyapi.presentation.fragments.episodes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapi.databinding.EpisodesItemBinding
import com.example.rickandmortyapi.domain.common.base.BaseDiffCallBack
import com.example.rickandmortyapi.domain.episodes.entity.EpisodeEntity

class EpisodesAdapter : ListAdapter<EpisodeEntity, EpisodesAdapter.EpisodesViewHolder>(
    BaseDiffCallBack()) {

    private lateinit var onClick: OnClick

    fun setOnClick(onClick: OnClick) {
        this.onClick = onClick
    }

    inner class EpisodesViewHolder(private val binding: EpisodesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(episode: EpisodeEntity) {
            binding.tvEpisodesName.text = episode.name
            binding.tvEpisodesData.text = episode.airDate
            itemView.setOnClickListener {
                onClick.onClicked(episode)
            }
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

    interface OnClick {
        fun onClicked(position: EpisodeEntity)
    }

}
