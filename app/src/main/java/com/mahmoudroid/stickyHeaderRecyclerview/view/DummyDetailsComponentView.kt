package com.mahmoudroid.stickyHeaderRecyclerview.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import com.mahmoudroid.stickyHeaderRecyclerview.R
import com.mahmoudroid.stickyHeaderRecyclerview.databinding.ViewDummyDetailsComponentBinding
import com.mahmoudroid.stickyHeaderRecyclerview.databinding.ViewDummyDetailsListItemBinding
import com.mahmoudroid.stickyHeaderRecyclerview.ext.requestLayoutForChangedDataset
import com.mahmoudroid.stickyHeaderRecyclerview.model.DummyModel

class DummyDetailsComponentView : ConstraintLayout {

    private lateinit var binding: ViewDummyDetailsComponentBinding
    private lateinit var dummyItemBinding: ViewDummyDetailsListItemBinding
    private lateinit var adapter: DetailAdapter

    var dummies: List<DummyModel> = emptyList()
        set(value) {
            field = value
            onItemsUpdated()
        }

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    private fun init(context: Context) {
        binding = ViewDummyDetailsComponentBinding.inflate(LayoutInflater.from(context), this, true)
        adapter = DetailAdapter(context)
        binding.dummyDetailsList.adapter = adapter
    }

    private fun onItemsUpdated() {
        adapter.notifyDataSetChanged()
        binding.dummyDetailsList.requestLayoutForChangedDataset()
    }

    inner class DetailAdapter(private val context: Context) : BaseAdapter() {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val dummyModel: DummyModel = dummies[position]
            var view: View? = convertView

            if (view == null) {
                view = LayoutInflater.from(context)
                    .inflate(R.layout.view_dummy_details_list_item, parent, false)
                dummyItemBinding = ViewDummyDetailsListItemBinding.bind(view)
                view.tag = dummyItemBinding
            } else {
                dummyItemBinding = view.tag as ViewDummyDetailsListItemBinding
            }

            dummyItemBinding.apply {
                tvName.text = dummyModel.name
                tvAge.text = dummyModel.age.toString()
            }

            return dummyItemBinding.root
        }

        override fun getItem(position: Int): Any {
            return dummies[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return dummies.size
        }

        override fun isEnabled(position: Int): Boolean {
            return false
        }
    }
}