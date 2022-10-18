package com.umutcansahin.noteapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import com.umutcansahin.noteapp.model.Note
import com.umutcansahin.noteapp.service.NoteDatabase
import kotlinx.coroutines.launch

class AddNoteViewModel(application: Application) : BaseViewModel(application) {

    val noteState = MutableLiveData<Boolean>()
    val noteLiveData = MutableLiveData<Note>()

    fun addNoteWithRoom(note: String, title: String) {

        if (title.isEmpty()){
            Toast.makeText(getApplication(), "lütfen title giriniz", Toast.LENGTH_SHORT).show()

        }else if (note.isEmpty()) {
            Toast.makeText(getApplication(), "lütfen note giriniz", Toast.LENGTH_SHORT).show()
        }else {
            launch {
                val myNote = Note(title,note)
                NoteDatabase(getApplication()).noteDao().insert(myNote)
                noteState.value = true
            }
        }
    }

    fun getNoteWithRoom(id:Int) {
        launch {
            val note = NoteDatabase(getApplication()).noteDao().getNote(id)
            noteLiveData.value = note
        }
    }

    fun updateNote(newTitle: String, newNote: String, newId: Int) {
        launch {
                NoteDatabase(getApplication()).noteDao().update(newTitle,newNote,newId)
            }


        }

    }
