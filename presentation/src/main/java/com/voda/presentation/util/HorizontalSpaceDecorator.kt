package com.voda.presentation.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpaceDecorator(
    private val space: Float,
    private val first: Float,
    private val last: Float,
    private val context: Context
) : RecyclerView.ItemDecoration() {

    private val sp = Util.dpToPx(space, context)
    private val f = Util.dpToPx(first, context)
    private val l = Util.dpToPx(last, context)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left += f
        }
        if (parent.getChildAdapterPosition(view) == state.itemCount - 1) {
            outRect.right += l
        } else {
            outRect.right += sp
        }
    }
}