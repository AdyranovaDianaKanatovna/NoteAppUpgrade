package com.example.noteapp.presentation.fragments.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.Fragment
import com.example.noteapp.databinding.FragmentUpdateBinding
import com.example.noteapp.domain.model.room.Note
import com.example.noteapp.extensions.showToast
import com.example.noteapp.presentation.fragments.main.MainFragment

class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val note = arguments?.getSerializable(MainFragment.keyNote) as Note
        binding.etTitleUpdate.setText(note.title)
        binding.etDescriptionUpdate.setText(note.description)

        binding.btnUpdate.setOnClickListener {
            val title = binding.etTitleUpdate.text.toString()
            val description = binding.etDescriptionUpdate.text.toString()
            when {
                title.isEmpty() -> {
                    requireContext().showToast("Enter title")
                }
                description.isEmpty() -> {
                    requireContext().showToast("Enter description")
                }
                else -> {
                    val bundle = Bundle()
                    bundle.putSerializable(
                        updateNoteKey,
                        Note(id = note.id, title = title, description = description)
                    )
                    parentFragmentManager.setFragmentResult("2", bundle)
                    findNavController().navigateUp()

                }
            }
        }
    }

    companion object {
        const val updateNoteKey = "updateNoteKey"
    }
}
