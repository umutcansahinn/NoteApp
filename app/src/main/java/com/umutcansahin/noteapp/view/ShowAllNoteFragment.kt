package com.umutcansahin.noteapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.umutcansahin.noteapp.R
import com.umutcansahin.noteapp.adapter.NoteAdapter
import com.umutcansahin.noteapp.databinding.FragmentShowAllNoteBinding
import com.umutcansahin.noteapp.viewmodel.ShowAllNoteViewModel

class ShowAllNoteFragment : Fragment() {

    private var _binding: FragmentShowAllNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ShowAllNoteViewModel

    private val recyclerViewAdapter = NoteAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowAllNoteBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ShowAllNoteViewModel::class.java)
        viewModel.getNote()

        binding.recyclerView.layoutManager = GridLayoutManager(context,1)
        binding.recyclerView.adapter = recyclerViewAdapter

        binding.fab.setOnClickListener {

            val action = ShowAllNoteFragmentDirections.actionShowAllNoteFragmentToAddNoteFragment(0)
            Navigation.findNavController(view).navigate(action)

        }

    observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.notes.observe(viewLifecycleOwner, Observer { notes ->
            notes?.let {
                recyclerViewAdapter.updateNoteList(it)
            }
        })
    }
}