package com.example.newsapp

import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(val context: Context, private val itemClickLister: ItemClickLister): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    var list:ArrayList<Notes> = arrayListOf<Notes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
      val view= LayoutInflater.from(parent.context).inflate(R.layout.layout_item,parent,false)
        val viewHolder = NotesViewHolder(view)
        viewHolder.deleteButton.setOnClickListener { itemClickLister.onDelete(list[viewHolder.adapterPosition]) }
        viewHolder.editButton.setOnClickListener { itemClickLister.onEdit(list[viewHolder.adapterPosition]) }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note= list[position]
        holder.text.text=note.title
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun onChange(item:List<Notes>){
        list.clear()
        list.addAll(item)
        notifyDataSetChanged()

    }

    inner class NotesViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val editButton: ImageView= item.findViewById(R.id.edit)
        val deleteButton: ImageView =item.findViewById(R.id.delete)
        val text: TextView =item.findViewById(R.id.text)

    }
}

interface ItemClickLister{
    fun onDelete(notes: Notes)
    fun onEdit(notes: Notes)
}