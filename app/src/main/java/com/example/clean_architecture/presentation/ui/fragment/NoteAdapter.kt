package com.example.clean_architecture.presentation.ui.fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.clean_architecture.data.model.NoteEntity
import com.example.clean_architecture.databinding.ItemNoteBinding
import java.text.SimpleDateFormat

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var list = arrayListOf<NoteEntity>()


    inner class NoteViewHolder(private var binding: ItemNoteBinding) : ViewHolder(binding.root){
        @SuppressLint("SimpleDateFormat")
        fun bind(note: NoteEntity) {
            binding.itemTitle.text=note.title
            binding.itemDescription.text=note.description
            val simpleDateFormat = SimpleDateFormat("dd MMMM, hh:mm")
            val dateString = simpleDateFormat.format(note.createdAt)
            binding.itemCreatedAt.text = String.format("%s", dateString)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
