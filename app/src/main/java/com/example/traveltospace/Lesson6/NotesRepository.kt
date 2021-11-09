package com.example.traveltospace.Lesson6

class NotesRepository : NotesRepositoryInterface {

    private var arrayList = ArrayList<Note>()

    private var numberOfNote = 1

    override fun addNote() {
        val note = Note("Заметка №$numberOfNote", "Описание заметки")
        arrayList.add(note)
        numberOfNote++
    }

    override fun removeNote(note: Note) {
        arrayList.remove(note)
    }

    override fun getNotes(): ArrayList<Note> {
        return arrayList
    }
}