package com.umutcansahin.noteapp.viewmodel


import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umutcansahin.noteapp.model.Note
import com.umutcansahin.noteapp.service.NoteDatabase
import kotlinx.coroutines.*

class ShowAllNoteViewModel(application: Application) : BaseViewModel(application) {

    val notes = MutableLiveData<List<Note>>()

    fun getNote() {
        launch {
            val getData = NoteDatabase(getApplication()).noteDao().getAllNote()
            notes.value = getData
        }

    }
}