package com.example.rickandmortyapi.presentation.fragments.search

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapi.databinding.FragmentSearchBinding
import com.example.rickandmortyapi.domain.common.base.BaseFragment
import com.example.rickandmortyapi.domain.search.entity.SearchDataEntity
import com.example.rickandmortyapi.domain.search.entity.toCharacterEntity
import com.example.rickandmortyapi.domain.search.entity.toEpisodeEntity
import com.example.rickandmortyapi.domain.search.entity.toLocationEntity
import com.example.rickandmortyapi.presentation.state.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModels()
    private val list = mutableListOf<SearchDataEntity>()
    private val adapter: SearchAdapter by lazy {
        SearchAdapter(
            list,
            this::onItemCharacter,
            this::onItemLocation,
            this::onItemEpisode
        )
    }

    private fun onItemEpisode(id: Int) {
        findNavController()
            .navigate(SearchFragmentDirections.actionSearchFragmentToEpisodeDetailFragment(id))
    }

    private fun onItemLocation(id: Int) {
        findNavController()
            .navigate(SearchFragmentDirections.actionSearchFragmentToLocationsDetailFragment(id))
    }

    private fun onItemCharacter(id: Int) {
        findNavController()
            .navigate(SearchFragmentDirections.actionSearchFragmentToCharactersDetailFragment(id))

    }

    override fun bind(): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
        etLogic()
    }

    private fun searchItem(it: String) {
        viewModel.fetchLocations(it)
        viewModel.fetchEpisodes(it)
        viewModel.fetchCharacter(it)
    }

    override fun setupObservers() {
        observeCharacters()
        observeEpisodes()
        observeLocations()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeLocations() {
        lifecycleScope.launchWhenCreated {
            viewModel.fetchLocationSearch.collectLatest {
                when (it) {
                    is UiState.Loading -> {
                    }
                    is UiState.Error -> {
                        Log.e("TAG", "observeEpisode: $it")
                    }
                    is UiState.Success -> it.data.map { location ->
                        location.toLocationEntity()
                    }.let { sorted ->
                        val sort = sorted.sortedByDescending { it1 ->
                            it1.locationEntity.created
                        }
                        list.addAll(sort)
                    }

                    else -> {}
                }
                adapter.notifyDataSetChanged()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeEpisodes() {
        lifecycleScope.launchWhenCreated {
            viewModel.fetchEpisodeSearch.collectLatest {
                when (it) {
                    is UiState.Loading -> {
                    }
                    is UiState.Error -> {
                        Log.e("TAG", "observeEpisode: $it")
                    }
                    is UiState.Success -> it.data.map { character ->
                        character.toEpisodeEntity()
                    }.let { sorted ->
                        val sort = sorted.sortedByDescending { it1 ->
                            it1.episodeEntity.created
                        }
                        list.addAll(sort)
                    }

                    else -> {}
                }
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun setupUI() {
        etLogic()
        initRecycler()
    }

    private fun etLogic() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                searchItem(binding.etSearch.text.toString())
                list.clear()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchItem(binding.etSearch.text.toString())
                list.clear()
            }
        })
    }

    private fun initRecycler() {
        binding.rvAll.apply {
            adapter = this@SearchFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeCharacters() {
        lifecycleScope.launchWhenCreated {
            viewModel.fetchCharacterSearch.collectLatest {
                when (it) {
                    is UiState.Loading -> {
                    }
                    is UiState.Error -> {
                        Log.e("TAG", "observeEpisode: $it")
                    }
                    is UiState.Success -> it.data.map { character ->
                        character.toCharacterEntity()
                    }.let { sorted ->
                        val sort = sorted.sortedByDescending { it1 ->
                            it1.characterEntity.created
                        }
                        list.addAll(sort)
                    }

                    else -> {}
                }
                adapter.notifyDataSetChanged()
            }
        }
    }


}