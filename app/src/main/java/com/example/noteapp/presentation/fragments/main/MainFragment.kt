package com.example.noteapp.presentation.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.core.BaseFragment
import com.example.noteapp.core.UIState
import com.example.noteapp.databinding.FragmentMainBinding
import com.example.noteapp.domain.model.room.Note
import com.example.noteapp.extensions.invisible
import com.example.noteapp.extensions.showToast
import com.example.noteapp.extensions.visible
import com.example.noteapp.presentation.fragments.update.UpdateFragment
import com.example.noteapp.presentation.ui.main_activity.MainViewModel
import com.example.noteapp.presentation.ui.main_activity.NoteAdapter

class MainFragment : BaseFragment(), NoteAdapter.ItemClickListener {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)


        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        setFragmentResultListener("1") { key, bundle ->
            val result = bundle.getSerializable("key")
            viewModel.addNote(result as Note)
        }

        setFragmentResultListener("2") { key, bundle ->
            val result = bundle.getSerializable(UpdateFragment.updateNoteKey)
            viewModel.updateNote(result as Note)
        }

        initClickers()
        viewModel.getAllNotes()

        viewModel.addNotesState.subscribe {
            when (it) {
                is UIState.Error -> {
                    requireActivity().showToast(it.error)
                }
                is UIState.Loading -> {
                    binding.progressBar.visible()
                }
                is UIState.Success -> {
                    requireActivity().showToast("Add Note")
                    binding.progressBar.invisible()
                    viewModel.getAllNotes()
                }
            }
        }
        viewModel.noteState.subscribe {
            when (it) {
                is UIState.Error -> {
                    requireActivity().showToast(it.error)
                }
                is UIState.Loading -> {
                    binding.progressBar.visible()
                }
                is UIState.Success -> {
                    requireActivity().showToast("Get All Notes")
                    binding.progressBar.invisible()
                    adapter = NoteAdapter(it.data)
                    binding.rvNotes.adapter = adapter
                    adapter.setItemClickListener(this)
                }
            }
        }
        viewModel.deleteNotesState.subscribe {
            when (it) {
                is UIState.Error -> {
                    requireActivity().showToast(it.error)
                }
                is UIState.Loading -> {
                    binding.progressBar.visible()
                }
                is UIState.Success -> {
                    requireActivity().showToast("Delete Note")
                    binding.progressBar.invisible()
                    viewModel.getAllNotes()
                }
            }

        }
        viewModel.updateNoteState.subscribe {
            when (it) {
                is UIState.Error -> {
                    requireActivity().showToast(it.error)
                }
                is UIState.Loading -> {
                    binding.progressBar.visible()
                }
                is UIState.Success -> {
                    requireActivity().showToast("Update Note")
                    binding.progressBar.invisible()
                    viewModel.getAllNotes()
                }
            }
        }
    }

    private fun initClickers() {
        binding.fubAdd.setOnClickListener {
            findNavController().navigate(R.id.addNoteFragment)
        }
    }

    override fun onItemClick(note: Note) {
        val bundle = Bundle()
        bundle.putSerializable(keyNote, note)
        findNavController().navigate(R.id.updateFragment, bundle)
    }

    override fun onLongClickListener(note: Note) {
        viewModel.deleteNote(note)
    }

    companion object {
        const val keyNote = "keynote"
    }
}