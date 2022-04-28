package com.example.rickandmortyapi.presentation.fragments.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortyapi.databinding.CharactersItemBinding
import com.example.rickandmortyapi.domain.characters.entity.CharacterEntity
import com.example.rickandmortyapi.domain.common.base.BaseDiffCallBack

class CharactersAdapter : ListAdapter<CharacterEntity, CharactersAdapter.CharactersViewHolder>(BaseDiffCallBack()) {

    private lateinit var onClick: OnClick

    fun setOnClick(onClick: OnClick) {
        this.onClick = onClick
    }

    inner class CharactersViewHolder(private val binding: CharactersItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(character: CharacterEntity) {
            binding.imageCharacter.load(character.image)
            binding.tvCharacterName.text = character.name
            binding.tvCharacterStatus.text = character.status
            itemView.setOnClickListener{
                onClick.onClicked(character)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            CharactersItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    interface OnClick {
        fun onClicked(position: CharacterEntity)
    }

}