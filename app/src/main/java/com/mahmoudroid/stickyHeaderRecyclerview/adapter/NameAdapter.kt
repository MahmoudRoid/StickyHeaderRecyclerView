package com.mahmoudroid.stickyHeaderRecyclerview.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudroid.stickyHeaderRecyclerview.databinding.ItemRecyclerBinding
import com.mahmoudroid.stickyHeaderRecyclerview.model.DummyModel

class NameAdapter: RecyclerView.Adapter<NameAdapter.ViewHolder>() {

    private val TAG = NameAdapter::class.java.simpleName
    private var dummyHeaders: List<Char> = listOf()
    var dummyData: Map<Char, List<DummyModel>> = emptyMap()
        set(value) {
            field = value
            dummyHeaders = dummyData.keys.toList()
        }


    override fun getItemCount() = dummyData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "onCreateViewHolder: ")
        return ViewHolder(ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: ")
        holder.bind(dummyHeaders[position])
    }

    inner class ViewHolder(private var itemBinding: ItemRecyclerBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(header: Char) {
            itemBinding.tvHeader.text = "$header"
            dummyData[header]?.let { items ->
                itemBinding.dummyDetailsView.dummies = items
            }
        }
    }

    fun getHeaderForCurrentPosition(position: Int) = if (position in dummyHeaders.indices) {
        dummyHeaders[position]
    } else {
        ""
    }

}