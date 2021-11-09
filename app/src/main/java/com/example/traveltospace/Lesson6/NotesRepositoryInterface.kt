package com.example.traveltospace.Lesson6

interface NotesRepositoryInterface {
    fun addNote()
    fun removeNote(note: Note)
    fun getNotes(): ArrayList<Note>

}