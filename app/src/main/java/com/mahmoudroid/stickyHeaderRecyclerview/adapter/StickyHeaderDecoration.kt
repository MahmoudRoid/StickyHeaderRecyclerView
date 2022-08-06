package com.mahmoudroid.stickyHeaderRecyclerview.adapter

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.View.MeasureSpec
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.State
import com.mahmoudroid.stickyHeaderRecyclerview.databinding.ViewStickyHeaderBinding

class StickyHeaderDecoration(private val adapter: NameAdapter, root: View) : RecyclerView.ItemDecoration() {

    private val headerBinding by lazy { ViewStickyHeaderBinding.inflate(LayoutInflater.from(root.context)) }

    private val headerView: View
        get() = headerBinding.root

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: State) {
        super.onDrawOver(canvas, parent, state)

        val topChild = parent.getChildAt(0)
        val secondChild = parent.getChildAt(1)

        parent.getChildAdapterPosition(topChild)
            .let { topChildPosition ->
                val header = adapter.getHeaderForCurrentPosition(topChildPosition)
                headerBinding.tvStickyHeader.text = header.toString()

                layoutHeaderView(topChild)

                canvas.drawHeaderView(topChild, secondChild)
            }
    }

    private fun layoutHeaderView(topView: View?) {
        topView?.let {
            headerView.measure(
                MeasureSpec.makeMeasureSpec(topView.width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
            )
            headerView.layout(topView.left, 0, topView.right, headerView.measuredHeight)
        }
    }

    private fun Canvas.drawHeaderView(topView: View?, secondChild: View?) {
        save()
        translate(0f, calculateHeaderTop(topView, secondChild))
        headerView.draw(this)
        restore()
    }

    private fun calculateHeaderTop(topView: View?, secondChild: View?): Float =
        secondChild?.let { secondView ->
            val threshold = getPixels(8, headerBinding.root.context) + headerView.bottom
            if (secondView.findViewById<View>(headerView.id)?.visibility != View.GONE && secondView.top <= threshold) {
                (secondView.top - threshold).toFloat()
            } else {
                maxOf(topView?.top ?: 0, 0).toFloat()
            }
        } ?: maxOf(topView?.top ?: 0, 0).toFloat()


    private fun getPixels(dipValue: Int, context: Context): Int {
        val resources: Resources = context.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dipValue.toFloat(),
            resources.displayMetrics
        ).toInt()
    }
}