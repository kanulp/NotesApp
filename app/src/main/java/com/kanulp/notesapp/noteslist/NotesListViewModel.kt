package com.kanulp.notesapp.noteslist

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kanulp.notesapp.util.SharedPref
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class NotesListViewModel(application: Application) : AndroidViewModel(application) {



    var noteLiveData : MutableLiveData<MutableList<NotesModel>>? = MutableLiveData()
    var notesList : ArrayList<NotesModel>? = ArrayList()
    var sharedPrefs = SharedPref.SharedPref
    val gson = Gson()
    var json_str : String? = null
    var context : Context? = null

    init {

        context = getApplication<Application>().applicationContext
    }

    fun addNote(note_title: String, note_details: String){

        var notesModel = NotesModel(note_title, note_details, getCurrDate())
        notesList?.add(notesModel)
        noteLiveData?.value = notesList
        savePrefs()

    }

    fun getNote(position: Int) : NotesModel?{
        return noteLiveData?.value?.get(position)
    }

    fun updateNote(position: Int, note_title: String, note_details: String){

        noteLiveData?.value?.get(position)?.note_title  =  note_title
        noteLiveData?.value?.get(position)?.note_details  = note_details
        noteLiveData?.value?.get(position)?.note_time = getCurrDate()
        savePrefs()
    }

    fun savePrefs(){
        val output: String = gson.toJson(noteLiveData?.value)
        sharedPrefs.setData(context!!,output)
    }

    fun getCurrDate() : String{
        val sdf = SimpleDateFormat("EEE MMM d, ''yy hh:mm a")
        val currentDate = sdf.format(Date())
        return currentDate
    }

    fun getDataFromPreference(){

        val noteType : Type = object :
            TypeToken<ArrayList<NotesModel?>?>() {}.type

        json_str = sharedPrefs.getData(context!!)

        if(json_str!=null) {
            val notesArray: ArrayList<NotesModel> =
                gson.fromJson(json_str, noteType)
            notesList?.clear()
            notesList?.addAll(notesArray)
            noteLiveData?.value = notesArray

        }

    }

}