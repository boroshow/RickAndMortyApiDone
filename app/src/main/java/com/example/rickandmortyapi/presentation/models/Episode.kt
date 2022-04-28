package com.example.rickandmortyapi.presentation.models

import com.example.rickandmortyapi.domain.common.base.BaseId
import com.example.rickandmortyapi.domain.episodes.entity.EpisodeEntity
import com.google.gson.annotations.SerializedName

data class Episode(
    override val id: Long,
    val episodeId: Int,
    val name: String,
    @SerializedName("air_date")
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String,
) : BaseId

fun EpisodeEntity.toUi() = Episode(id, episodeId, name, airDate, episode, characters, url, created)