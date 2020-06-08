package com.tenny.edgecircle.utils

import android.content.res.Resources
import android.graphics.Rect
import android.graphics.RectF
import android.util.TypedValue

private val displayMetrics = Resources.getSystem().displayMetrics

val Float.dp
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)

val Int.dp
    get() = this.toFloat().dp

fun Rect.toRectF(offset: Float): RectF {
    return RectF(
        this.left.toFloat() + offset,
        this.top.toFloat() + offset,
        this.right.toFloat() - offset,
        this.bottom.toFloat() - offset
    )
}
