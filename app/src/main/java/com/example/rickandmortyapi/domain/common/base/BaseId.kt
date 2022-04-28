package com.example.rickandmortyapi.domain.common.base

interface BaseId {
    val baseId: Long
    override fun equals(other: Any?): Boolean
}