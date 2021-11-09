package com.example.traveltospace.Lesson6

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteListViewModel : ViewModel(), FABClickListener {
    private val notesListData = MutableLiveData<List<Note>>()
    private val notesRepository: NotesRepositoryInterface = NotesRepository()

    fun getNotesLiveData(): LiveData<List<Note>?> {
        requestNotes()
        return notesListData
    }

    fun addClicked() {
        Toast.makeText(Lesson6Fragment().context, "AddClicked", Toast.LENGTH_SHORT).show()
        notesRepository.addNote()
        notesListData.value = notesRepository.getNotes()
    }

    fun removeClicked(note: Note) {
        notesRepository.removeNote(note)
        notesListData.value = notesRepository.getNotes()
    }

    fun requestNotes() {
        notesListData.value = notesRepository.getNotes()
    }

    override fun fabClickListener() {
        Toast.makeText(Lesson6Fragment().context, "NotesIsAdded", Toast.LENGTH_SHORT).show()
        addClicked()
    }

}