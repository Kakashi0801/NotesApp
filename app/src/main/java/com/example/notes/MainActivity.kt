package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), INotesRVAdapter {

    lateinit var viewModel: NoteViewModel
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)
        binding!!.recyclerView.layoutManager= LinearLayoutManager(this)
        val adapter = NotesRecyclerAdapter(this,this)
        binding!!.recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes!!.observe(this, Observer { list->
            adapter.updateList(list as ArrayList<Notes>)
        })

    }

    override fun onItemClick(note: Notes) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.text} deleted",Toast.LENGTH_LONG).show()
    }

    fun submitData(view: View) {
        val noteText = binding!!.input.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Notes(noteText))
            Toast.makeText(this,"${noteText} inserted",Toast.LENGTH_LONG).show()
        }
        binding!!.input.text.clear()
    }
}