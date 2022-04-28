package com.example.rickandmortyapi.domain.locations.entity

import com.example.rickandmortyapi.domain.common.base.BaseId

data class LocationsEntity(
    val info: Info,
    val results: List<LocationEntity>,
)

data class Info (
    val count: Int,
    val pages: Any,
    val next: Any,
    val prev: Any,
)

data class LocationEntity(
    val id: Int,
    val name: String,
    val type: String,
    val dimension : String,
    val residents : List<String>,
    val url : String,
    val created : String,
    override val baseId : Long,
) : BaseId
