package com.example.rickandmortyapi.domain.characters.entity

import com.example.rickandmortyapi.domain.common.base.BaseId

data class CharactersEntity(
    val info: Info,
    val results: List<CharacterEntity>,
)

data class Info(
    val count: Int,
    val pages: Any,
    val next: Any,
    val prev: Any,
)

data class CharacterEntity(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginCharacter,
    val location: LocationCharacter,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
    override val baseId: Long,
) : BaseId

data class LocationCharacter(
    val name: String,
    val url: String,
)

data class OriginCharacter(
    val name: String,
    val url: String,
)
