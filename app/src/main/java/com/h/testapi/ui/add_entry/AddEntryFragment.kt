package com.h.testapi.ui.add_entry

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.h.testapi.R
import com.h.testapi.util.Status
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_add_entry.*
import javax.inject.Inject


class AddEntryFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var addEntryViewModel: AddEntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addEntryViewModel = ViewModelProvider(this, factory)[AddEntryViewModel::class.java]
        return inflater.inflate(R.layout.fragment_add_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeStatus()

        save.setOnClickListener {
            save()
        }

        cancel.setOnClickListener {
            val fm: FragmentManager = requireActivity().supportFragmentManager
            fm.popBackStack()
        }

    }

    fun save() {
        if (edit_text.text.toString() == "") {
            Toast.makeText(context, "Enter text...", Toast.LENGTH_SHORT).show()
            return
        }
        addEntryViewModel.addEntry(edit_text.text.toString())
    }

    fun observeStatus() {
        addEntryViewModel.status.observe(viewLifecycleOwner, Observer {
            if (it == Status.DONE) {
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
                val fm: FragmentManager = requireActivity().supportFragmentManager
                fm.popBackStack()
            }
            else if (it == Status.ERROR)
                showDialog()

        })
    }


    fun showDialog() {
        AlertDialog.Builder(requireContext())
            .setMessage("Connection error")
            .setPositiveButton("Retry") {_, _ -> save()}
            .setNegativeButton("Cancel") {it, _ -> it.dismiss()}
            .show()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    companion object {
        fun newInstance() = AddEntryFragment()
    }
}