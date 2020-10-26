package com.h.testapi.ui.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.h.testapi.R
import com.h.testapi.adapters.OnCardListener
import com.h.testapi.adapters.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.h.testapi.model.Entry
import com.h.testapi.ui.add_entry.AddEntryFragment
import com.h.testapi.ui.entry.EntryFragment
import com.h.testapi.util.Status
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class ListFragment : DaggerFragment(), OnCardListener {
    private val TAG = "ListFragment"
    private lateinit var adapter: RecyclerAdapter
    private lateinit var entries: List<Entry>

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var listViewModel: ListViewModel

    override fun onCardClick(position: Int) {
        val entryFragment = EntryFragment.newInstance()
        val bundle = Bundle()
        bundle.putParcelable("CurrentEntry", entries[position])
        entryFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, entryFragment)
            .addToBackStack(null)
            .commit()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listViewModel = ViewModelProvider(this, factory)[ListViewModel::class.java]
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listViewModel.getEntries()
        setupUI()
        observeData()
        observeStatus()
    }

    fun setupUI() {
        recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = RecyclerAdapter(this)
        recycler_view.adapter = adapter

        fab.setOnClickListener {
            val addEntryFragment = AddEntryFragment.newInstance()
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, addEntryFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    fun observeData() {
        listViewModel.getList().observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "getList = $it")
            entries = it
            adapter.setEntriesInAdapter(entries)
        })
    }

    fun observeStatus() {
        listViewModel.status.observe(viewLifecycleOwner, Observer {
            if (it == Status.ERROR || it == Status.SESSION_ERROR)
                showDialog()
        })
    }

    fun showDialog() {
        AlertDialog.Builder(requireContext())
            .setMessage("Connection error")
            .setPositiveButton("Retry") { _, _ -> listViewModel.getEntries() }
            .setNegativeButton("Cancel") { it, _ -> it.dismiss() }
            .show()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    companion object {
        fun newInstance() = ListFragment()
    }
}