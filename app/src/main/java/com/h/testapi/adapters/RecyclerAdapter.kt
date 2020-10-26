package com.h.testapi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.h.testapi.R
import com.h.testapi.model.Entry
import com.h.testapi.util.DateUtils
import kotlinx.android.synthetic.main.entry_list_item.view.*

class RecyclerAdapter(onCardListener: OnCardListener) : RecyclerView.Adapter<RecyclerAdapter.EntryHolder>() {
    private  val TAG = "RecyclerAdapter"
    private var entries: List<Entry> = ArrayList()
    private var entriesOld: List<Entry> = ArrayList()
    private val mOnCardListener = onCardListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.entry_list_item, parent, false)
        return EntryHolder(itemView, mOnCardListener)

    }

    override fun getItemCount(): Int {
        return entries.size
    }

    override fun onBindViewHolder(holder: EntryHolder, position: Int) {
        val currentEntry: Entry = entries.get(position)
        holder.textViewBody.text = currentEntry.body
        holder.textViewDate.text = DateUtils.convertDate(currentEntry.da)
    }

    fun setEntriesInAdapter(entries: List<Entry>) {
        entriesOld=this.entries
        this.entries=entries
        updateList(entriesOld,this.entries)
        notifyDataSetChanged()
    }

    private fun updateList(old:List<Entry>, new:List<Entry>) {
        val callback = DiffCallback(old, new)
        DiffUtil.calculateDiff((callback)).dispatchUpdatesTo(this)
    }

    class EntryHolder(itemView: View, var entryListener: OnCardListener) :
            RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var textViewBody: TextView
        var textViewDate: TextView


        init {
            textViewBody = itemView.body
            textViewDate = itemView.da

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            entryListener.onCardClick(adapterPosition)
        }
    }
}