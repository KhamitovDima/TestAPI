package com.h.testapi.ui.entry

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.h.testapi.R
import com.h.testapi.model.Entry
import com.h.testapi.util.DateUtils
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_entry.*


class EntryFragment : DaggerFragment() {

    private var currentEntry: Entry? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentEntry = arguments?.getParcelable("CurrentEntry")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        date.text = "Create: " + DateUtils.convertDate(currentEntry?.da ?: 0)
        updateDate.text = "Update: " +  DateUtils.convertDate(currentEntry?.dm ?: 0)
        text.text = currentEntry?.body
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    companion object {
        fun newInstance() = EntryFragment()
    }
}