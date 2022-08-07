package com.ozancanguz.notetakingapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ozancanguz.notetakingapplication.Model.Note
import com.ozancanguz.notetakingapplication.R

class NoteAdapter:RecyclerView.Adapter<NoteAdapter.noteViewHolder>(){
    var notes:List<Note> =ArrayList()


    class noteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val textviewTitle:TextView=itemView.findViewById(R.id.textViewtitle)
        val textViewDescription:TextView=itemView.findViewById(R.id.textViewdescription)
        val cardView:CardView=itemView.findViewById(R.id.cardview)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): noteViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)

        return noteViewHolder(v)
    }

    override fun onBindViewHolder(holder: noteViewHolder, position: Int) {

        var currentNote:Note=notes[position]
        holder.textviewTitle.text=currentNote.title
        holder.textViewDescription.text=currentNote.description
    }

    override fun getItemCount(): Int {
        return notes.size
    }


    fun setNote(myNotes:List<Note>){
        this.notes=myNotes
        notifyDataSetChanged()
    }

    fun getNote(position: Int):Note{
        return notes[position]

    }
}