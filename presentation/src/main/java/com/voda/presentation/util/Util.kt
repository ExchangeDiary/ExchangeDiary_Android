package com.voda.presentation.util

import android.content.Context
import android.util.TypedValue

object Util {
    fun dpToPx(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.getResources().getDisplayMetrics()
        ).toInt()
    }
}
