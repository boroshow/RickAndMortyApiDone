package com.example.rickandmortyapi.domain.common.base

import androidx.recyclerview.widget.DiffUtil

class BaseDiffCallBack<B : BaseId> : DiffUtil.ItemCallback<B>() {

    override fun areItemsTheSame(oldItem: B, newItem: B): Boolean {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: B, newItem: B): Boolean {
        return oldItem == newItem
    }
}