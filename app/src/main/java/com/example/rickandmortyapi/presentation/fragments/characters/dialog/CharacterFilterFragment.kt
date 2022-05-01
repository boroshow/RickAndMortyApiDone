package com.example.rickandmortyapi.presentation.fragments.characters.dialog

import android.os.Bundle
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
import com.example.rickandmortyapi.databinding.FragmentCharacterFiterBinding

class CharacterFilterFragment : DialogFragment() {

    private val binding: FragmentCharacterFiterBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_fiter, container, false)
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
        // radio group checked change listener
        binding.submit.setOnClickListener {
            val checkedRadioButtonId = binding.groupStatus.checkedRadioButtonId
            val checkedRadioButtonId2 = binding.groupGender.checkedRadioButtonId

            if (checkedRadioButtonId > -1 && checkedRadioButtonId2 > -1) {
                val selectedRadioButton =
                    view.findViewById<RadioButton>(checkedRadioButtonId)
                val selectedRadioButton2 =
                    view.findViewById<RadioButton>(checkedRadioButtonId2)

                findNavController().navigate(CharacterFilterFragmentDirections.actionCharacterFilterFragmentToCharactersFragment(
                    selectedRadioButton.text.toString(),
                    selectedRadioButton2.text.toString())
                )
            } else if (checkedRadioButtonId == -1 && checkedRadioButtonId2 > -1) {
                val selectedRadioButton2 =
                    view.findViewById<RadioButton>(checkedRadioButtonId2)
                findNavController().navigate(CharacterFilterFragmentDirections.actionCharacterFilterFragmentToCharactersFragment(
                    null,
                    selectedRadioButton2.text.toString()))
            } else if (checkedRadioButtonId > -1 && checkedRadioButtonId2 == -1) {
                val selectedRadioButton =
                    view.findViewById<RadioButton>(checkedRadioButtonId)
                findNavController().navigate(CharacterFilterFragmentDirections.actionCharacterFilterFragmentToCharactersFragment(
                    selectedRadioButton.text.toString(),
                    null))
            } else {
                Toast.makeText(requireContext(), "Nothing enter", Toast.LENGTH_SHORT).show()
            }
        }
        binding.cancel.setOnClickListener {
            findNavController().navigate(R.id.action_characterFilterFragment_to_charactersFragment)
        }

    }

}

