package com.example.rickandmortyapi.presentation.fragments.episodes.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.databinding.FragmentEpisodeFilterBinding
import com.example.rickandmortyapi.presentation.fragments.characters.dialog.CharacterFilterFragmentDirections

class EpisodeFilterFragment : DialogFragment() {

    private val binding: FragmentEpisodeFilterBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_episode_filter, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupClickListeners(view: View) {
        binding.submit.setOnClickListener {
            findNavController().navigate(EpisodeFilterFragmentDirections.actionEpisodeFilterFragmentToEpisodesFragment(
                binding.etEpisode.text.toString()  ))
        }
        binding.cancel.setOnClickListener {
            findNavController().navigate(R.id.action_characterFilterFragment_to_charactersFragment)
        }

    }
}