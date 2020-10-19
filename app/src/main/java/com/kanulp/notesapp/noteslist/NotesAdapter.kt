package com.kanulp.notesapp.noteslist

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kanulp.notesapp.R

class NotesAdapter(var context: Activity, var notes: List<NotesModel>, var listener: NotesAdapter.OnItemClickListener?) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    //var listener: OnItemClicked? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_notes_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.model = notes!![position]
        holder.tv_note_title?.text = notes!![position].note_title
        holder.tv_note_time?.text = "Created On : ${notes!![position].note_time}"
        holder.item_view?.setOnClickListener {
            listener?.onItemClick(position)
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int?)
    }

    override fun getItemCount(): Int {
        return notes!!.size
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(
            mView
    ) {
        var tv_note_title: TextView? =null
        var tv_note_time: TextView? = null
        var model: NotesModel? = null
        var item_view: LinearLayout? = null

        init {
            tv_note_title = mView.findViewById<View>(R.id.tv_note_title) as TextView
            tv_note_time = mView.findViewById<View>(R.id.tv_note_time) as TextView
            item_view = mView.findViewById<View>(R.id.item_view) as LinearLayout
        }
    }
}