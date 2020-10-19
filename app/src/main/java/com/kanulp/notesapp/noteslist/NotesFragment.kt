package com.kanulp.notesapp.noteslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kanulp.notesapp.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NotesFragment : Fragment(){

    private var viewModel: NotesListViewModel? = null
    var recyclerView : RecyclerView? = null
    var notesAdapter : NotesAdapter? = null


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_notes, container, false)
        recyclerView = view.findViewById(R.id.rv)

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.action_NotesListFragment_to_EditNoteFragment)
        }

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(NotesListViewModel::class.java)
        setupListUpdate()
    }




    private fun setupListUpdate() {
        viewModel?.noteLiveData?.observe(viewLifecycleOwner, object : Observer<List<NotesModel>> {
            override fun onChanged(t: List<NotesModel>?) {
                if (t != null) {
                    notesAdapter = NotesAdapter(activity!!, t!!, object: NotesAdapter.OnItemClickListener {
                        override fun onItemClick(position: Int?) {
                            val bundle = bundleOf("position" to position)
                            findNavController().navigate(R.id.action_NotesListFragment_to_EditNoteFragment, bundle)
                        }
                    });
                    recyclerView?.layoutManager = LinearLayoutManager(context);
                    recyclerView?.adapter = notesAdapter;
                } else {
                    Toast.makeText(activity, "No Data found", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }


}