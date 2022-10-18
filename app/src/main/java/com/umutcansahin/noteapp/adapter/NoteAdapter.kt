package com.umutcansahin.noteapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.umutcansahin.noteapp.R
import com.umutcansahin.noteapp.databinding.NoteRowBinding
import com.umutcansahin.noteapp.model.Note
import com.umutcansahin.noteapp.view.ShowAllNoteFragmentDirections
import kotlin.coroutines.coroutineContext

class NoteAdapter(val noteList: ArrayList<Note>): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(val binding: NoteRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemBinding = NoteRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.binding.recyclerTitle.text = noteList[position].title
        holder.binding.recyclerNote.text = noteList[position].note

        holder.itemView.setOnClickListener {
            val id = noteList[position].id!!
            val action = ShowAllNoteFragmentDirections.actionShowAllNoteFragmentToAddNoteFragment(id)
            Navigation.findNavController(it).navigate(action)

        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }


    fun updateNoteList(newNoteList: List<Note>) {
        noteList.clear()
        noteList.addAll(newNoteList)
        notifyDataSetChanged()
    }
}