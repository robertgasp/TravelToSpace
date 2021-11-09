package com.example.traveltospace.Lesson6

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.traveltospace.databinding.CardForNotesBinding

class NotesAdapter(private val fragment: Fragment, private var notes: LiveData<List<Note>?>?) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var notesArray: List<Note> = listOf()
    private val fabClickListener: FABClickListener?=null

    fun setData(arrayList: List<Note>) {
        notesArray = arrayList
    }

    fun fabClick(){
            fabClickListener?.fabClickListener()
        Toast.makeText(Lesson6Fragment().context, "FAB", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesAdapter.NotesViewHolder {
        val binding =
            CardForNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesAdapter.NotesViewHolder, position: Int) {
        holder.bind(notesArray[position])
    }

    override fun getItemCount(): Int {
        return notesArray.size
    }


    inner class NotesViewHolder(private val binding: CardForNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) = with(binding) {
            cardNoteTitle.text = note.noteTitle
            cardNoteDescription.text = note.noteDescription
        }
    }

    inner class NoteListDiffUtilCallBack(
        private val oldList: List<Note>,
        private val newList: List<Note>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size


        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].noteTitle == newList[newItemPosition].noteTitle
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}