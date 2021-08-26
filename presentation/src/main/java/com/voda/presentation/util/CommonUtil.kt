package com.voda.presentation.util

import android.content.res.Resources

object CommonUtil {
    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}