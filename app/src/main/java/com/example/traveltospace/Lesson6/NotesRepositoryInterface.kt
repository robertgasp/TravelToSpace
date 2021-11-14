package com.example.traveltospace.Lesson6

interface NotesRepositoryInterface {
    fun addNote(position:Int)
    fun addNote(position:Int, note:Note)
        fun removeNote(note: Note)
    fun removeNote(position: Int)
    fun getNote(position: Int):Note
    fun getNotes(): List<Note>

}