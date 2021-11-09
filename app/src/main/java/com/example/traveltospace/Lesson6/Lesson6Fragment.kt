package com.example.traveltospace.Lesson6

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.traveltospace.databinding.FragmentLesson6Binding
import java.util.*

class Lesson6Fragment : Fragment() {

    private var _binding: FragmentLesson6Binding? = null
    private val binding get() = _binding!!

    private val notesViewModel: NoteListViewModel? = null

    private var notes = notesViewModel?.getNotesLiveData()
    private var fabClickListener: FABClickListener? = null
    private var contextListFragment: Context? = null

    private var notesAdapter: NotesAdapter? = null
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FABClickListener) {
            contextListFragment = context
            fabClickListener = context
        }
    }

    override fun onDetach() {
        contextListFragment = null
        super.onDetach()
    }

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
        initRecyclerView(binding.recyclerViewContainerForNotes, notes)
        notesViewModel?.getNotesLiveData()?.observe(viewLifecycleOwner, { notes ->
            notes?.let {
                notesAdapter?.setData(it)
            }
        })

        binding.fabNewNote.setOnClickListener {
            notesViewModel?.addClicked()
            Toast.makeText(context, "FABButtonIsClicked", Toast.LENGTH_SHORT).show()
        }
    }

    fun initRecyclerView(notesRecyclerView: RecyclerView, notes: LiveData<List<Note>?>?) {
        val lm = LinearLayoutManager(context)
        notesRecyclerView.layoutManager = lm
        notesAdapter = NotesAdapter(this, notes)
        notesRecyclerView.adapter = notesAdapter
    }
}