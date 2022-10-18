package com.umutcansahin.noteapp.view

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.umutcansahin.noteapp.databinding.FragmentAddNoteBinding
import com.umutcansahin.noteapp.model.Note
import com.umutcansahin.noteapp.service.NoteDatabase
import com.umutcansahin.noteapp.viewmodel.AddNoteViewModel
import kotlinx.coroutines.*


class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private val  binding get() = _binding!!

    private lateinit var viewModel: AddNoteViewModel
    private var noteId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentAddNoteBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =ViewModelProvider(requireActivity()).get(AddNoteViewModel::class.java)


        arguments?.let {
            noteId = AddNoteFragmentArgs.fromBundle(it).id
            if (noteId != 0){
                viewModel.getNoteWithRoom(noteId)
                binding.saveNote.visibility = View.GONE
                binding.updateNote.visibility = View.VISIBLE
            }else {
                binding.updateNote.visibility = View.GONE
                binding.saveNote.visibility = View.VISIBLE
            }
        }

        addNote()

        observeLiveData()

        updateNote()


    }

    private fun observeLiveData() {
        viewModel.noteState.observe(viewLifecycleOwner, Observer { boolean ->
            boolean?.let {

                    val action = AddNoteFragmentDirections.actionAddNoteFragmentToShowAllNoteFragment()
                    view?.let { view ->
                        Navigation.findNavController(view).navigate(action)
                    }
            }

        })

        viewModel.noteLiveData.observe(viewLifecycleOwner, Observer { note ->
            note?.let {
                binding.noteText.text = Editable.Factory.getInstance().newEditable(note.note)
                binding.titleText.text = Editable.Factory.getInstance().newEditable(note.title)

            }
        })

    }
    private fun addNote() {
        binding.saveNote.setOnClickListener {
            val myTitle = binding.titleText.text.toString()
            val myNote = binding.noteText.text.toString()
            viewModel.addNoteWithRoom(myNote,myTitle)
        }
    }
    private fun updateNote() {
        binding.updateNote.setOnClickListener {
            val newTitle = binding.titleText.text.toString()
            val newNote = binding.noteText.text.toString()
            viewModel.updateNote(newTitle,newNote,noteId)
            val action = AddNoteFragmentDirections.actionAddNoteFragmentToShowAllNoteFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}
