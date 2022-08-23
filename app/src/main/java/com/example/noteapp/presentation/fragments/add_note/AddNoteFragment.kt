package com.example.noteapp.presentation.fragments.add_note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.noteapp.databinding.FragmentAddNoteBinding
import com.example.noteapp.domain.model.room.Note
import com.example.noteapp.extensions.showToast

class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickers()
    }
    private fun initClickers() {
        binding.btnEnter.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            when {
                title.isEmpty() -> {
                    requireContext().showToast("Enter title")
                }
                description.isEmpty() -> {
                    requireContext().showToast("Enter description")
                }
                else -> {
                    val bundle = Bundle()
                    bundle.putSerializable("key", Note(title = title, description = description))
                    parentFragmentManager.setFragmentResult("1",bundle)
                    findNavController().navigateUp()
                }
            }
        }
    }
}