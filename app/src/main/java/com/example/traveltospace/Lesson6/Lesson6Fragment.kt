package com.example.traveltospace.Lesson6

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.traveltospace.databinding.FragmentLesson6Binding
import kotlin.collections.ArrayList

class Lesson6Fragment : Fragment() {

    private var _binding: FragmentLesson6Binding? = null
    private val binding get() = _binding!!

    private val notesRepository: NotesRepositoryInterface = NotesRepository()

    private var notesAdapter: NotesAdapter? = null
    private lateinit var itemTouchHelper: ItemTouchHelper



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLesson6Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(binding.recyclerViewContainerForNotes)


        binding.fabNewNote.setOnClickListener {
            if (notesRepository.getNotes().isEmpty()) {
                notesAdapter?.addNote(notesRepository.getNotes().size)
            } else
                notesAdapter?.addNote(notesRepository.getNotes().size - 1)
        }

        ItemTouchHelper(NoteTouchHelperCallback(notesAdapter!!)).attachToRecyclerView(binding.recyclerViewContainerForNotes)

        notesAdapter?.setData(notesRepository.getNotes() as ArrayList<Note>)
    }

    fun initRecyclerView(notesRecyclerView: RecyclerView) {
        val lm = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        notesRecyclerView.layoutManager = lm
        notesAdapter = NotesAdapter(this, object : OnStartDragListener {
            override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
                itemTouchHelper.startDrag(viewHolder)
            }
        })
        notesRecyclerView.adapter = notesAdapter
    }
}