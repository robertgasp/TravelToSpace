package com.example.traveltospace.Lesson6

import android.widget.Toast
import androidx.fragment.app.Fragment

class NotesRepository : NotesRepositoryInterface {

    private val littleArray = Note("sgsg","sgsgse")
    private val littleArray2 = Note("sgsgaffesefsef","sgsgssefsfsfesfe")

    private var arrayList = ArrayList<Note>()


    private var numberOfNote = 1

    override fun addNote(position:Int) {
        arrayList.add(position, Note("Заметка №$numberOfNote", "Описание заметки"))
        numberOfNote++
    }

    override fun addNote(position: Int, note: Note) {
        arrayList.add(position,note)
    }

    override fun removeNote(note: Note) {
        arrayList.remove(note)
    }

    override fun removeNote(position: Int) {
        arrayList.removeAt(position)
    }

    override fun getNote(position: Int):Note {
        return arrayList[position]
    }

    override fun getNotes(): ArrayList<Note> {
        return arrayList
    }

/*    override fun fabClickListener(fragment: Fragment) {
        //Toast.makeText(fragment.context, "NotesIsAdded", Toast.LENGTH_SHORT).show()
        addNote(arrayList.size-1)
    }*/
}