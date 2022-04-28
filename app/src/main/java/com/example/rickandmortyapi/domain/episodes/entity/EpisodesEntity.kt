package com.example.rickandmortyapi.domain.episodes.entity

import com.example.rickandmortyapi.domain.common.base.BaseId
import com.google.gson.annotations.SerializedName

data class EpisodesEntity(
    val info: Info,
    val results: List<EpisodeEntity>,
)

data class Info(
    val count: Int,
    val pages: Any,
    val next: Any,
    val prev: Any,
)

data class EpisodeEntity(
    val idd: Long,
    val episodeId: Int,
    val name: String,
    @SerializedName("air_date")
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String,
    override val id: Long,
) : BaseId
