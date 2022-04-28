package com.example.rickandmortyapi.domain.common.base

interface BaseId {
    val id: Long
    override fun equals(other: Any?): Boolean
}