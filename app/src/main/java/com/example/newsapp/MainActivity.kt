package com.example.newsapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class MainActivity : AppCompatActivity(),ItemClickLister {

    lateinit var viewModel: NotesViewModel
    lateinit var adapter: NotesAdapter
    lateinit var edit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        edit = findViewById<EditText>(R.id.description)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NotesAdapter(this, this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NotesViewModel::class.java]

        viewModel.allNotes.observe(this, Observer { item ->
            item?.let { adapter.onChange(it) }
        })


    }

    override fun onDelete(notes: Notes) {
        viewModel.onDelete(notes)
    }

    override fun onEdit(notes: Notes) {
        edit.setText(notes.title)
    }

    fun onClick(view: View) {
        val string = edit.text.toString()
        if (string.isNotEmpty()) {
            viewModel.insert(Notes(string,Date()))
        }
    }


}


