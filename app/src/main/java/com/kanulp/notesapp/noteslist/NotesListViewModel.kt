package com.kanulp.notesapp.noteslist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class NotesListViewModel : ViewModel(){


    var noteLiveData : MutableLiveData<MutableList<NotesModel>>? = MutableLiveData()
    var notesList : ArrayList<NotesModel>? = ArrayList()


    fun addNote(note_title: String, note_details: String){

        val sdf = SimpleDateFormat("EEE MMM d, ''yy hh:mm a")
        val currentDate = sdf.format(Date())

        var notesModel = NotesModel(note_title, note_details, currentDate)
        notesList?.add(notesModel)
        noteLiveData?.value = notesList

        Log.d("VIEWMODEL", "data noteslistLive : ${noteLiveData?.value}")

    }

    fun getNote(position: Int) : NotesModel?{
        return notesList?.get(position)
    }

    fun updateNote(position: Int,note_title: String, note_details: String){
        noteLiveData?.value?.get(position)?.note_title  =  note_title
        noteLiveData?.value?.get(position)?.note_details  = note_details
    }


}