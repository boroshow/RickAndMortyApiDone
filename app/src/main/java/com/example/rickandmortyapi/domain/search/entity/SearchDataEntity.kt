package com.example.rickandmortyapi.domain.search.entity

import com.example.rickandmortyapi.domain.characters.entity.CharacterEntity
import com.example.rickandmortyapi.domain.episodes.entity.EpisodeEntity
import com.example.rickandmortyapi.domain.locations.entity.LocationEntity

sealed class SearchDataEntity {

    data class CharactersEntity(
        val characterEntity: CharacterEntity,
    ) : SearchDataEntity()

    data class EpisodesEntity(
        val episodeEntity: EpisodeEntity,
    ) : SearchDataEntity()

    data class LocationsEntity(
        val locationEntity: LocationEntity,
    ) : SearchDataEntity()

}

fun CharacterEntity.toCharacterEntity() = SearchDataEntity.CharactersEntity(
    CharacterEntity(
        id,
        name,
        status,
        species,
        type,
        gender,
        origin,
        location,
        image,
        episode,
        url,
        created,
        baseId
    )
)

fun LocationEntity.toLocationEntity() = SearchDataEntity.LocationsEntity(
    LocationEntity(id, name, type, dimension, residents, url, created, baseId)
)

fun EpisodeEntity.toEpisodeEntity() = SearchDataEntity.EpisodesEntity(
    EpisodeEntity(id, name, airDate, episode, characters, url, created, baseId)
)

