package com.kanulp.notesapp.notes_edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kanulp.notesapp.R
import com.kanulp.notesapp.noteslist.NotesListViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class EditNoteFragment : Fragment() {

    var ed_note_title : EditText? = null
    var ed_note_description : EditText? = null
    var btn_action : Button? = null
    var tv_title : TextView? = null

    var viewModel : NotesListViewModel? = null

    var fromEdit : Boolean? = false
    var position : Int? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_edit_note, container, false)

        bindView(view)

        return view
    }

    private fun bindView(view: View?) {
        ed_note_title = view?.findViewById(R.id.ed_note_title)
        ed_note_description = view?.findViewById(R.id.ed_note_description)
        btn_action = view?.findViewById(R.id.btn_action)
        tv_title = view?.findViewById(R.id.tv_title)

        btn_action?.setOnClickListener {

            var title = ed_note_title?.text.toString()
            var desc = ed_note_description?.text.toString()

            if(fromEdit!!){
                viewModel?.updateNote(position?:0,title, desc)
                Toast.makeText(activity,"Note Updated Successfully!",Toast.LENGTH_SHORT).show()
            }else {
                viewModel?.addNote(title, desc)
                Toast.makeText(activity,"Note Added Successfully!",Toast.LENGTH_SHORT).show()

            }
            findNavController().navigate(R.id.action_EditNoteFragment_to_NotesListFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(NotesListViewModel::class.java)

        //var title = arguments?.getString("title")
        //var desc = arguments?.getString("desc")

        position = arguments?.getInt("position")
        if(position!=null) {
            btn_action?.text = "Update"
            tv_title?.text = "Update Note"
            fromEdit = true
            var model = viewModel?.getNote(position = position!!)
            ed_note_title?.setText(model?.note_title)
            ed_note_description?.setText(model?.note_details)
        }
        else{
            btn_action?.text = "Add"
            tv_title?.text = "Add New Note"
        }

    }

}