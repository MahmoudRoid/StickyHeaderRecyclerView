package com.mahmoudroid.stickyHeaderRecyclerview.ext

import android.widget.ListView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView


fun RecyclerView.setDivider(
    dividerResId: Int? = null,
    orientation: Int = DividerItemDecoration.VERTICAL
) {
    val dividerItemDecoration = DividerItemDecoration(this.context, orientation)
    dividerResId?.let { resId ->
        this.context.getDrawable(resId)
            ?.let { divider ->
                dividerItemDecoration.setDrawable(divider)
            }
    }
    this.addItemDecoration(dividerItemDecoration)
}


fun ListView.requestLayoutForChangedDataset() {

    val listAdapter = this.adapter
    listAdapter?.let { adapter ->
        val itemCount = adapter.count

        var totalHeight = 0
        for (position in 0 until itemCount) {
            val item = adapter.getView(position, null, this)
            item.measure(0, 0)

            totalHeight += item.measuredHeight

            val layoutParams = this.layoutParams
            layoutParams.height = totalHeight
            this.requestLayout()
        }
    }
}