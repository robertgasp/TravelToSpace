package com.example.traveltospace.Lesson6

import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.core.view.MotionEventCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.traveltospace.databinding.CardForNotesBinding

class NotesAdapter(private val fragment: Fragment, private val dragListener: OnStartDragListener) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(), ItemTouchHelperAdapter {

    private val notesRepository: NotesRepositoryInterface = NotesRepository()

    private var notesArray: ArrayList<Note> = ArrayList<Note>()

    fun addNote(position: Int) {
        notesRepository.addNote(position)
        notesArray.add(position, notesRepository.getNote(position))
        notifyItemInserted(position - 1)
    }

    fun removeNote(position: Int) {
        notesRepository.removeNote(position)
        notesArray.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setData(arrayList: ArrayList<Note>) {
        notesArray.addAll(arrayList)
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

/*    override fun onBindViewHolder(
        holder: NotesViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        }else{
            val combinedChange = createCombinedPayload(payloads as List<Note>)
        }
    }*/

    override fun getItemCount(): Int {
        return notesArray.size
    }

    inner class NotesViewHolder(private val binding: CardForNotesBinding) :
        RecyclerView.ViewHolder(binding.root), ItemTouchHelperViewHolder {

        fun bind(note: Note) = with(binding) {
            cardNoteTitle.text = note.noteTitle
            cardNoteDescription.text = note.noteDescription

            addNewNote.setOnClickListener {
                addNote(notesArray.indexOf(note) + 1)
            }

            removeNote.setOnClickListener {
                removeNote(notesArray.indexOf(note))
            }

            dragMove.setOnTouchListener { _, motionEvent ->
                if (MotionEventCompat.getActionIndex(motionEvent) == MotionEvent.ACTION_DOWN) {
                    dragListener.onStartDrag(this@NotesViewHolder)
                }
                false
            }
        }

        override fun onNoteSelected() {
            binding.cardNote.setBackgroundColor(Color.RED)
        }

        override fun onNoteClear() {
            binding.cardNote.setBackgroundColor(0)
        }
    }

    override fun onNoteMove(fromPosition: Int, toPosition: Int) {
        val singleNote = notesRepository.getNote(fromPosition)
        notesRepository.removeNote(fromPosition)
        notesRepository.addNote(
            if (toPosition > fromPosition) toPosition - 1 else toPosition, singleNote
        )

        notesArray.removeAt(fromPosition).apply {
            notesArray.add(
                if (toPosition > fromPosition) toPosition - 1 else toPosition, this
            )
        }
        notifyItemMoved(fromPosition, toPosition)

    }

    override fun onNoteDismiss(position: Int) {
        removeNote(position)
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