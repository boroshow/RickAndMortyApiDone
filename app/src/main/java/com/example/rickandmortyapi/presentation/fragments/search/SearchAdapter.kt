package com.example.rickandmortyapi.presentation.fragments.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.databinding.CharactersItemBinding
import com.example.rickandmortyapi.databinding.EpisodesItemBinding
import com.example.rickandmortyapi.databinding.LocationsItemBinding
import com.example.rickandmortyapi.domain.search.entity.SearchDataEntity
import com.example.rickandmortyapi.presentation.fragments.characters.CharactersAdapter
import kotlin.reflect.KFunction0

class SearchAdapter(
    private var data: MutableList<SearchDataEntity>,
    private var characterItemClick: (id: Int) -> Unit,
    private var locationItemClick: (id: Int) -> Unit,
    private var episodeItemClick: (id: Int) -> Unit,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            R.layout.characters_item -> {
                return SearchViewHolderCharacters(
                    characterItemClick,
                    CharactersItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            R.layout.locations_item -> {
                return SearchViewHolderLocations(
                    locationItemClick,
                    LocationsItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            R.layout.episodes_item -> {
                return SearchViewHolderEpisodes(
                    episodeItemClick,
                    EpisodesItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalAccessError("Invalid ViewType Provided")
        }
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is SearchDataEntity.CharactersEntity -> R.layout.characters_item
            is SearchDataEntity.LocationsEntity -> R.layout.locations_item
            is SearchDataEntity.EpisodesEntity -> R.layout.episodes_item
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SearchViewHolderCharacters -> holder.bind(data[position] as SearchDataEntity.CharactersEntity)
            is SearchViewHolderLocations -> holder.bind(data[position] as SearchDataEntity.LocationsEntity)
            is SearchViewHolderEpisodes -> holder.bind(data[position] as SearchDataEntity.EpisodesEntity)
        }
    }


    class SearchViewHolderCharacters(
        private val onItemClick: (id: Int) -> Unit,
        private val binding: CharactersItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(characters: SearchDataEntity.CharactersEntity) {
            binding.tvCharacterName.text = characters.characterEntity.name
            binding.tvCharacterStatus.text = characters.characterEntity.status
            binding.imageCharacter.load(characters.characterEntity.image)
            itemView.setOnClickListener {
                onItemClick(characters.characterEntity.id)
            }
        }
    }

    class SearchViewHolderLocations(
        private val onItemClick: (id: Int) -> Unit,
        private val binding: LocationsItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(locations: SearchDataEntity.LocationsEntity) {
            binding.tvLocationsName.text = locations.locationEntity.name
            binding.tvLocationsType.text = locations.locationEntity.type
            itemView.setOnClickListener {
                onItemClick(locations.locationEntity.id)
            }
        }
    }

    class SearchViewHolderEpisodes(
        private val onItemClick: (id: Int) -> Unit,
        private val binding: EpisodesItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(episodes: SearchDataEntity.EpisodesEntity) {
            binding.tvEpisodesName.text = episodes.episodeEntity.name
            binding.tvEpisodesData.text = episodes.episodeEntity.airDate
            itemView.setOnClickListener {
                onItemClick(episodes.episodeEntity.id)
            }
        }
    }
}